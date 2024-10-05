import com.typesafe.sbt.SbtNativePackager.Docker
import com.typesafe.sbt.packager.Keys.{dockerBuildCommand, dockerExecCommand, dockerBuildOptions}

object DockerSettings {
  lazy val repository = s"myrepository"

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
