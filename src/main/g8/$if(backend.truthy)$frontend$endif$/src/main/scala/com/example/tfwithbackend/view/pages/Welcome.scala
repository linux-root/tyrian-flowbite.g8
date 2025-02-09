package com.example.tfwithbackend.view.pages

import tyrian.Html.*
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.model.Model.HomeState
import com.example.tfwithbackend.view.components.Icons

object Welcome:

  def apply(state: HomeState): Html[Msg] =
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
        div(cls := "flex items-space-between items-center w-[60rem] h-[12rem] p-5 space-y-6 text-center")(
          div(cls := "mb-3 font-large text-gray-900 dark:text-gray-300")(
            div(cls := "flex flex-col space-y-4 w-[50rem]")(
              div(cls := "text-green-400 text-2xl font-semibold")("Message from server"),
              div(cls := "font-mono text-sm italic text-gray-600 dark:text-white break-words whitespace-normal")(
                if state.serverMessage.isEmpty then "Empty...is Server running ?" else state.serverMessage.get
              )
            )
          ),
          button(
            cls := "flex border border-gray-200 bg-green-400 text-white justify-center items-center w-12 h-12 rounded-full hover:animate-spin mx-6 ml-auto transition-all duration-300 hover:bg-green-500 hover:text-white",
            onClick(Msg.NextBackendMessage)
          )(
            Icons.refresh
          )
        )
      ),
      ComponentsDemo()
    )
