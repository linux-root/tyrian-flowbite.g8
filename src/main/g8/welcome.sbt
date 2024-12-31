import org.apache.tools.ant.taskdefs.Taskdef
import sbtwelcome._
import scala.sys.process._
import scala.collection.compat.immutable.LazyList
import sys.env
import scala.Console._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import java.nio.file.{Files, Paths}

/**
 * Logo generated on [[https://patorjk.com]]
 */

logo :=
  s"""
     |      ████████╗██╗   ██╗██████╗ ██╗ █████╗ ███╗   ██╗
     |      ╚══██╔══╝╚██╗ ██╔╝██╔══██╗██║██╔══██╗████╗  ██║
     |         ██║    ╚████╔╝ ██████╔╝██║███████║██╔██╗ ██║
     |         ██║     ╚██╔╝  ██╔══██╗██║██╔══██║██║╚██╗██║
     |         ██║      ██║   ██║  ██║██║██║  ██║██║ ╚████║
     |         ╚═╝      ╚═╝   ╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝
     |  
     |Version : \${version.value}
     |
     |\$YELLOW Scala \${scalaVersion.value}\$RESET
     | 
     |""".stripMargin

usefulTasks := Seq(
  UsefulTask("~fastLinkJS", "Auto re-compile when source code changes detected").alias("watch"),
  UsefulTask("webpackDevServer", s"Start Webpack dev server").alias("dev"),
  UsefulTask("Docker/publishLocal", "Publish locally web app as a docker image").alias("dpl"),
  UsefulTask("Docker/publish", "Publish web app as a docker image to remote container registry").alias("dp"),
  UsefulTask("welcome", "Menu").alias("h")
)

logoColor := MAGENTA

lazy val webpackDevServer = taskKey[Unit]("Start dev server. Should be oppened in a separated terminal")

webpackDevServer := {
  val compiledScalajsFile = Paths.get(s"target/scala-\${scalaVersion.value}/tyrian-flowbite-fastopt/main.js")
  println(compiledScalajsFile)
  if (Files.exists(compiledScalajsFile)) {
    println(s"\n\$GREEN Installing npm packages...\$RESET")
    "npm install".!
    println(s"\n\$GREEN Spawning up Dev server...")
    println(s"\n\n\nServer will be available shortly on : \$YELLOW http://localhost:9876 \$RESET\n\n\n")
    "npm run dev".!
  } else {
    println(s"\n\$RED Please run watch command first. Then run dev command in a separated terminal\n\$RESET")
  }
}
