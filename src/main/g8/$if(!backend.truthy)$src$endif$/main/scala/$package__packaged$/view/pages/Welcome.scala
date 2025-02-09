package $package$.view.pages

import tyrian.Html.*
import tyrian.Html
import $package$.model.Msg

object Welcome:

  def apply(message: String): Html[Msg] =
    val tyrianLink =
      a(
        href   := "https://tyrian.indigoengine.io",
        rel    := "noopener noreferrer",
        target := "_blank",
        cls    := "text-purple-700 dark:text-purple-900 hover:underline"
      )(
        "Tyrian"
      )
    val flowbiteLink = a(href := "https://flowbite.com", rel := "noopener noreferrer", target := "_blank", cls := "text-blue-700 hover:underline")(
      "Flowbite"
    )

    val title = h1(cls := "mb-2 text-4xl font-bold tracking-tight text-gray-900 dark:text-white")(
      text("Welcome to "),
      tyrianLink,
      text(" + "),
      flowbiteLink
    )

    div(cls := "flex flex-col items-center mb-8")(
      div(cls := "my-8")(title),
      div(
        cls := "max-w transition-transform duration-300 ease-in-out hover:-translate-y-2 bg-white border border-gray-200 rounded-lg shadow-xl dark:bg-gray-600"
      )(
        a(href := "https://github.com/linux-root/tyrian-flowbite.g8")(
          img(cls := "rounded-t-lg", src := "assets/images/image-1.jpg")
        ),
        div(cls := "p-5")(
          p(cls := "mb-3 font-normal text-gray-700 dark:text-gray-400")(
            p(cls := "mb-3 font-large text-gray-900 dark:text-gray-300")(
              text("Experience the power of"),
              span(cls := "text-red-400 font-semibold")(" purely functional programming "),
              text("with "),
              tyrianLink,
              text(" and "),
              flowbiteLink,
              text(" — it's absolutely "),
              span(cls := "text-green-600 font-bold")("AWESOME!")
            )
          )
        )
      ),
      ComponentsDemo()
    )
