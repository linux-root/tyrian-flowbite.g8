package com.example.util

import org.scalajs.dom
import tyrian.Cmd
import zio.Task
import zio.ZIO
import zio.interop.catz.*
import cats.implicits
import tyrian.cmds.LocalStorage

import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.page.Page

object LocalStorageHelper:

  val handler: PartialFunction[LocalStorage.Result, Msg] = {
    case LocalStorage.Result.Success => Msg.NoOp
    case e                           => Msg.Error(e.toString)
  }

  def storeSession(accessToken: String, refreshToken: String): Cmd[Task, Msg] =
    Cmd.Batch(
      LocalStorage.setItem("accessToken", accessToken)(handler),
      LocalStorage.setItem("refreshToken", refreshToken)(handler)
    )

  /**
   * In case users reload SPA, session will be obtained from local storage
   */
  def obtainSessionCmd: Cmd[Task, Msg] =
    // TODO: might need to check token expiry
    val tryLoginFromLocalStorage = ZIO.attempt {
      val accessToken  = dom.window.localStorage.getItem("accessToken")
      val refreshToken = dom.window.localStorage.getItem("refreshToken")

      if (accessToken != null && refreshToken != null)
        Msg.RestoredSession(accessToken, refreshToken)
      else
        Msg.NavigateTo(Page.Login)
    }

    Cmd.Run(tryLoginFromLocalStorage)

  val cleanStorageWhenLogout: Cmd[Task, Msg] =
    LocalStorage.removeItem("accessToken")(handler) |+| LocalStorage.removeItem("refreshToken")(handler)
