import sbtwelcome._
import scala.Console._

/**
 * Logo generated on [[https://patorjk.com]]
 */

logo :=
  s"""
     |   ▄▖    ▘    
     |   ▐ ▌▌▛▘▌▀▌▛▌
     |   ▐ ▙▌▌ ▌█▌▌▌
     |     ▄▌       
     |     
     | Scala Version : \${(scalaVersion).value}
     | 
     |""".stripMargin

usefulTasks := Seq(
  UsefulTask("dev", "Start both ~fastOptJS and Vite dev server concurrently").alias("devServer"),
  UsefulTask("publishDist", "Build static web artifact").alias("dst"),
  UsefulTask("Docker/publishLocal", "Publish locally web app as a docker image").alias("dpl"),
  UsefulTask("Docker/publish", "Publish web app as a docker image to remote container registry").alias("dp"),
  UsefulTask("project root", "Return to root project").alias("r")
)

logoColor := MAGENTA
