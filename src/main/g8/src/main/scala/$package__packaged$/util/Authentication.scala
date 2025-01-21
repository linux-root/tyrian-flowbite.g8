package $package$.util

import tyrian.Cmd
import tyrian.cmds.*
$if(use_zio.truthy)$
import zio.*
$else$
import cats.effect.IO
$endif$

object Authentication {

  def authenticate(username: String, password: String): Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Nothing] =
    PrettyLogger.success(
      s"mocking authentication for username: \$username, password: \$password"
    )

}
