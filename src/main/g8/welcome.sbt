import org.apache.tools.ant.taskdefs.Taskdef
import sbtwelcome._
import scala.sys.process._
import scala.collection.compat.immutable.LazyList
import sys.env
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
     |\${scala.Console.YELLOW}Scala \${scalaVersion.value}\${scala.Console.RESET}
     | 
     |""".stripMargin

usefulTasks := Seq(
  UsefulTask("~fastLinkJS", "Auto re-compile when source code changes detected").alias("watch"),
  UsefulTask("webpackDevServer", s"Start Webpack dev server").alias("dev"),
  UsefulTask("Docker/publishLocal", "Publish locally web app as a docker image").alias("dpl"),
  UsefulTask("Docker/publish", "Publish web app as a docker image to remote container registry").alias("dp"),
  UsefulTask("welcome", "Menu").alias("h")
)

logoColor := scala.Console.MAGENTA

lazy val webpackDevServer = taskKey[Unit](s"Start dev server")

webpackDevServer := {
  val nodeModulesPath = Paths.get("node_modules")
  if (Files.exists(nodeModulesPath) && Files.isDirectory(nodeModulesPath)) {
    "npm run dev".!
  } else {
    println(s"\${scala.Console.YELLOW}Folder node_modules doesn't exist, installing npm packages...\${scala.Console.RESET}")
    "npm install".!
    "npm run dev".!
  }
}
