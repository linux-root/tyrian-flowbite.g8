import com.w47s0n.consolebox.Consolebox
import com.w47s0n.consolebox.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Shared logging utilities for development environment tools.
 * Provides consistent boxed message formatting across CLI and code watcher components.
 */
object LoggingUtils {

  // ============================================================================
  // Boxed Message Builders (return String)
  // ============================================================================

  def boxedSuccess(lines: String*): String =
    Consolebox.box(lines.mkString("\n"), LogLevel.Success)

  def boxedError(lines: String*): String =
    Consolebox.box(lines.mkString("\n"), LogLevel.Error)

  def boxedWarning(lines: String*): String =
    Consolebox.box(lines.mkString("\n"), LogLevel.Warning)

  def boxedInfo(lines: String*): String =
    Consolebox.box(lines.mkString("\n"))

  // ============================================================================
  // Direct Logging Helpers (print to stdout)
  // ============================================================================

  def logSuccess(lines: String*): Unit =
    println(boxedSuccess(lines: _*))

  def logError(lines: String*): Unit =
    println(boxedError(lines: _*))

  def logWarning(lines: String*): Unit =
    println(boxedWarning(lines: _*))

  def logInfo(lines: String*): Unit =
    println(boxedInfo(lines: _*))

  // ============================================================================
  // Utility Methods
  // ============================================================================

  def currentTimeFormatted(): String =
    LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a"))
}
