import sbt.io.Path.relativeTo
import scala.Console._
import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import sbt._
import Keys._

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin, DockerPlugin)
  .settings(
    organization := "$organization$",
    name := "$name;format="norm"$",
    scalaVersion := "$scala_version$",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true,
    // Source maps seem to be broken with bundler
    Compile / fastOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    libraryDependencies ++= Seq(
      $if(use_zio.truthy)$
      "io.indigoengine"            %%% "tyrian-zio"                % Dependencies.Tyrian,
      "dev.zio"                    %%% "zio-interop-cats"          % Dependencies.ZioInteropCats,
      $else$
      "io.indigoengine"            %%% "tyrian-io"                % Dependencies.Tyrian,
      $endif$
      "com.softwaremill.quicklens" %%% "quicklens"                 % Dependencies.Quicklens,
      ("org.scala-js"              %%% "scalajs-java-securerandom" % Dependencies.JavaSecureRandom).cross(CrossVersion.for3Use2_13)
    )
  )
  .settings(
    dockerBaseImage       := "nginx:stable-perl",
    Docker / publish      := (Docker / publish).dependsOn(Compile / fullLinkJS).value,
    Docker / publishLocal := (Docker / publishLocal).dependsOn(Compile / fullLinkJS).value,
    dockerExposedPorts    := Seq(80),
    dockerRepository      := Some(DockerSettings.repository),
    DockerSettings.x86ArchSetting,
    Docker / defaultLinuxInstallLocation := "/usr/share/nginx/html",
    dockerCommands := dockerCommands.value.filter {
      case ExecCmd(cmd, _) => cmd != "ENTRYPOINT" && cmd != "CMD" && cmd != "USER"
      case Cmd(cmd, _)     => cmd != "USER" && cmd != "RUN"
      case _               => true

    } ++ Seq(Cmd("COPY", "nginx.conf", "/etc/nginx/nginx.conf"), Cmd("CMD", """["nginx", "-g", "daemon off;"]""")),
    Docker / mappings ++= {
      import sys.process._
      println(s"\n\$GREEN Installing npm packages...\$RESET")
      "npm install".!
      println(s"\n\n\$GREEN Webpack bundling...\$RESET\n\n")
      val result          = "npm run build:prod".!
      val frontendDist    = baseDirectory.value / "dist"
      val nginxConfigFile = baseDirectory.value / "nginx.conf"

      (frontendDist ** "*").get.map { file =>
        file -> s"/usr/share/nginx/html/\${frontendDist.relativize(file).get.getPath}"
      } :+ (nginxConfigFile -> "/nginx.conf")

    }
  )
