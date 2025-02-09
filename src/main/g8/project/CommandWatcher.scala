import scala.sys.process._
import java.io.{BufferedReader, InputStreamReader}
import scala.util.matching.Regex

object CommandWatcher {
  private def readStream(stream: java.io.InputStream, onLine: String => Unit): Unit = {
    val reader = new BufferedReader(new InputStreamReader(stream))
    Iterator.continually(reader.readLine()).takeWhile(_ != null).foreach(onLine)
  }

  private def removeAnsiCodes(text: String): String =
    text.replaceAll("\u001B\\[[;\\d]*m", "")

  def watch(command: Seq[String], pattern: Regex, successMessage: String): Unit = {

    val process = Process(command, None, "FORCE_COLOR" -> "1").run(
      new ProcessIO(
        _ => (), 
        stdout =>
          readStream(
            stdout,
            line =>{
              if (pattern.findFirstIn(removeAnsiCodes(line)).isDefined)
                println(s"\n$successMessage")
            }
          ),
        stderr => readStream(stderr, line => println(s"⚠️ $line"))
      )
    )

    process.exitValue()
  }
}
