package com.example.tfwithbackend.view.pages

import tyrian.Html
import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.view.components.Icons
import tyrian.Attr
import javax.swing.Icon

object DatepickerView:

  def apply(): Html[Msg] =
    val pickers = List(datepickerInput, inlineDatepicker, dateRangePicker).map(pickerBlock)
    div(cls := "flex flex-col mt-8")(
      pickers
    )

  private def emptyAttr(name: String): Attr[Nothing] = attribute(name, "")

  private def pickerBlock(picker: Html[Msg]) =
    div(cls := "flex flex-row max-w-full p-6 my-4 bg-white dark:bg-gray-600 border border-gray-200 rounded-lg shadow")(
      picker
    )

  private val datepickerInput =
    div(
      cls := "relative max-w-sm"
    )(
      // Icon container
      div(
        cls := "absolute inset-y-0 start-0 flex items-center ps-3.5 pointer-events-none"
      )(
        Icons.calendar
      ),
      // Input field
      input(
        id := "default-datepicker",
        emptyAttr("datepicker"),
        `type`      := "text",
        cls         := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500",
        placeholder := "Select date"
      )
    )

  private val inlineDatepicker =
    div(cls := "flex flex-col gap-4")(
      h2(cls := "text-gray-800")("Inline datepicker"),
      div(
        id := "datepicker-inline",
        emptyAttr("inline-datepicker"),
        attr("data-date", "12/16/1969")
      )()
    )

  private val dateRangePicker =
    div(cls := "flex flex-col gap-4")(
      h2(cls := "text-gray-800")("Date range picker"),
      div(
        id  := "date-range-picker",
        cls := "flex items-center",
        emptyAttr("date-rangepicker")
      )(
        // Start Date Input
        div(cls := "relative")(
          div(cls := "absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none")(
            Icons.calendar
          ),
          input(
            id          := "datepicker-range-start",
            name        := "start",
            `type`      := "text",
            cls         := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500",
            placeholder := "Select date start"
          )
        ),

        // Separator
        span(cls := "mx-4 text-gray-500")("to"),

        // End Date Input
        div(cls := "relative")(
          div(cls := "absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none")(
            Icons.calendar
          ),
          input(
            id          := "datepicker-range-end",
            name        := "end",
            `type`      := "text",
            cls         := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500",
            placeholder := "Select date end"
          )
        )
      )
    )
