package com.example.tfwithbackend.view.pages

import tyrian.Html
import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.view.components.Icons

object ToggleView:

  def apply(): Html[Msg] =
    val toggles = List(default, checkedToggleSwitch, disabledToggleSwitches, colorfulToggleSwitches, toggleSize).map(toggleBlock)
    div(cls := "flex flex-col mt-8")(
      toggles
    )

  private def toggleBlock(toggle: Html[Msg]) =
    div(cls := "flex flex-row max-w-full p-6 my-4 bg-white border border-gray-200 dark:bg-gray-600 rounded-lg shadow")(
      toggle
    )

  private val default =
    label(
      cls := "inline-flex items-center cursor-pointer"
    )(
      input(
        `type` := "checkbox",
        value  := "",
        cls    := "sr-only peer"
      ),
      div(
        cls := "relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
      )(),
      span(
        cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
      )("Toggle me")
    )

  val checkedToggleSwitch: Html[Msg] =
    label(
      cls := "inline-flex items-center cursor-pointer"
    )(
      input(
        `type`  := "checkbox",
        value   := "",
        cls     := "sr-only peer",
        checked := true // Mark the checkbox as checked
      ),
      div(
        cls := "relative w-11 h-6 bg-gray-200 rounded-full peer peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
      )(),
      span(
        cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
      )("Checked toggle")
    )
  val disabledToggleSwitches: Html[Msg] =
    div(
      cls := "space-y-4" // Optional: Adds spacing between the switches
    )(
      // First toggle switch (disabled, unchekcked)
      label(
        cls := "inline-flex items-center mb-5 cursor-pointer"
      )(
        input(
          `type` := "checkbox",
          value  := "",
          cls    := "sr-only peer",
          attr("disabled", "true")
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-400 dark:text-gray-500"
        )("Disabled toggle")
      ),
      // Second toggle switch (disabled, checked)
      label(
        cls := "inline-flex items-center cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true,
          attr("disabled", "true")
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-400 dark:text-gray-500"
        )("Disabled checked")
      )
    )

  val colorfulToggleSwitches: Html[Msg] =
    div(
      cls := "space-y-4" // Optional: Adds spacing between the switches
    )(
      // Red switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer peer-focus:ring-4 peer-focus:ring-red-300 dark:peer-focus:ring-red-800 dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-red-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Red")
      ),
      // Green switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-green-300 dark:peer-focus:ring-green-800 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-green-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Green")
      ),
      // Purple switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-purple-300 dark:peer-focus:ring-purple-800 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-purple-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Purple")
      ),
      // Yellow switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-yellow-300 dark:peer-focus:ring-yellow-800 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-yellow-400"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Yellow")
      ),
      // Teal switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-teal-300 dark:peer-focus:ring-teal-800 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-teal-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Teal")
      ),
      // Orange switch
      label(
        cls := "inline-flex items-center me-5 cursor-pointer"
      )(
        input(
          `type`  := "checkbox",
          value   := "",
          cls     := "sr-only peer",
          checked := true
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-orange-300 dark:peer-focus:ring-orange-800 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-orange-500"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Orange")
      )
    )

  val toggleSize: Html[Msg] =
    div(
      cls := "space-y-4" // Optional spacing between switches
    )(
      // Small toggle
      label(
        cls := "inline-flex items-center mb-5 cursor-pointer"
      )(
        input(
          `type` := "checkbox",
          value  := "",
          cls    := "sr-only peer"
        ),
        div(
          cls := "relative w-9 h-5 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Small toggle")
      ),
      // Default toggle
      label(
        cls := "inline-flex items-center mb-5 cursor-pointer"
      )(
        input(
          `type` := "checkbox",
          value  := "",
          cls    := "sr-only peer"
        ),
        div(
          cls := "relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Default toggle")
      ),
      // Large toggle
      label(
        cls := "inline-flex items-center cursor-pointer"
      )(
        input(
          `type` := "checkbox",
          value  := "",
          cls    := "sr-only peer"
        ),
        div(
          cls := "relative w-14 h-7 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[4px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-6 after:w-6 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
        )(),
        span(
          cls := "ms-3 text-sm font-medium text-gray-900 dark:text-gray-300"
        )("Large toggle")
      )
    )
