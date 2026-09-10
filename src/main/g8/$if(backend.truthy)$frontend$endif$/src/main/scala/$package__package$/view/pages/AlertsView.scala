package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html
import $package$.model.Msg
import $package$.util.ElementIdGenerator
import $package$.view.components.Icons.i

object AlertsView:
  // Tailwind can only see class names that appear verbatim in source, so each
  // variant carries fully-spelled class strings rather than interpolated colors.
  case class AlertStyle(
    label: String,
    container: String,
    button: String
  )

  def alert(style: AlertStyle) =
    val _id           = ElementIdGenerator.generate("alert")
    val dismissTarget = "#" + _id
    div(
      id   := _id,
      cls  := s"flex items-center p-4 mb-4 text-sm border rounded-lg \${style.container}",
      role := "alert"
    )(
      i"""<svg class="shrink-0 w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
             <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
           </svg>""",
      div(
        cls := "ms-3 font-medium"
      )(
        text(s"A simple \${style.label} alert with an "),
        a(
          href := "#",
          cls  := "font-semibold underline hover:no-underline"
        )("example link"),
        text(". Give it a click if you like.")
      ),
      button(
        `type` := "button",
        cls    := s"ms-auto -mx-1.5 -my-1.5 rounded-lg focus:ring-2 p-1.5 inline-flex items-center justify-center h-8 w-8 \${style.button}",
        attr("data-dismiss-target", dismissTarget),
        attr("aria-label", "Close")
      )(
        span(cls := "sr-only")("Dismiss"),
        i"""<svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
               <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
             </svg>"""
      )
    )

  val blueStyle = AlertStyle(
    label = "info",
    container = "text-blue-800 border-blue-300 bg-blue-50 dark:bg-gray-800 dark:text-blue-400 dark:border-blue-800",
    button = "bg-blue-50 text-blue-500 focus:ring-blue-400 hover:bg-blue-200 dark:bg-gray-800 dark:text-blue-400 dark:hover:bg-gray-700"
  )

  val redStyle = AlertStyle(
    label = "danger",
    container = "text-red-800 border-red-300 bg-red-50 dark:bg-gray-800 dark:text-red-400 dark:border-red-800",
    button = "bg-red-50 text-red-500 focus:ring-red-400 hover:bg-red-200 dark:bg-gray-800 dark:text-red-400 dark:hover:bg-gray-700"
  )

  val greenStyle = AlertStyle(
    label = "success",
    container = "text-green-800 border-green-300 bg-green-50 dark:bg-gray-800 dark:text-green-400 dark:border-green-800",
    button = "bg-green-50 text-green-500 focus:ring-green-400 hover:bg-green-200 dark:bg-gray-800 dark:text-green-400 dark:hover:bg-gray-700"
  )

  val yellowStyle = AlertStyle(
    label = "warning",
    container = "text-yellow-800 border-yellow-300 bg-yellow-50 dark:bg-gray-800 dark:text-yellow-300 dark:border-yellow-800",
    button = "bg-yellow-50 text-yellow-500 focus:ring-yellow-400 hover:bg-yellow-200 dark:bg-gray-800 dark:text-yellow-300 dark:hover:bg-gray-700"
  )

  val grayStyle = AlertStyle(
    label = "dark",
    container = "text-gray-800 border-gray-300 bg-gray-50 dark:bg-gray-800 dark:text-gray-300 dark:border-gray-600",
    button = "bg-gray-50 text-gray-500 focus:ring-gray-400 hover:bg-gray-200 dark:bg-gray-800 dark:text-gray-300 dark:hover:bg-gray-700"
  )

  val alert1 = alert(blueStyle)
  val alert2 = alert(redStyle)
  val alert3 = alert(greenStyle)
  val alert4 = alert(yellowStyle)
  val alert5 = alert(grayStyle)

  def apply() = div(cls := "flex flex-col")(
    alert1,
    alert2,
    alert3,
    alert4,
    alert5
  )
