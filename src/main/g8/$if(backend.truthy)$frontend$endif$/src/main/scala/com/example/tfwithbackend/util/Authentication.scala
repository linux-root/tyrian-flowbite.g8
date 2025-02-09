package com.example.tfwithbackend.util

import tyrian.Cmd
import tyrian.cmds.*
import zio.*
import com.example.until.HttpHelper
import com.example.tfwithbackend.model.Msg
object Authentication {

  def authenticate(username: String, password: String): Cmd[Task, Msg] =
    HttpHelper.login(username, password)
}
