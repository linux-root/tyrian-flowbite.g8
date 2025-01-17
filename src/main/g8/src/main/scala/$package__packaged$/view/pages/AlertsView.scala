package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html.raw
import tyrian.Html
import $package$.model.Msg
import $package$.util.ElementIdGenerator

object AlertsView:
  case class AlertStyle(
    color: String,
    borderColor: String,
    bgColor: String,
    buttonBgColor: String,
    buttonTextColor: String,
    focusRingColor: String,
    hoverBgColor: String,
    darkTextColor: String,
    darkBgColor: String,
    darkBorderColor: String
  )

  def alert(style: AlertStyle) =
    val textContent   = "A simple info alert with an example link. Give it a click if you like."
    val _id           = ElementIdGenerator.generate("alert")
    val dismissTarget = s"#\$_id"
    div(
      id   := _id,
      cls  := s"flex items-center p-4 mb-4 text-\${style.color} border-t-4 border-\${style.borderColor} bg-\${style.bgColor} dark:text-\${style.darkTextColor} dark:bg-\${style.darkBgColor} dark:border-\${style.darkBorderColor}",
      role := "alert"
    )(
      raw("div")(
        """<svg class="flex-shrink-0 w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
             <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
           </svg>"""
      ),
      div(
        cls := "ms-3 text-sm font-medium"
      )(
        text(textContent),
        a(
          href := "#",
          cls  := "font-semibold underline hover:no-underline"
        )("example link"),
        text(". Give it a click if you like.")
      ),
      button(
        `type` := "button",
        cls    := s"ms-auto -mx-1.5 -my-1.5 bg-\${style.buttonBgColor} text-\${style.buttonTextColor} rounded-lg focus:ring-2 focus:ring-\${style.focusRingColor} p-1.5 hover:bg-\${style.hoverBgColor} inline-flex items-center justify-center h-8 w-8 dark:bg-gray-800 dark:text-\${style.darkTextColor} dark:hover:bg-gray-700",
        attr("data-dismiss-target", dismissTarget),
        attr("aria-label", "Close")
      )(
        span(cls := "sr-only")("Dismiss"),
        raw("div")(
          """<svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
               <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
             </svg>"""
        )
      )
    )

  // Define styles for alerts
  val blueStyle = AlertStyle(
    color = "blue-800",
    borderColor = "blue-300",
    bgColor = "blue-50",
    buttonBgColor = "blue-50",
    buttonTextColor = "blue-500",
    focusRingColor = "blue-400",
    hoverBgColor = "blue-200",
    darkTextColor = "blue-400",
    darkBgColor = "gray-800",
    darkBorderColor = "blue-800"
  )

  val redStyle = AlertStyle(
    color = "red-800",
    borderColor = "red-300",
    bgColor = "red-50",
    buttonBgColor = "red-50",
    buttonTextColor = "red-500",
    focusRingColor = "red-400",
    hoverBgColor = "red-200",
    darkTextColor = "red-400",
    darkBgColor = "gray-800",
    darkBorderColor = "red-800"
  )

  // Green alert style
  val greenStyle = AlertStyle(
    color = "green-800",
    borderColor = "green-300",
    bgColor = "green-50",
    buttonBgColor = "green-50",
    buttonTextColor = "green-500",
    focusRingColor = "green-400",
    hoverBgColor = "green-200",
    darkTextColor = "green-400",
    darkBgColor = "gray-800",
    darkBorderColor = "green-800"
  )

  // Yellow alert style
  val yellowStyle = AlertStyle(
    color = "yellow-800",
    borderColor = "yellow-300",
    bgColor = "yellow-50",
    buttonBgColor = "yellow-50",
    buttonTextColor = "yellow-500",
    focusRingColor = "yellow-400",
    hoverBgColor = "yellow-200",
    darkTextColor = "yellow-300",
    darkBgColor = "gray-800",
    darkBorderColor = "yellow-300"
  )

  // Gray alert style for dark theme
  val grayStyle = AlertStyle(
    color = "gray-800",
    borderColor = "gray-300",
    bgColor = "gray-50",
    buttonBgColor = "gray-50",
    buttonTextColor = "gray-500",
    focusRingColor = "gray-400",
    hoverBgColor = "gray-200",
    darkTextColor = "gray-300",
    darkBgColor = "gray-800",
    darkBorderColor = "gray-800"
  )

  val alert1 = alert(blueStyle)
  val alert2 = alert(redStyle)
  val alert3 = alert(greenStyle)
  val alert4 = alert(yellowStyle)
  val alert5 = alert(grayStyle)

  def apply() = div(cls := "max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-600")(
    alert1,
    alert2,
    alert3,
    alert4,
    alert5
  )
