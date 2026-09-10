package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html

import $package$.model.Msg

object CarouselView:

  private case class Slide(gradient: String, title: String, subtitle: String)

  // Gradient strings are spelled out in full so Tailwind picks them up.
  private val slides = List(
    Slide(
      "from-purple-600 to-indigo-600",
      "Purely functional frontend",
      "The Elm architecture in Scala 3 — model, update, view, and nothing you didn't expect."
    ),
    Slide(
      "from-sky-500 to-blue-600",
      "Type-safe views",
      "Your markup is checked by the compiler. Rename a field and the view won't build until it's right."
    ),
    Slide(
      "from-emerald-500 to-teal-600",
      "60+ Flowbite components",
      "Accordions, datepickers, carousels, toggles — interactive and styled out of the box."
    ),
    Slide(
      "from-orange-500 to-rose-500",
      "Powered by Scala.js",
      "Compile Scala straight to a fast, tree-shaken JavaScript bundle."
    ),
    Slide(
      "from-slate-700 to-gray-900",
      "Dark mode, built in",
      "One toggle in the model. Every component follows."
    )
  )

  def apply(): Html[Msg] =
    div(cls := "flex flex-col items-center")(
      div(
        id  := "feature-carousel",
        // `isolate` keeps Flowbite's z-30 slide stacking from painting over the fixed navbar on scroll
        cls := "relative isolate w-full max-w-3xl",
        attr("data-carousel", "slide")
      )(
        div(cls := "relative h-64 md:h-80 overflow-hidden rounded-lg shadow-xl")(
          slides.map(slideItem)
        ),
        indicators,
        control("data-carousel-prev", chevron("m14 8-4 4 4 4"), "Previous", "start-0"),
        control("data-carousel-next", chevron("m10 16 4-4-4-4"), "Next", "end-0")
      )
    )

  private def slideItem(s: Slide): Html[Msg] =
    div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
      div(
        cls := s"absolute inset-0 flex flex-col items-center justify-center text-center px-10 bg-linear-to-br \${s.gradient}"
      )(
        h2(cls := "text-2xl md:text-4xl font-bold text-white")(s.title),
        p(cls := "mt-3 max-w-lg text-sm md:text-base text-white/80")(s.subtitle)
      )
    )

  private val indicators: Html[Msg] =
    div(cls := "absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3")(
      slides.zipWithIndex.map { case (_, idx) =>
        button(
          `type` := "button",
          cls    := "w-3 h-3 rounded-full bg-white/50 hover:bg-white transition-colors",
          attr("aria-current", if idx == 0 then "true" else "false"),
          attr("aria-label", s"Slide \${idx + 1}"),
          attr("data-carousel-slide-to", idx.toString)
        )()
      }
    )

  private def control(dataAttr: String, icon: Html[Nothing], label: String, side: String): Html[Msg] =
    button(
      `type` := "button",
      cls    := s"absolute top-0 \$side z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none",
      attr(dataAttr, "")
    )(
      span(
        cls := "inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 group-hover:bg-white/50 group-focus:ring-4 group-focus:ring-white/70 group-focus:outline-none"
      )(
        icon,
        span(cls := "sr-only")(label)
      )
    )

  private def chevron(path: String): Html[Nothing] =
    Html.raw("div")(
      s"""<svg class="w-5 h-5 text-white rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="\$path"/></svg>"""
    )
