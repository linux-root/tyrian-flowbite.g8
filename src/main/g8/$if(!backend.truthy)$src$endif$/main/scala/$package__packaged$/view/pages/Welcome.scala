package $package$.view.pages

import tyrian.Html.*
import tyrian.Html
import $package$.model.Msg

object Welcome:

  def apply(): Html[Msg] =
    val tyrianLink =
      a(
        href   := "https://tyrian.indigoengine.io",
        rel    := "noopener noreferrer",
        target := "_blank",
        cls    := "font-mono text-purple-600 dark:text-purple-800 hover:underline"
      )(
        "Tyrian"
      )
    val flowbiteLink = a(href := "https://flowbite.com", rel := "noopener noreferrer", target := "_blank", cls := "text-blue-700 hover:underline")(
      "Flowbite"
    )

    val title = h1(cls := "mb-2 text-4xl font-bold tracking-tight text-gray-900 dark:text-white")(
      tyrianLink,
      text(" <+> "),
      flowbiteLink
    )

    div(cls := "flex flex-col items-center mb-8")(
      div(cls := "my-8")(title),
      div(
        cls := "max-w transition-transform duration-300 ease-in-out hover:-translate-y-2 bg-white border border-gray-200 rounded-lg shadow-xl dark:bg-gray-600"
      )(
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
