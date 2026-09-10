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
        cls    := "font-mono text-purple-600 dark:text-purple-400 hover:underline"
      )(
        "Tyrian"
      )
    val flowbiteLink = a(
      href   := "https://flowbite.com",
      rel    := "noopener noreferrer",
      target := "_blank",
      cls    := "text-blue-700 dark:text-blue-400 hover:underline"
    )(
      "Flowbite"
    )

    val title = h1(cls := "mb-2 text-4xl font-bold tracking-tight text-gray-900 dark:text-white")(
      tyrianLink,
      span(cls := "mx-2 text-gray-400 dark:text-gray-500")("<+>"),
      flowbiteLink
    )

    div(cls := "flex flex-col items-center px-4 py-10")(
      div(cls := "my-8")(title),
      div(
        cls := "max-w-xl transition-transform duration-300 ease-in-out hover:-translate-y-2 bg-white border border-gray-200 rounded-lg shadow-xl dark:bg-gray-800 dark:border-gray-700"
      )(
        div(cls := "p-5")(
          p(cls := "font-medium text-gray-900 dark:text-gray-200")(
            text("Experience the power of"),
            span(cls := "text-red-500 dark:text-red-400 font-semibold")(" purely functional programming "),
            text("with "),
            tyrianLink,
            text(" and "),
            flowbiteLink,
            text(" — it's absolutely "),
            span(cls := "text-green-600 dark:text-green-400 font-bold")("AWESOME!")
          )
        )
      ),
      ComponentsDemo()
    )
