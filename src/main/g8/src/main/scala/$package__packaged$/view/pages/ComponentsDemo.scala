package $package$.view.pages

import tyrian.Html.*
import tyrian.Html
import $package$.model.Msg
import $package$.page.*
import $package$.view.components.Icons

object ComponentsDemo:

  private def componentItem(name: String, page: Page, imageUrl: String): Html[Msg] =
    a(
      onClick(Msg.NavigateTo(page)),
      cls := "h-64 bg-white rounded-lg border border-gray-100 hover:border-white dark:border-gray-800 dark:hover:border-gray-700 hover:shadow-lg dark:hover:shadow-lg-light dark:bg-gray-900"
    )(
      div(
        cls := "bg-gray-50 dark:bg-gray-800 rounded-t-md py-2.5 px-5 flex justify-between items-center border-b border-gray-200 dark:border-gray-700"
      )(span(cls := "text-base font-medium text-gray-900 dark:text-white")(name), span(cls := "text-gray-500 dark:text-gray-400")(Icons.link)),
      div(cls := "flex justify-center items-center h-52")(
        div(cls := "relative h-4/6 dark:hidden w-56")(
          span(
            style := "box-sizing: border-box; display: block; overflow: hidden; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; position: absolute; inset: 0px;"
          )(
            img(
              alt   := "Alerts",
              src   := imageUrl,
              style := "position: absolute; inset: 0px; box-sizing: border-box; padding: 0px; border: none; margin: auto; display: block; width: 0px; height: 0px; min-width: 100%; max-width: 100%; min-height: 100%; max-height: 100%; object-fit: contain;",
              sizes := "100vw"
            )
          )
        ),
        div(cls := "hidden relative h-4/6 dark:block w-56")(
          span(
            style := "box-sizing: border-box; display: block; overflow: hidden; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; position: absolute; inset: 0px;"
          )(
            img(
              alt   := "Alerts",
              src   := "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
              cls   := "w-56",
              style := "position: absolute; inset: 0px; box-sizing: border-box; padding: 0px; border: none; margin: auto; display: block; width: 0px; height: 0px; min-width: 100%; max-width: 100%; min-height: 100%; max-height: 100%; object-fit: contain;"
            )
          )
        )
      )
    )

  def apply(): Html[Msg] =
    val alert      = componentItem("Alerts", Page.Alerts, "/assets/images/alerts.svg")
    val buttons    = componentItem("Button", Page.Buttons, "/assets/images/buttons.svg")
    val toggle     = componentItem("Toggle", Page.Toggle, "/assets/images/toggle.svg")
    val datepicker = componentItem("Datepicker", Page.Datepicker, "/assets/images/datepicker.svg")
    val accordion  = componentItem("Accordion", Page.Accordion, "/assets/images/accordion.svg")
    val badges     = componentItem("Badges", Page.Badges, "/assets/images/badges.svg")
    val cards      = componentItem("Cards", Page.Cards, "/assets/images/cards.svg")
    val carousel   = componentItem("Carousel", Page.Carousel, "/assets/images/carousel.svg")
    val components = List(cards, buttons, toggle, datepicker, alert, accordion, badges, carousel)
    div(cls := "cursor-pointer w-full grid grid-cols-1 gap-6 sm:grid-cols-2 xl:grid-cols-3 my-8")(
      components
    )
