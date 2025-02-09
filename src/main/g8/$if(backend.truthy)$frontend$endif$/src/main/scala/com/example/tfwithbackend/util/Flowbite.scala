package com.example.tfwithbackend.util

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import tyrian.Cmd
import scala.concurrent.duration.*
import zio.*
import zio.interop.catz.*
object Flowbite:
  @JSImport("js/flowbite.js", JSImport.Namespace)
  @js.native
  private object FlowbiteJS extends js.Object:
    def init(): Unit = js.native

  val initCmd: Cmd[Task, Nothing] =
    val effect = ZIO.attempt(FlowbiteJS.init()).delay(100.millis)
    Cmd.SideEffect(effect)
