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

lazy val webpackDevServer = taskKey[Unit](s"Start dev server. Should be oppened in a separated terminal")

webpackDevServer := {
  val compiledScalajsFile = Paths.get(s"target/scala-\${scalaVersion.value}/tyrian-flowbite-fastopt/main.js")
  println(compiledScalajsFile)
  if (Files.exists(compiledScalajsFile)) {
    println(s"\n\${scala.Console.GREEN}Installing npm packages...\${scala.Console.RESET}")
    "npm install".!
    println(s"\n\${scala.Console.GREEN}Spawning up Dev server...")
    println(s"\n\${scala.Console.GREEN}It will be available shortly on : \${scala.Console.YELLOW}http://localhost:9876 \${scala.Console.RESET}")
    "npm run dev".!
  } else {
    println(s"\n\${scala.Console.RED}Please run watch command first. Then run dev command in a separated terminal\n\${scala.Console.RESET}")
  }
}
