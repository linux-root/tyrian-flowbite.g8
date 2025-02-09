import org.apache.tools.ant.taskdefs.Taskdef
import sbtwelcome._
import scala.collection.compat.immutable.LazyList
import sys.env
import scala.Console._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Logo generated on [[https://patorjk.com]]
 */

logo :=
  s"""
     |      ███████╗██╗ ██████╗       ██╗  ██╗████████╗████████╗██████╗ 
     |      ╚══███╔╝██║██╔═══██╗      ██║  ██║╚══██╔══╝╚══██╔══╝██╔══██╗
     |        ███╔╝ ██║██║   ██║█████╗███████║   ██║      ██║   ██████╔╝
     |       ███╔╝  ██║██║   ██║╚════╝██╔══██║   ██║      ██║   ██╔═══╝ 
     |      ███████╗██║╚██████╔╝      ██║  ██║   ██║      ██║   ██║     
     |      ╚══════╝╚═╝ ╚═════╝       ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚═╝     
     |
     | Scala Version : \${(scalaVersion).value}
     | 
     |""".stripMargin

usefulTasks := Seq(
  UsefulTask("run", "Run server"),
  UsefulTask("Docker/publishLocal", "Publish locally web app as a docker image").alias("dpl"),
  UsefulTask("Docker/publish", "Publish web app as a docker image to remote container registry").alias("dp"),
  UsefulTask("project root", "Return to root project").alias("r")
)

logoColor := RED
