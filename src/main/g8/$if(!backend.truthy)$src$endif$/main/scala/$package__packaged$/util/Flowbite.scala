package $package$.util

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import tyrian.Cmd
import scala.concurrent.duration.*
$if(use_zio.truthy)$
import zio.*
import zio.interop.catz.*
$else$
import cats.effect.*
import cats.effect.Sync
$endif$

object Flowbite:
  @JSImport("js/flowbite.js", JSImport.Namespace)
  @js.native
  private object FlowbiteJS extends js.Object:
    def init(): Unit = js.native

  val initCmd: Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Nothing] =
    $if(use_zio.truthy)$
    val effect = ZIO.attempt(FlowbiteJS.init()).delay(100.millis)
    $else$
    val effect = Temporal[IO].sleep(100.millis) *> Sync[IO].delay(FlowbiteJS.init())
    $endif$
    Cmd.SideEffect(effect)
