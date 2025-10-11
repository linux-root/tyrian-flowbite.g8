import scala.sys.process._
import java.nio.file.{Files, FileSystems}
import java.nio.file.StandardWatchEventKinds._
import sbt.File
import com.w47s0n.consolebox.Consolebox
import com.w47s0n.consolebox.*
import LoggingUtils._

final class ScalaJSCodeWatcher(
  sourceDirectories: Seq[File],
  compilationRunner: () => Boolean,  // Returns true on success, false on failure
  viteServerStarter: () => Process,
) {
  private val DebounceMillis = 500L
  private val PollIntervalSeconds = 1L
  private val ShutdownGracePeriodMillis = 500L
  private val ExcludedDirectories = Set("target", "node_modules", "dist")

  @volatile private var isRunning: Boolean = true
  @volatile private var cleanedUp: Boolean = false
  @volatile private var viteProcess: Option[Process] = None

  private def logScalaJsInfo(message: String): Unit = println(s"[Scala.js] \$message")
  private def logScalaJsError(message: String): Unit = println(s"[Scala.js ERROR] \$message")

  def start(): Unit = {
    logScalaJsInfo("Running initial compilation...")
    val succeeded = this.compilationRunner()
    if (succeeded) {
      logSuccess(s"[Scala.js] Initial compilation completed at \${currentTimeFormatted()}")
      startDevEnvironment()
    } else {
      logError(
        "[Scala.js] Initial compilation failed - Vite dev server will not start",
        "Fix the compilation errors and restart the development environment"
      )
    }
  }

  private def startDevEnvironment(): Unit = {
    registerShutdownHook()
    startFileWatcher()

    try {
      val process = viteServerStarter()
      this.viteProcess = Some(process)
      monitorViteProcess(process)
    } catch {
      case _: InterruptedException =>
      case e: Exception => logError(s"Unexpected error: \${e.getMessage}")
    } finally {
      cleanup()
    }
  }

  private def registerDirectories(watchService: java.nio.file.WatchService): Unit = {
    sourceDirectories
      .filter(_.exists())
      .foreach(registerDirectoryTree(_, watchService))
  }

  private def registerDirectoryTree(rootDir: File, watchService: java.nio.file.WatchService): Unit = {
    Files.walk(rootDir.toPath)
      .filter(Files.isDirectory(_))
      .filter(isWatchableDirectory)
      .forEach(_.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE))
  }

  private def isWatchableDirectory(path: java.nio.file.Path): Boolean = {
    val name = path.getFileName.toString
    !ExcludedDirectories.contains(name) && !name.startsWith(".")
  }

  private def watchLoop(watchService: java.nio.file.WatchService): Unit = {
    var lastCompilationTime = 0L

    while (isRunning) {
      try {
        Option(watchService.poll(PollIntervalSeconds, java.util.concurrent.TimeUnit.SECONDS))
          .foreach { key =>
            processWatchEvents(key, lastCompilationTime) match {
              case Some(newTime) => lastCompilationTime = newTime
              case None => // No recompilation needed
            }
            key.reset()
          }
      } catch {
        case _: InterruptedException => isRunning = false
        case e: Exception => logScalaJsError(s"Watch error: \${e.getMessage}")
      }
    }
  }

  private def processWatchEvents(
    key: java.nio.file.WatchKey,
    lastCompilationTime: Long
  ): Option[Long] = {
    val scalaFileChanges = extractScalaFileChanges(key)

    if (scalaFileChanges.isEmpty) {
      None
    } else {
      val now = System.currentTimeMillis()
      if (shouldTriggerCompilation(now, lastCompilationTime)) {
        triggerRecompilation(scalaFileChanges.length)
        Some(now)
      } else {
        None
      }
    }
  }

  private def extractScalaFileChanges(key: java.nio.file.WatchKey): Seq[java.nio.file.WatchEvent[_]] = {
    key.pollEvents().toArray
      .map(_.asInstanceOf[java.nio.file.WatchEvent[_]])
      .filter(event => Option(event.context()).exists(_.toString.endsWith(".scala")))
      .toSeq
  }

  private def shouldTriggerCompilation(now: Long, lastCompilationTime: Long): Boolean =
    now - lastCompilationTime > DebounceMillis

  private def triggerRecompilation(fileCount: Int): Unit = {
    logScalaJsInfo(s"Detected \$fileCount Scala file change(s), recompiling...")
    val succeeded = this.compilationRunner()
    if (succeeded) {
      logScalaJsInfo(s"Recompilation completed at \${currentTimeFormatted()}")
    }
  }

  private def startFileWatcher(): Unit = {
    val watchService = FileSystems.getDefault.newWatchService()
    registerDirectories(watchService)

    val watcherThread = new Thread {
      setDaemon(true)
      override def run(): Unit = watchLoop(watchService)
    }
    watcherThread.start()
  }

  private def monitorViteProcess(process: Process): Unit = {
    while (isRunning && process.isAlive()) {
      Thread.sleep(ShutdownGracePeriodMillis)
    }
    if (!process.isAlive()) {
      isRunning = false
    }
  }

  private def registerShutdownHook(): Unit = {
    java.lang.Runtime.getRuntime.addShutdownHook(new Thread(() => cleanup(), "shutdown-hook"))
  }

  private def cleanup(): Unit = {
    if (!cleanedUp) {
      cleanedUp = true
      performCleanup()
    }
  }

  private def performCleanup(): Unit = {
    try {
      isRunning = false
      terminateViteProcess()
    } finally {
      printShutdownMessage()
    }
  }

  private def terminateViteProcess(): Unit = {
    viteProcess.foreach { process =>
      process.destroy()
      waitForProcessTermination()
    }
  }

  private def waitForProcessTermination(): Unit = {
    try {
      Thread.sleep(ShutdownGracePeriodMillis)
    } catch {
      case _: InterruptedException => // Ignore during shutdown
    }
  }

  private def printShutdownMessage(): Unit = {
    System.err.println(Consolebox.box("Development environment stopped", LogLevel.Success))
    System.err.flush()
  }
}
