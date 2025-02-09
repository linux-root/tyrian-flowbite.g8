package com.example.tfwithbackend.view

import tyrian.Html.*
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.util.ElementIdGenerator

object Tooltip:

  def apply(_text: String)(elem: Html[Msg], _class: String = ""): Html[Msg] =
    val _id = ElementIdGenerator.generate("tooltip")
    div(
      div(cls := _class, attribute("data-tooltip-target", _id))(
        elem
      ),
      div(
        id   := _id,
        role := "tooltip",
        cls  := "absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
      )(
        text(_text),
        div(cls := "tooltip-arrow", attribute("data-popper-arrow", "true"))()
      )
    )
