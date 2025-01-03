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

  def apply(content: Html[Msg]): Html[Msg] =
    div(cls := "flex flex-col min-h-screen bg-gray-100")(
      main(cls := "flex-grow container mx-auto p-4 bg-gray-100")(
        content
      ),
      theFooter
    )
