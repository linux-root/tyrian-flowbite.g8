import com.typesafe.sbt.SbtNativePackager.Docker
import com.typesafe.sbt.packager.Keys.{dockerBuildCommand, dockerExecCommand, dockerBuildOptions}

object DockerSettings {

   $if(backend.truthy)$
  /**
   * Container image tags will look like this :
   * '$docker_repo$/$name$:backend-0.1.0' and '$docker_repo$/$name$:frontend-0.1.0'
   * */
   $else$
   // Container image tag will look like this : '$docker_repo$/$name$:0.1.0'
  $endif$
  lazy val repository = "$docker_repo$" 


  /**
   * * support build x86 - intel CPU image on Mac M1 chip required run 'docker buildx install' to
   * set docker buildx up
   */
  lazy val x86ArchSetting = Docker / dockerBuildCommand := {
    val armArch = List("aarch64", "arm64") // Mac ARM chip M
    if (armArch.contains(sys.props("os.arch"))) {
      dockerExecCommand.value ++ Seq("buildx", "build", "--platform=linux/amd64", "--load") ++ dockerBuildOptions.value :+ "."
    } else dockerBuildCommand.value
  }
}
