package com.example.tfwithbackend.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html.raw
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.util.ElementIdGenerator
import com.example.tfwithbackend.view.components.Icons.i

object AlertsView:
  case class AlertStyle(
    color: String,
    buttonTextColor: String,
    focusRingColor: String,
    darkTextColor: String
  )

  def alert(style: AlertStyle) =
    val textContent   = "A simple info alert with an example link. Give it a click if you like."
    val _id           = ElementIdGenerator.generate("alert")
    val dismissTarget = "#" + _id
    div(
      id   := _id,
      cls  := s"flex items-center shadow p-4 mb-4 bg-white text-\${style.color} border-t-4 border-gray-200 dark:text-\${style.darkTextColor} dark:bg-gray-700 dark:border-gray-400",
      role := "alert"
    )(
      i"""<svg class="flex-shrink-0 w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
             <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
           </svg>""",
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
        cls    := s"ms-auto -mx-1.5 -my-1.5 text-\${style.buttonTextColor} rounded-lg focus:ring-2 focus:ring-\${style.focusRingColor} p-1.5 hover:bg-gray-100 inline-flex items-center justify-center h-8 w-8 dark:bg-gray-700 dark:text-\${style.darkTextColor} dark:hover:bg-gray-900",
        attr("data-dismiss-target", dismissTarget),
        attr("aria-label", "Close")
      )(
        span(cls := "sr-only")("Dismiss"),
        i"""<svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
               <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
             </svg>"""
      )
    )

  // Define styles for alerts
  val blueStyle = AlertStyle(
    color = "blue-800",
    buttonTextColor = "blue-500",
    focusRingColor = "blue-400",
    darkTextColor = "blue-400"
  )

  val redStyle = AlertStyle(
    color = "red-800",
    buttonTextColor = "red-500",
    focusRingColor = "red-400",
    darkTextColor = "red-300"
  )

  // Green alert style
  val greenStyle = AlertStyle(
    color = "green-800",
    buttonTextColor = "green-500",
    focusRingColor = "green-400",
    darkTextColor = "green-300"
  )

  // Yellow alert style
  val yellowStyle = AlertStyle(
    color = "yellow-800",
    buttonTextColor = "yellow-500",
    focusRingColor = "yellow-400",
    darkTextColor = "yellow-300"
  )

  // Gray alert style for dark theme
  val grayStyle = AlertStyle(
    color = "gray-800",
    buttonTextColor = "gray-500",
    focusRingColor = "gray-400",
    darkTextColor = "gray-300"
  )

  val alert1 = alert(blueStyle)
  val alert2 = alert(redStyle)
  val alert3 = alert(greenStyle)
  val alert4 = alert(yellowStyle)
  val alert5 = alert(grayStyle)

  def apply() = div(cls := "flex flex-col space-around  max-w-full p-6")(
    alert1,
    alert2,
    alert3,
    alert4,
    alert5
  )
