package $package$.page

import tyrian.Cmd
import tyrian.Html
import tyrian.Html.div
import java.util.UUID
import tyrian.cmds.Logger
import zio.*
import zio.interop.catz.*
import $package$.util.PrettyLogger
import $package$.view.pages.LoginPage
import $package$.until.HttpHelper
import $package$.model.*
import $package$.view.pages.*

enum Page(
  val title: String,
  val path: String,
  val render: Model => Html[Msg],
  beforeEnter: Model => Cmd[Task, Msg] = _ => Cmd.None, // e.g: side effect for loading data
  val isSecured: Boolean = true
){
  def doNavigate(model: Model): Cmd[Task, Msg] = beforeEnter(model) |+| Cmd.emit(Msg.DoNavigate(this))

  case Login      extends Page("Login", "/login", model => LoginPage(model.loginForm), isSecured = false)
  case Home       extends Page("Home", "/", model => Welcome(model.homeState), _ => HttpHelper.fetchServerMessage)
  case Alerts     extends Page("Alerts", "/components/alerts", model => AlertsView(), _ => PrettyLogger.info("Entering Alerts page"))
  case Accordion  extends Page("Accordion", "/components/accordion", model => AccordionView(), _ => PrettyLogger.info("Entering Accordion page"))
  case Buttons    extends Page("Buttons", "/components/buttons", model => ButtonsView(), _ => PrettyLogger.info("Entering Buttons page"))
  case Toggle     extends Page("Toggle", "/components/toggle", model => ToggleView(), _ => PrettyLogger.info("Entering Toggle page"))
  case Datepicker extends Page("Datepicker", "/components/datepicker", model => DatepickerView(), _ => PrettyLogger.info("Entering Datepicker page"))
  case Badges     extends Page("Badges", "/components/badges", model => BadgesView(), _ => PrettyLogger.info("Entering Badges page"))
  case Cards      extends Page("Cards", "/components/cards", model => CardsView(), _ => PrettyLogger.info("Entering Cards page"))
  case Carousel   extends Page("Carousel", "/components/carousel", model => CarouselView(), _ => PrettyLogger.info("Entering Carousel page"))
}
