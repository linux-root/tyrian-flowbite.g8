package com.example.tfwithbackend.view.pages

import tyrian.Html.*
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.view.components.Icons
import com.example.tfwithbackend.view.components.Icons.i

object BadgesView:
  def apply(): Html[Msg] =
    div(cls := "flex flex-col mt-8")(
      defaultBadges,
      largeBadges,
      pillBadges,
      linkBadges,
      badgesWithIcon,
      notificationBadges,
      buttonWithBadge
    )

  private def badges(spans: List[Html[Msg]]): Html[Msg] =
    div(cls := "flex flex-row mx-auto max-w-full p-6 my-4")(
      spans
    )

  private val defaultBadges =
    val spans = List(
      span(
        cls := "bg-blue-100 text-blue-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-blue-900 dark:text-blue-300"
      )("Default"),
      span(
        cls := "bg-gray-100 text-gray-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-gray-300"
      )("Dark"),
      span(
        cls := "bg-red-100 text-red-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-red-900 dark:text-red-300"
      )("Red"),
      span(
        cls := "bg-green-100 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300"
      )("Green"),
      span(
        cls := "bg-yellow-100 text-yellow-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-yellow-900 dark:text-yellow-300"
      )("Yellow"),
      span(
        cls := "bg-indigo-100 text-indigo-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-indigo-900 dark:text-indigo-300"
      )("Indigo"),
      span(
        cls := "bg-purple-100 text-purple-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-purple-900 dark:text-purple-300"
      )("Purple"),
      span(
        cls := "bg-pink-100 text-pink-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-pink-900 dark:text-pink-300"
      )("Pink")
    )
    badges(spans)

  private val largeBadges =
    val spans = List(
      span(
        cls := "bg-blue-100 text-blue-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-blue-900 dark:text-blue-300"
      )("Default"),
      span(
        cls := "bg-gray-100 text-gray-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-gray-300"
      )("Dark"),
      span(
        cls := "bg-red-100 text-red-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-red-900 dark:text-red-300"
      )("Red"),
      span(
        cls := "bg-green-100 text-green-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300"
      )("Green"),
      span(
        cls := "bg-yellow-100 text-yellow-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-yellow-900 dark:text-yellow-300"
      )("Yellow"),
      span(
        cls := "bg-indigo-100 text-indigo-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-indigo-900 dark:text-indigo-300"
      )("Indigo"),
      span(
        cls := "bg-purple-100 text-purple-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-purple-900 dark:text-purple-300"
      )("Purple"),
      span(
        cls := "bg-pink-100 text-pink-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-pink-900 dark:text-pink-300"
      )("Pink")
    )
    badges(spans)

  private val pillBadges =
    val spans = List(
      span(
        cls := "bg-blue-100 text-blue-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-blue-900 dark:text-blue-300"
      )("Default"),
      span(
        cls := "bg-gray-100 text-gray-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-gray-700 dark:text-gray-300"
      )("Dark"),
      span(
        cls := "bg-red-100 text-red-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-red-900 dark:text-red-300"
      )("Red"),
      span(
        cls := "bg-green-100 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-green-900 dark:text-green-300"
      )("Green"),
      span(
        cls := "bg-yellow-100 text-yellow-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-yellow-900 dark:text-yellow-300"
      )("Yellow"),
      span(
        cls := "bg-indigo-100 text-indigo-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-indigo-900 dark:text-indigo-300"
      )("Indigo"),
      span(
        cls := "bg-purple-100 text-purple-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-purple-900 dark:text-purple-300"
      )("Purple"),
      span(
        cls := "bg-pink-100 text-pink-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-pink-900 dark:text-pink-300"
      )("Pink")
    )
    badges(spans)

  private val linkBadges =
    val badgeLinks = List(
      a(
        cls := "bg-blue-100 hover:bg-blue-200 text-blue-800 text-xs font-semibold me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-blue-400 border border-blue-400 inline-flex items-center justify-center"
      )("Badge link"),
      a(
        cls := "bg-blue-100 hover:bg-blue-200 text-blue-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-blue-400 border border-blue-400 inline-flex items-center justify-center"
      )("Badge link")
    )
    badges(badgeLinks)

  private val badgesWithIcon =
    badges(
      List(
        span(
          cls := "bg-gray-100 text-gray-800 text-xs font-medium inline-flex items-center px-2.5 py-0.5 rounded me-2 dark:bg-gray-700 dark:text-gray-400 border border-gray-500"
        )(
          Icons.clock,
          text("3 days ago")
        ),
        span(
          cls := "bg-blue-100 text-blue-800 text-xs font-medium inline-flex items-center px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-blue-400 border border-blue-400"
        )(
          Icons.clock,
          text("2 minutes ago")
        )
      )
    )

  private val notificationBadges =
    badges(
      List(
        button(
          tpe := "button",
          cls := "relative inline-flex items-center p-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        )(
          i"""<svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 16">
                <path d="m10.036 8.278 9.258-7.79A1.979 1.979 0 0 0 18 0H2A1.987 1.987 0 0 0 .641.541l9.395 7.737Z"/>
                <path d="M11.241 9.817c-.36.275-.801.425-1.255.427-.428 0-.845-.138-1.187-.395L0 2.6V14a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V2.5l-8.759 7.317Z"/>
              </svg>""",
          span(cls := "sr-only")("Notifications"),
          div(
            cls := "absolute inline-flex items-center justify-center w-6 h-6 text-xs font-bold text-white bg-red-500 border-2 border-white rounded-full -top-2 -end-2"
          )("20")
        )
      )
    )

  private val buttonWithBadge =
    badges(
      button(
        tpe := "button",
        cls := "inline-flex items-center px-5 py-2.5 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
      )(
        text("Messages"),
        span(cls := "inline-flex items-center justify-center w-4 h-4 ms-2 text-xs font-semibold text-blue-800 bg-blue-200 rounded-full")("2")
      ) :: Nil
    )
