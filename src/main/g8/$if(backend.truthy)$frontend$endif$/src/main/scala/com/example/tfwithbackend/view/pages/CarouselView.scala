package com.example.tfwithbackend.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.view.components.Icons

object CarouselView:
  def apply(): Html[Msg] =
    div(cls := "flex items-center justify-center h-screen")(
      carousel
    )

  private val carousel: Html[Msg] =
    div(id := "default-carousel", cls := "relative w-full", attr("data-carousel", "slide"))(
      // Carousel wrapper
      div(cls := "relative h-56 overflow-hidden rounded-lg md:h-96")(
        // Item 1
        div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
          img(
            src := "/assets/images/carousel-1.svg",
            cls := "absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2",
            alt := "..."
          )
        ),
        // Item 2
        div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
          img(
            src := "/assets/images/carousel-2.svg",
            cls := "absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2",
            alt := "..."
          )
        ),
        // Item 3
        div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
          img(
            src := "/assets/images/carousel-3.svg",
            cls := "absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2",
            alt := "..."
          )
        ),
        // Item 4
        div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
          img(
            src := "/assets/images/carousel-4.svg",
            cls := "absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2",
            alt := "..."
          )
        ),
        // Item 5
        div(cls := "hidden duration-700 ease-in-out", attr("data-carousel-item", ""))(
          img(
            src := "/assets/images/carousel-5.svg",
            cls := "absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2",
            alt := "..."
          )
        )
      ),
      // Slider indicators
      div(cls := "absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3 rtl:space-x-reverse")(
        button(cls := "w-3 h-3 rounded-full", attr("aria-current", "true"), attr("aria-label", "Slide 1"), attr("data-carousel-slide-to", "0")),
        button(cls := "w-3 h-3 rounded-full", attr("aria-current", "false"), attr("aria-label", "Slide 2"), attr("data-carousel-slide-to", "1")),
        button(cls := "w-3 h-3 rounded-full", attr("aria-current", "false"), attr("aria-label", "Slide 3"), attr("data-carousel-slide-to", "2")),
        button(cls := "w-3 h-3 rounded-full", attr("aria-current", "false"), attr("aria-label", "Slide 4"), attr("data-carousel-slide-to", "3")),
        button(cls := "w-3 h-3 rounded-full", attr("aria-current", "false"), attr("aria-label", "Slide 5"), attr("data-carousel-slide-to", "4"))
      ),
      // Slider controls
      button(
        cls := "absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none",
        attr("data-carousel-prev", "")
      )(
        span(
          cls := "inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none"
        )(
          Icons.chevronLeft,
          span(cls := "sr-only")("Previous")
        )
      ),
      button(
        cls := "absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none",
        attr("data-carousel-next", "")
      )(
        span(
          cls := "inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none"
        )(
          Icons.chevronRight,
          span(cls := "sr-only")("Next")
        )
      )
    )
