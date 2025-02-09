package com.example.tfwithbackend.view

import tyrian.Html.*
import tyrian.syntax.*
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.view.components.Icons
import tyrian.Elem
import javax.swing.Icon

object MainContainer:

  def navbar(isDark: Boolean, isLoggedIn: Boolean): Html[Msg] =
    nav(cls := "bg-purple-600 dark:bg-purple-900 fixed w-full z-20 top-0 start-0 border-b border-gray-200 dark:border-gray-700")(
      div(cls := "flex flex-wrap items-center justify-between p-4")(
        a(href := "/", cls := "flex items-center")(
          img(src := "/assets/images/tyrian-horizontal.svg", cls := "h-12")
        ),
        div(cls := "flex")(
          darkModeSwitchButton(isDark),
          if isLoggedIn then controlButton(Msg.Logout, Icons.logout) else div()
        )
      )
    )

  private val theFooter =
    footer(cls := "w-full bg-purple-600 dark:bg-purple-900 border-t dark:border-gray-700 text-white py-4")(
      div(cls := "container mx-auto text-center")(
        p(cls := "text-sm")(
          "Created by Scala with Love © 2025. All rights reserved."
        ),
        br(),
        a(
          href := "https://github.com/linux-root/tyrian-flowbite.g8",
          cls  := "text-blue-400 hover:underline ml-2 flex items-center justify-center space-x-1 inline-flex"
        )(Icons.github)
      )
    )

  private def controlButton(clickMsg: Msg, child: Elem[Msg]) =
    val dark   = "dark:text-white dark:hover:bg-purple-700 dark:focus:ring-purple-200"
    val normal = "text-white hover:bg-purple-600 focus:ring-purple-100"
    button(
      onClick(clickMsg),
      cls := s"transition-transform duration-300 ease-in-out hover:scale-105 p-4 me-2 mb-2 rounded-full focus:outline-none \$normal \$dark"
    )(child)

  private def darkModeSwitchButton(isDark: Boolean) =
    val icon = if isDark then Icons.sun else Icons.moon
    controlButton(Msg.ToggleDarkMode, icon)

  def apply(content: Html[Msg], isDark: Boolean, isLoggedIn: Boolean): Html[Msg] =
    div(cls := (if isDark then "dark" else "not-dark-mode"))(
      navbar(isDark, isLoggedIn),
      main(cls := "flex flex-col min-h-screen mt-20 bg-gray-200 dark:bg-gray-900")(content),
      theFooter
    )
