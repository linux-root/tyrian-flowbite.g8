package $package$.main

import tyrian.Html.*
import tyrian.*
import zio.*
import zio.interop.catz.*
import com.softwaremill.quicklens.*
import scala.scalajs.js
import scala.scalajs.js.annotation.*
import tyrian.CSS.*
import tyrian.Routing
import $package$.model.*
import $package$.model.Model.User
import $package$.util.Flowbite
import $package$.view.MainContainer
import $package$.route.*
import $package$.util.*
import $package$.page.*
import $package$.util.LocalStorageHelper
import $package$.until.HttpHelper

@JSExportTopLevel("TyrianApp")
object WebApp extends TyrianZIOApp[Msg, Model]:

  def main(args: Array[String]): Unit = launch("app") // mount the app to div with id="app"

  def router: Location => Msg =
    case loc: Location.Internal =>
      loc.pathName match
        case Route.Home(_) =>
          Msg.NavigateTo(Page.Home)

        case Route.ComponentDemo(page) =>
          Msg.NavigateTo(page)

        case path @ _ =>
          Msg.UnhandledRoute(path)

    case loc: Location.External => Msg.GoToInternet(loc)

  def init(flags: Map[String, String]): (Model, Cmd[Task, Msg]) =
    val initState = Model.init
    (initState, LocalStorageHelper.obtainSessionCmd)

  def update(model: Model): Msg => (Model, Cmd[Task, Msg]) =
    case Msg.NoOp => (model, Cmd.None)

    case Msg.LogMessage(msg) =>
      (model, PrettyLogger.info(msg))

    case Msg.Error(msg) =>
      (model, PrettyLogger.error(msg))

    case Msg.ToggleDarkMode =>
      (model.toggleDarkMode, Cmd.None)

    case Msg.GoToInternet(loc) =>
      (model, Nav.loadUrl(loc.url))

    case Msg.UpdateLoginForm(form) =>
      (model.copy(loginForm = form), Cmd.None)

    case Msg.SubmitLogin =>
      val loginCmd = HttpHelper.login(model.loginForm.username, model.loginForm.password)
      (model.modify(_.loginForm.isLoading).setTo(true), loginCmd)

    case Msg.NavigateTo(page) =>
      val isLoginRequired = page.isSecured && !model.isLoggedIn
      if (!isLoginRequired) {
        (model.navigateTo(page), page.doNavigate(model))
      } else (Model.init, Cmd.Emit(Msg.NavigateTo(Page.Login)))

    case Msg.RestoredSession(accessToken, refreshToken) =>
      (model.loginSuccess(accessToken, refreshToken), Cmd.Emit(Msg.DoNavigate(Page.Home)))

    case Msg.LoginSuccess(accessToken, refreshToken) =>
      val saveTokenAndEnterHome = LocalStorageHelper.storeSession(accessToken, refreshToken) |+| Cmd.Emit(Msg.NavigateTo(Page.Home))
      (model.loginSuccess(accessToken, refreshToken), saveTokenAndEnterHome)

    case Msg.LoginFailure(msg) =>
      (model.modify(_.loginForm.error).setTo(Some(msg)), Cmd.None)

    case Msg.Logout =>
      (model.logout, LocalStorageHelper.cleanStorageWhenLogout |+| Cmd.Emit(Msg.NavigateTo(Page.Login)))

    case Msg.DoNavigate(page) =>
      (model.modify(_.currentPage).setTo(page), Nav.pushUrl[Task](page.path) |+| Flowbite.initCmd)

    case Msg.UnhandledRoute(path) =>
      (model, PrettyLogger.error("Unhandled route" + path))

    case Msg.GreetingFromBackend(text) =>
      (model.modify(_.homeState.serverMessage).setTo(Some(text)), PrettyLogger.info(text))

    case Msg.NextBackendMessage =>
      (model, HttpHelper.fetchServerMessage2)

    case Msg.SendHttpRequestWithAccessToken(f) =>
      val cmd = model.user match
        case None =>
          Cmd.Emit(Msg.DoNavigate(Page.Login))
        case Some(User(_, accessToken, _)) =>
          f(accessToken)

      (model, cmd)

  def view(model: Model): Html[Msg] =
    val pageContent = model.currentPage.render(model)
    MainContainer(pageContent, model.isDarkMode, model.isLoggedIn)

  def subscriptions(model: Model): Sub[Task, Msg] = Sub.None
