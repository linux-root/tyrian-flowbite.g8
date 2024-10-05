package $organization$.view

import tyrian.Html.*
import tyrian.Html
import $organization$.model.Msg

object Welcome:

  def apply(message: String): Html[Msg] =
    val title = "Welcome to Tyrian + Flowbite"

    div(cls := "max-w bg-white border border-gray-200 rounded-lg shadow-xl dark:bg-gray-800 dark:border-gray-700")(
      a(href := "#")(
        img(cls := "rounded-t-lg", src := "https://flowbite.com/docs/images/blog/image-1.jpg")
      ),
      div(cls := "p-5")(
        a(href := "#")(
          h5(cls := "mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white")(title)
        ),
        p(cls := "mb-3 font-normal text-gray-700 dark:text-gray-400")(
          message
        ),
        Tooltip("Demo interactive UI components based on the Flowbite API and data attributes interface")(
          button(
            cls := "btn inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          )(
            text("Enter")
          )
        )
      )
    )
