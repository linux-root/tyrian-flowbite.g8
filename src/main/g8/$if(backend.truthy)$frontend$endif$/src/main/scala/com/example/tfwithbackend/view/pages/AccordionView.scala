package com.example.tfwithbackend.view.pages

import tyrian.Html
import com.example.tfwithbackend.model.Msg
import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import com.example.tfwithbackend.view.components.Icons

object AccordionView:
  def apply(): Html[Msg] =
    div(
      id := "accordion-collapse",
      attr("data-accordion", "open")
    )(
      // Accordion Item 1
      h2(
        id := "accordion-collapse-heading-1"
      )(
        button(
          `type` := "button",
          cls    := "flex items-center justify-between w-full p-5 font-medium rtl:text-right text-gray-500 border border-b-0 border-gray-200 rounded-t-xl focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:border-gray-700 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-800 gap-3",
          attr("data-accordion-target", "#accordion-collapse-body-1"),
          attr("aria-expanded", "true"),
          attr("aria-controls", "accordion-collapse-body-1")
        )(
          span("What is Flowbite?"),
          Html.raw("div")(
            """<svg class="w-4 h-4 shrink-0 -me-0.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7.529 7.988a2.502 2.502 0 0 1 5 .191A2.441 2.441 0 0 1 10 10.582V12m-.01 3.008H10M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
      </svg>"""
          )
        )
      ),

      // Accordion Body 1
      div(
        id  := "accordion-collapse-body-1",
        cls := "hidden",
        attr("aria-labelledby", "accordion-collapse-heading-1")
      )(
        div(
          cls := "p-5 border border-b-0 border-gray-200 dark:border-gray-700 dark:bg-gray-900"
        )(
          p(cls := "mb-2 text-gray-500 dark:text-gray-400")(
            "Flowbite is an open-source library of interactive components built on top of Tailwind CSS including buttons, dropdowns, modals, navbars, and more."
          ),
          p(cls := "text-gray-500 dark:text-gray-400")(
            text("Check out this guide to learn how to "),
            a(
              href := "/docs/getting-started/introduction/",
              cls  := "text-blue-600 dark:text-blue-500 hover:underline"
            )("get started"),
            text(" and start developing websites even faster with components on top of Tailwind CSS.")
          )
        )
      ),

      // // Accordion Item 2
      h2(
        id := "accordion-collapse-heading-2"
      )(
        button(
          `type` := "button",
          cls    := "flex items-center justify-between w-full p-5 font-medium rtl:text-right text-gray-500 border border-b-0 border-gray-200 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:border-gray-700 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-800 gap-3",
          attr("data-accordion-target", "#accordion-collapse-body-2"),
          attr("aria-expanded", "false"),
          attr("aria-controls", "accordion-collapse-body-2")
        )(
          span("Is there a Figma file available?"),
          Html.raw("div")(
            """<svg class="w-3 h-3 rotate-180 shrink-0"  aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5 5 1 1 5"/>
                  </svg>"""
          )
        )
      ),

      // Accordion Body 2
      div(
        id  := "accordion-collapse-body-2",
        cls := "hidden",
        attr("aria-labelledby", "accordion-collapse-heading-2")
      )(
        div(
          cls := "p-5 border border-b-0 border-gray-200 dark:border-gray-700"
        )(
          p(cls := "mb-2 text-gray-500 dark:text-gray-400")(
            "Flowbite is first conceptualized and designed using the Figma software so everything you see in the library has a design equivalent in our Figma file."
          ),
          p(cls := "text-gray-500 dark:text-gray-400")(
            text("Check out the "),
            a(
              href := "https://flowbite.com/figma/",
              cls  := "text-blue-600 dark:text-blue-500 hover:underline"
            )("Figma design system"),
            text(" based on the utility classes from Tailwind CSS and components from Flowbite.")
          )
        )
      ),

      // Accordion Item 3
      h2(
        id := "accordion-collapse-heading-3"
      )(
        button(
          `type` := "button",
          cls    := "flex items-center justify-between w-full p-5 font-medium rtl:text-right text-gray-500 border border-b-0 border-gray-200 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:border-gray-700 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-800 gap-3",
          attr("data-accordion-target", "#accordion-collapse-body-3"),
          attr("aria-expanded", "false"),
          attr("aria-controls", "accordion-collapse-body-3")
        )(
          span("What are the differences between Flowbite and Tailwind UI?"),
          Html.raw("div")(
            """<svg class="w-3 h-3 rotate-180 shrink-0"  aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5 5 1 1 5"/>
                  </svg>"""
          )
        )
      ),

      // Accordion Body 3
      div(
        id  := "accordion-collapse-body-3",
        cls := "hidden",
        attr("aria-labelledby", "accordion-collapse-heading-3")
      )(
        div(
          cls := "p-5 border border-t-0 border-gray-200 dark:border-gray-700"
        )(
          p(cls := "mb-2 text-gray-500 dark:text-gray-400")(
            "The main difference is that the core components from Flowbite are open source under the MIT license, whereas Tailwind UI is a paid product. Another difference is that Flowbite relies on smaller and standalone components, whereas Tailwind UI offers sections of pages."
          ),
          p(cls := "mb-2 text-gray-500 dark:text-gray-400")(
            "However, we actually recommend using both Flowbite, Flowbite Pro, and even Tailwind UI as there is no technical reason stopping you from using the best of two worlds."
          ),
          p(cls := "mb-2 text-gray-500 dark:text-gray-400")(
            "Learn more about these technologies:"
          ),
          ul(cls := "ps-5 text-gray-500 list-disc dark:text-gray-400")(
            li(
              a(
                href := "https://flowbite.com/pro/",
                cls  := "text-blue-600 dark:text-blue-500 hover:underline"
              )("Flowbite Pro")
            ),
            li(
              a(
                href := "https://tailwindui.com/",
                rel  := "nofollow",
                cls  := "text-blue-600 dark:text-blue-500 hover:underline"
              )("Tailwind UI")
            )
          )
        )
      )
    )
