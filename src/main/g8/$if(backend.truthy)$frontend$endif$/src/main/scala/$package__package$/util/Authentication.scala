package $package$.util

import tyrian.Cmd
import tyrian.cmds.*
import zio.*
import $package$.until.HttpHelper
import $package$.model.Msg
object Authentication {

  def authenticate(username: String, password: String): Cmd[Task, Msg] =
    HttpHelper.login(username, password)
}
