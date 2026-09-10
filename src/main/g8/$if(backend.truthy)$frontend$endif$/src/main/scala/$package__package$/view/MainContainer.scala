package $package$.view

import tyrian.Html.*
import tyrian.syntax.*
import tyrian.Html
import tyrian.Html.{attribute => attr}
import $package$.model.Msg
import $package$.page.Page
import $package$.view.components.Icons
import tyrian.Elem

object MainContainer:

  private val navLinks: List[Html[Msg]] =
    Page.values.toList.filterNot(p => p == Page.Home || p == Page.Login).map { p =>
      a(
        href := p.path,
        cls  := "text-sm font-medium text-white/80 hover:text-white hover:underline"
      )(p.title)
    }

  def navbar(isDark: Boolean, isLoggedIn: Boolean): Html[Msg] =
    nav(cls := "bg-purple-600 dark:bg-purple-950 sticky top-0 z-20 w-full border-b border-purple-700 dark:border-gray-800")(
      div(cls := "flex flex-wrap items-center justify-between gap-x-6 gap-y-3 p-4")(
        a(href := "/", cls := "flex items-center shrink-0")(
          img(src := "/assets/images/tyrian-horizontal.svg", cls := "h-10", alt := "Tyrian home")
        ),
        div(cls := "flex flex-wrap items-center gap-x-4 gap-y-1")(navLinks),
        div(cls := "flex items-center")(
          darkModeSwitchButton(isDark),
          if isLoggedIn then controlButton(Msg.Logout, "Log out", Icons.logout) else div()
        )
      )
    )

  private val theFooter =
    footer(cls := "w-full bg-purple-600 dark:bg-purple-950 border-t border-purple-700 dark:border-gray-800 text-white py-4")(
      div(cls := "container mx-auto text-center")(
        p(cls := "text-sm")(
          "Created by Scala with Love © 2025. All rights reserved."
        ),
        br(),
        a(
          href := "https://github.com/linux-root/tyrian-flowbite.g8",
          attr("aria-label", "GitHub repository"),
          cls  := "text-blue-300 hover:underline ml-2 flex items-center justify-center space-x-1 inline-flex"
        )(Icons.github)
      )
    )

  private def controlButton(clickMsg: Msg, label: String, child: Elem[Msg]) =
    val dark   = "dark:text-white dark:hover:bg-purple-800 dark:focus:ring-purple-200"
    val normal = "text-white hover:bg-purple-700 focus:ring-purple-100"
    button(
      onClick(clickMsg),
      attr("aria-label", label),
      cls := s"transition-transform duration-300 ease-in-out hover:scale-105 p-3 rounded-full focus:outline-none \$normal \$dark"
    )(child)

  private def darkModeSwitchButton(isDark: Boolean) =
    val icon = if isDark then Icons.sun else Icons.moon
    controlButton(Msg.ToggleDarkMode, if isDark then "Switch to light mode" else "Switch to dark mode", icon)

  def apply(content: Html[Msg], isDark: Boolean, isLoggedIn: Boolean): Html[Msg] =
    div(cls := (if isDark then "dark" else ""))(
      div(cls := "flex flex-col min-h-screen bg-gray-200 dark:bg-gray-900")(
        navbar(isDark, isLoggedIn),
        main(cls := "flex-1 w-full")(content),
        theFooter
      )
    )
