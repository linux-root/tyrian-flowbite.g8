import sbt.io.Path.relativeTo
import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import sbt._
import Keys._

lazy val autoImportSettings = Seq(
  scalacOptions += "-Yimports:zio,scala,scala.Predef"
)

ThisBuild / organization := "$organization$"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin, DockerPlugin)
  .settings(
    name         := "tyrian-flowbite",
    scalaVersion := "3.5.1",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true,
    // Source maps seem to be broken with bundler
    Compile / fastOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    libraryDependencies ++= Seq(
      "io.indigoengine"            %%% "tyrian-zio"                % Dependencies.Tyrian,
      "dev.zio"                    %%% "zio-interop-cats"          % Dependencies.ZioInteropCats,
      "org.http4s"                 %%% "http4s-dom"                % Dependencies.Http4sDom,
      "com.softwaremill.quicklens" %%% "quicklens"                 % Dependencies.Quicklens,
      ("org.scala-js"              %%% "scalajs-java-securerandom" % Dependencies.JavaSecureRandom).cross(CrossVersion.for3Use2_13)
    )
  )
  .settings(
    dockerBaseImage       := "nginx:alpine",
    Docker / publish      := (Docker / publish).dependsOn(Compile / fullOptJS).value,
    Docker / publishLocal := (Docker / publishLocal).dependsOn(Compile / fullOptJS).value,
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
      println("\n\nWebpack bundling...")
      val result          = "npm run build:prod".!
      val frontendDist    = baseDirectory.value / "dist"
      val nginxConfigFile = baseDirectory.value / "nginx.conf"

      (frontendDist ** "*").get.map { file =>
        file -> s"/usr/share/nginx/html/\${frontendDist.relativize(file).get.getPath}"
      } :+ (nginxConfigFile -> "/nginx.conf")

    }
  )
