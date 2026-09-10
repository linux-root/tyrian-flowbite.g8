package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html
import $package$.model.Msg
import $package$.model.Model.HomeState
import $package$.view.components.Icons

object Welcome:

  def apply(state: HomeState): Html[Msg] =
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

    val serverMessage =
      if state.serverMessage.isEmpty then "Empty... is the server running?"
      else state.serverMessage.get

    div(cls := "flex flex-col items-center px-4 py-10")(
      div(cls := "my-8")(title),
      div(
        cls := "w-full max-w-xl transition-transform duration-300 ease-in-out hover:-translate-y-2 bg-white border border-gray-200 rounded-lg shadow-xl dark:bg-gray-800 dark:border-gray-700"
      )(
        div(cls := "flex items-center gap-4 p-5")(
          div(cls := "flex flex-col gap-2 grow text-center sm:text-left")(
            div(cls := "text-green-500 dark:text-green-400 text-xl font-semibold")("Message from server"),
            div(cls := "font-mono text-sm italic text-gray-600 dark:text-gray-300 break-words whitespace-normal")(serverMessage)
          ),
          button(
            cls := "shrink-0 flex justify-center items-center w-12 h-12 rounded-full border border-gray-200 dark:border-gray-600 bg-green-500 text-white transition-all duration-300 hover:bg-green-600 hover:animate-spin",
            attr("aria-label", "Fetch a new message from the server"),
            onClick(Msg.NextBackendMessage)
          )(
            Icons.refresh
          )
        )
      ),
      ComponentsDemo()
    )
