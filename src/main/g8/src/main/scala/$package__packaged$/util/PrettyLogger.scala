package $package$.util

import org.scalajs.dom.console
import tyrian.Cmd
$if(use_zio.truthy)$
import zio.Task
import zio.interop.catz.*
$else$
import cats.effect.*
import cats.effect.Sync
$endif$

object PrettyLogger {
  def success(msg: String): Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Nothing] =
    Cmd.SideEffect(
      console.log(
        s"%c😇 \$msg %c",
        "color: white; background: green; font-size: 16px; padding: 5px 10px; border-radius: 5px;",
        "color: green; font-size: 14px;"
      )
    )

  def info(msg: String): Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Nothing] =
    Cmd.SideEffect(
      console.log(
        s"%cℹ️ \$msg %c",
        "color: white; background: blue; font-size: 16px; padding: 5px 10px; border-radius: 5px;",
        "color: blue; font-size: 14px;"
      )
    )

  def error(msg: String): Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Nothing] =
    Cmd.SideEffect(
      console.log(
        s"%cℹ️ \$msg %c",
        "color: white; background: red; font-size: 16px; padding: 5px 10px; border-radius: 5px;",
        "color: red; font-size: 14px;"
      )
    )
}
