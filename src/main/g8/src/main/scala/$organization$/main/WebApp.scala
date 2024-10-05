package $organization$.main

import tyrian.Html.*
import tyrian.*
import zio.*
import zio.interop.catz.*
import $organization$.model.*
import $organization$.util.Flowbite
import scala.scalajs.js
import scala.scalajs.js.annotation.*
import tyrian.CSS.*
import tyrian.Routing
import $organization$.view.MainContainer
import $organization$.view.Welcome

@JSImport("resources/index.css", JSImport.Default)
@js.native
object IndexCSS extends js.Object

@JSExportTopLevel("TyrianApp")
object WebApp extends TyrianZIOApp[Msg, Model]:

  private val css = IndexCSS // Webpack will use this css when bundling

  def main(args: Array[String]): Unit = launch("app") // mount the app to div with id="app"

  def router: Location => Msg = Routing.none(Msg.NoOp)

  def init(flags: Map[String, String]): (Model, Cmd[Task, Msg]) =
    (Model.init, Flowbite.initCmd)

  def update(model: Model): Msg => (Model, Cmd[Task, Msg]) =
    case Msg.NoOp => (model, Cmd.None)

  def view(model: Model): Html[Msg] =
    MainContainer(
      Welcome("Hello world")
    )

  def subscriptions(model: Model): Sub[Task, Msg] = Sub.None
