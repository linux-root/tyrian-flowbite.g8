package $package$.view

import tyrian.Html.*
import tyrian.Html
import $package$.model.Msg
import $package$.view.components.Icons

object MainContainer:

  private val theFooter =
    footer(cls := "bg-purple-500 text-white py-4 rounded shadow-lg m-4 ")(
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

  private def darkModeSwitchButton(isDark: Boolean) =
    val dark   = "dark:text-white dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800"
    val normal = "text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300"
    button(
      onClick(Msg.ToggleDarkMode),
      cls := s"fixed top-8 right-8 text-sm p-4 me-2 mb-2 font-medium rounded-full focus:outline-none \$normal \$dark"
    )(if isDark then Icons.sun else Icons.moon)

  def apply(content: Html[Msg], isDark: Boolean): Html[Msg] =
    div(cls := (if isDark then "dark" else "not-dark-mode"))(
      darkModeSwitchButton(isDark),
      div(cls := "flex flex-col min-h-screen bg-gray-100 dark:bg-gray-400")(
        main(cls := "flex-grow container mx-auto p-4 bg-gray-100 dark:bg-gray-400")(
          content
        ),
        theFooter
      )
    )
