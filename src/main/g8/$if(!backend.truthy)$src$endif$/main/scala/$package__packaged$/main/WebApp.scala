package $package$.main

import tyrian.Html.*
import tyrian.*
$if(use_zio.truthy) $
import zio.*
import zio.interop.catz.*
$else$
import cats.effect.IO
$endif$
import com.softwaremill.quicklens.*
import scala.scalajs.js
import scala.scalajs.js.annotation.*
import tyrian.CSS.*
import tyrian.Routing
import $package$.model.*
import $package$.util.Flowbite
import $package$.view.MainContainer
import $package$.route.*
import $package$.util.*
import $package$.page.*

@JSImport("resources/index.css", JSImport.Default)
@js.native
object IndexCSS extends js.Object

@JSExportTopLevel("TyrianApp")
object WebApp extends $if(use_zio.truthy)$TyrianZIOApp$else$TyrianIOApp$endif$[Msg, Model]:

  private val css = IndexCSS // Webpack will use this css when bundling

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

  def init(flags: Map[String, String]): (Model, Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Msg]) =
    val initState = Model.init
    (initState, Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[$if(use_zio.truthy)$Task$else$IO$endif$, Msg]) =
    case Msg.NoOp => (model, Cmd.None)

    case Msg.ToggleDarkMode =>
      (model.toggleDarkMode, Cmd.None)

    case Msg.GoToInternet(loc) =>
      (model, Nav.loadUrl(loc.url))

    case Msg.LogMessage(msg) =>
      (model, PrettyLogger.info(msg))

    case Msg.NavigateTo(page) =>
      if (page.isSecured) // Tips: check browser console to see the mocked authentication
      then (model, Authentication.authenticate("scala3", "fpLov3rs") |+| page.doNavigate(model))
      else (model, page.doNavigate(model))

    case Msg.DoNavigate(page) =>
      (model.modify(_.currentPage).setTo(page), Nav.pushUrl[$if(use_zio.truthy)$Task$else$IO$endif$](page.path) |+| Flowbite.initCmd)
    case Msg.UnhandledRoute(path) =>
      (model, PrettyLogger.error(s"Unhandled route: \$path"))

  def view(model: Model): Html[Msg] =
    val pageContent = model.currentPage.render(model)
    MainContainer(pageContent, model.isDarkMode)

  def subscriptions(model: Model): Sub[$if(use_zio.truthy)$Task$else$IO$endif$, Msg] = Sub.None
