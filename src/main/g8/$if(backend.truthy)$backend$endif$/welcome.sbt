import sbtwelcome._
import scala.Console._

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
  UsefulTask("Docker/publishLocal", "Publish server locally  as a docker image").alias("dpl"),
  UsefulTask("Docker/publish", "Publish server as a docker image to remote container registry").alias("dp"),
  UsefulTask("project root", "Return to root project").alias("r")
)

logoColor := RED
