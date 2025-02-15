import scala.sys.process._
import java.nio.file.Paths
import java.nio.file.Files

object CLIUtils {
  private val reset = "\u001B[0m"
  private val green = "\u001B[32m"
  private val red   = "\u001B[31m"
  private val yellow = "\u001B[33m"
  private val tip1  = "Tip: Change backend URL: BACKEND_BASE_URL=http://server.com sbt"

  private def formatBoxContent(lines: Seq[String], color: String): String = {
    val maxWidth = lines.map(_.length).reduceOption(_ max _).getOrElse(0) // Safe max calculation
    val boxWidth = maxWidth + 4                                           // Padding (2 spaces on each side)

    val top    = color + "╭" + "─" * (boxWidth - 2) + "╮" + reset
    val middle = lines.map(line => color + "│ " + line.padTo(maxWidth, ' ') + " │" + reset).mkString("\n")
    val bottom = color + "╰" + "─" * (boxWidth - 2) + "╯" + reset

    (top + "\n" + middle + "\n" + bottom).stripTrailing()
  }

  def boxedConfigs(configs: (String, String)*): String = {
    val formattedLines = configs.map { case (key, value) => s"\$key: \$value" }
    formatBoxContent(formattedLines, green)
  }

  private def boxedSuccess(lines: String*): String = formatBoxContent(lines, green)
  private def boxedError(lines: String*): String   = formatBoxContent(lines, red)
  private def boxedWarning(lines: String*): String = formatBoxContent(lines, yellow)

  private def installNpmPackages(prefix: String = ""): Unit = {
    val command        = List("npm", "install", "--prefix", prefix)
    val pattern        = "found\\\s\\\d+\\\svulnerabilities".r
    val successMessage = boxedSuccess("Installed npm packages successfully !")
    CommandWatcher.watch(command, pattern, successMessage)
  }

  private def removeDistFolderIfAny(prefix: String = ""): Unit = {
    val currentDistFolder = Paths.get(s"\${prefix}dist")
    if (Files.exists(currentDistFolder)) {
      s"rm -r \${prefix}dist".!
      println(boxedWarning(s"Deleted existing '\${prefix}dist' folder"))
    }
  }

  $if(backend.truthy)$
  def startFrontendDevServer(scalaVersion: String, backendUrl: String): Unit = {
    val compiledScalajsFile = Paths.get(s"frontend/target/scala-\$scalaVersion/frontend-fastopt/main.js")
    if (Files.exists(compiledScalajsFile)) {
      installNpmPackages("frontend")
      val devCommand = Seq("npm", "run", "dev", "--prefix", "frontend")
      val pattern    = "compiled successfully".r
      val successMessage = boxedSuccess(
        "Web app now available on http://localhost:9876",
        s"Using Backend URL: \$backendUrl",
        tip1
      )
      CommandWatcher.watch(devCommand, pattern, successMessage)
    } else {
      println(boxedError("Please run watch command first", " Then run this in a separate terminal"))
    }

  }

  def buildFrontend(backendUrl: String): Unit = {
    installNpmPackages("frontend")
    removeDistFolderIfAny("frontend/")
    val buildCommand   = Seq("npm", "run", "build:prod", "--prefix", "frontend")
    val pattern        = "webpack \\\d+\\\.\\\d+\\\.\\\d+ compiled".r
    val successMessage = boxedSuccess("Web app is available at directory 'frontend/dist'", s"Backend URL of this build: \$backendUrl", tip1)
    CommandWatcher.watch(buildCommand, pattern, successMessage)
  }
  $else$
  def startFrontendDevServer(projectName: String, scalaVersion: String): Unit = {
    val compiledScalajsFile = Paths.get(s"target/scala-\$scalaVersion/\$projectName-fastopt/main.js")

    if (Files.exists(compiledScalajsFile)) {
      installNpmPackages()
      val devCommand = Seq("npm", "run", "dev")
      val pattern    = "compiled successfully".r
      val successMessage = boxedSuccess(
        "Web app now available on http://localhost:9876"
      )
      CommandWatcher.watch(devCommand, pattern, successMessage)
    } else {
      println(boxedError("Please run watch command first. Then run dev command in a separate terminal"))
    }

  }

  def buildFrontend(): Unit = {
    installNpmPackages()
    removeDistFolderIfAny()
    val buildCommand   = Seq("npm", "run", "build:prod")
    val pattern        = "webpack \\\d+\\\.\\\d+\\\.\\\d+ compiled".r
    val successMessage = boxedSuccess("Web app is available at directory 'dist'")
    CommandWatcher.watch(buildCommand, pattern, successMessage)
  }
  $endif$
}
