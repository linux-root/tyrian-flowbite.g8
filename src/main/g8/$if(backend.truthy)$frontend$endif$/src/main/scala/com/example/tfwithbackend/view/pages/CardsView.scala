package com.example.tfwithbackend.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.Html
import com.example.tfwithbackend.model.Msg

object CardsView:
  def apply(): Html[Msg] =
    div(cls := "flex flex-col mt-8 mx-auto items-center gap-8")(
      functionalProgrammingLink,
      userProfile,
      formInput,
      latestCustomers,
      functionalProgrammingTestimonials
    )

  private val functionalProgrammingLink = a(
    cls := "flex flex-col items-center bg-white border border-gray-200 rounded-lg shadow md:flex-row md:max-w-xl hover:bg-gray-100 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-700"
  )(
    img(
      cls := "object-cover w-full rounded-t-lg h-96 md:h-auto md:w-48 md:rounded-none md:rounded-s-lg",
      src := "/assets/images/cats.png",
      alt := ""
    ),
    div(
      cls := "flex flex-col justify-between p-4 leading-normal"
    )(
      h5(
        cls := "mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white"
      )("Functional Programming"),
      p(
        cls := "mb-3 font-normal text-gray-700 dark:text-gray-400"
      )("Explore the principles, techniques, and applications of functional programming for building robust software solutions.")
    )
  )

  private val userProfile =
    div(
      cls := "w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
    )(
      div(
        cls := "flex justify-end px-4 pt-4"
      )(
        button(
          id := "dropdownButton",
          attr("data-dropdown-toggle", "dropdown"),
          cls := "inline-block text-gray-500 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-700 focus:ring-4 focus:outline-none focus:ring-gray-200 dark:focus:ring-gray-700 rounded-lg text-sm p-1.5",
          tpe := "button"
        )(
          span(cls := "sr-only")("Open dropdown"),
          Html.raw("div")(
            """<svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 16 3">
              <path d="M2 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3Zm6.041 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM14 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3Z"/>
              </svg>"""
          )
        ),
        div(
          id  := "dropdown",
          cls := "z-10 hidden text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700"
        )(
          ul(
            cls := "py-2",
            attr("aria-labelledby", "dropdownButton")
          )(
            li(
              a(
                href := "#",
                cls  := "block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
              )("Edit")
            ),
            li(
              a(
                href := "#",
                cls  := "block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
              )("Export Data")
            ),
            li(
              a(
                href := "#",
                cls  := "block px-4 py-2 text-sm text-red-600 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
              )("Delete")
            )
          )
        )
      ),
      div(
        cls := "flex flex-col items-center pb-10"
      )(
        img(
          cls := "w-24 h-24 mb-3 rounded-full shadow-lg",
          src := "/assets/images/cat-avt2.jpg",
          alt := "Bonnie image"
        ),
        h5(
          cls := "mb-1 text-xl font-medium text-gray-900 dark:text-white"
        )("Watson Dinh"),
        span(
          cls := "text-sm text-gray-500 dark:text-gray-400"
        )("Flatmapper"),
        div(
          cls := "flex mt-4 md:mt-6"
        )(
          a(
            href := "https://www.linkedin.com/in/user777",
            cls  := "inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          )("Add friend"),
          a(
            cls := "py-2 px-4 ms-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
          )("Message")
        )
      )
    )

  private val formInput =
    div(cls := "w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700")(
      form(cls := "space-y-6", attr("prop", "#"))(
        h5(cls := "text-xl font-medium text-gray-900 dark:text-white")("Sign in to our platform"),
        div()(
          label(cls := "block mb-2 text-sm font-medium text-gray-900 dark:text-white", attr("prop", "email"))("Your email"),
          input(
            cls := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white",
            attr("prop", "email"),
            id          := "email",
            name        := "email",
            placeholder := "name@company.com",
            attr("required", "true")
          )
        ),
        div()(
          label(cls := "block mb-2 text-sm font-medium text-gray-900 dark:text-white", attr("prop", "password"))("Your password"),
          input(
            cls := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white",
            attr("prop", "password"),
            id          := "password",
            name        := "password",
            placeholder := "••••••••",
            attr("required", "true")
          )
        ),
        div(cls := "flex items-start")(
          div(cls := "flex items-start")(
            div(cls := "flex items-center h-5")(
              input(
                tpe := "checkbox",
                cls := "w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800",
                attr("prop", "checkbox"),
                id    := "remember",
                value := "",
                attr("required", "true")
              )
            ),
            label(cls := "ms-2 text-sm font-medium text-gray-900 dark:text-gray-300", attr("prop", "remember"))("Remember me")
          ),
          a(cls := "ms-auto text-sm text-blue-700 hover:underline dark:text-blue-500", href := "#")("Lost Password?")
        ),
        button(
          cls := "w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800",
          attr("prop", "submit")
        )("Login to your account"),
        div(cls := "text-sm font-medium text-gray-500 dark:text-gray-300")(
          text("Not registered? "),
          a(cls := "text-blue-700 hover:underline dark:text-blue-500", href := "#")("Create account")
        )
      )
    )

  private val latestCustomers: Html[Msg] = div(
    cls := "w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-8 dark:bg-gray-800 dark:border-gray-700"
  )(
    div(
      cls := "flex items-center justify-between mb-4"
    )(
      h5(
        cls := "text-xl font-bold leading-none text-gray-900 dark:text-white"
      )("Latest Customers"),
      a(
        href := "#",
        cls  := "text-sm font-medium text-blue-600 hover:underline dark:text-blue-500"
      )("View all")
    ),
    div(
      cls := "flow-root"
    )(
      ul(
        role := "list",
        cls  := "divide-y divide-gray-200 dark:divide-gray-700"
      )(
        li(
          cls := "py-3 sm:py-4"
        )(
          div(
            cls := "flex items-center"
          )(
            div(
              cls := "flex-shrink-0"
            )(
              img(
                cls := "w-8 h-8 rounded-full",
                src := "/assets/images/cat-avt1.webp",
                alt := "Neil image"
              )
            ),
            div(
              cls := "flex-1 min-w-0 ms-4"
            )(
              p(
                cls := "text-sm font-medium text-gray-900 truncate dark:text-white"
              )("Neil Sims"),
              p(
                cls := "text-sm text-gray-500 truncate dark:text-gray-400"
              )("email@windster.com")
            ),
            div(
              cls := "inline-flex items-center text-base font-semibold text-gray-900 dark:text-white"
            )("320 BTC")
          )
        ),
        li(
          cls := "py-3 sm:py-4"
        )(
          div(
            cls := "flex items-center"
          )(
            div(
              cls := "flex-shrink-0"
            )(
              img(
                cls := "w-8 h-8 rounded-full",
                src := "/assets/images/cat-avt2.png",
                alt := "Bonnie image"
              )
            ),
            div(
              cls := "flex-1 min-w-0 ms-4"
            )(
              p(
                cls := "text-sm font-medium text-gray-900 truncate dark:text-white"
              )("Bonnie Green"),
              p(
                cls := "text-sm text-gray-500 truncate dark:text-gray-400"
              )("email@windster.com")
            ),
            div(
              cls := "inline-flex items-center text-base font-semibold text-gray-900 dark:text-white"
            )("3467")
          )
        ),
        li(
          cls := "py-3 sm:py-4"
        )(
          div(
            cls := "flex items-center"
          )(
            div(
              cls := "flex-shrink-0"
            )(
              img(
                cls := "w-8 h-8 rounded-full",
                src := "/assets/images/cat-avt3.jpg",
                alt := "Michael image"
              )
            ),
            div(
              cls := "flex-1 min-w-0 ms-4"
            )(
              p(
                cls := "text-sm font-medium text-gray-900 truncate dark:text-white"
              )("Michael Gough"),
              p(
                cls := "text-sm text-gray-500 truncate dark:text-gray-400"
              )("email@windster.com")
            ),
            div(
              cls := "inline-flex items-center text-base font-semibold text-gray-900 dark:text-white"
            )("67")
          )
        ),
        li(
          cls := "py-3 sm:py-4"
        )(
          div(
            cls := "flex items-center"
          )(
            div(
              cls := "flex-shrink-0"
            )(
              img(
                cls := "w-8 h-8 rounded-full",
                src := "/assets/images/cat-avt4.jpg",
                alt := "Lana image"
              )
            ),
            div(
              cls := "flex-1 min-w-0 ms-4"
            )(
              p(
                cls := "text-sm font-medium text-gray-900 truncate dark:text-white"
              )("Lana Byrd"),
              p(
                cls := "text-sm text-gray-500 truncate dark:text-gray-400"
              )("email@windster.com")
            ),
            div(
              cls := "inline-flex items-center text-base font-semibold text-gray-900 dark:text-white"
            )("367")
          )
        ),
        li(
          cls := "pt-3 pb-0 sm:pt-4"
        )(
          div(
            cls := "flex items-center"
          )(
            div(
              cls := "flex-shrink-0"
            )(
              img(
                cls := "w-8 h-8 rounded-full",
                src := "/assets/images/cat-avt5.jpg",
                alt := "Thomas image"
              )
            ),
            div(
              cls := "flex-1 min-w-0 ms-4"
            )(
              p(
                cls := "text-sm font-medium text-gray-900 truncate dark:text-white"
              )("Thomes Lean"),
              p(
                cls := "text-sm text-gray-500 truncate dark:text-gray-400"
              )("email@windster.com")
            ),
            div(
              cls := "inline-flex items-center text-base font-semibold text-gray-900 dark:text-white"
            )("2367")
          )
        )
      )
    )
  )

  private val functionalProgrammingTestimonials: Html[Msg] =
    div(
      cls := "grid mb-8 border border-gray-200 rounded-lg shadow-sm dark:border-gray-700 md:mb-12 md:grid-cols-2 bg-white dark:bg-gray-800"
    )(
      figure(
        cls := "flex flex-col items-center justify-center p-8 text-center bg-white border-b border-gray-200 rounded-t-lg md:rounded-t-none md:rounded-ss-lg md:border-e dark:bg-gray-800 dark:border-gray-700"
      )(
        blockquote(
          cls := "max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 dark:text-gray-400"
        )(
          h3(
            cls := "text-lg font-semibold text-gray-900 dark:text-white"
          )("Elm's type system changed my perspective"),
          p(
            cls := "my-4"
          )("Elm's strongly typed system has made my code more reliable and easier to maintain. It’s a game-changer!")
        ),
        figcaption(
          cls := "flex items-center justify-center"
        )(
          img(
            cls := "rounded-full w-9 h-9",
            src := "https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/karen-nelson.png",
            alt := "profile picture"
          ),
          div(
            cls := "space-y-0.5 font-medium dark:text-white text-left rtl:text-right ms-3"
          )(
            div("Alice Johnson"),
            div(
              cls := "text-sm text-gray-500 dark:text-gray-400"
            )("Frontend Developer at Elm Co.")
          )
        )
      ),
      figure(
        cls := "flex flex-col items-center justify-center p-8 text-center bg-white border-b border-gray-200 md:rounded-se-lg dark:bg-gray-800 dark:border-gray-700"
      )(
        blockquote(
          cls := "max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 dark:text-gray-400"
        )(
          h3(
            cls := "text-lg font-semibold text-gray-900 dark:text-white"
          )("PureScript for functional purity"),
          p(
            cls := "my-4"
          )("PureScript has allowed me to bring functional programming purity to JavaScript. The experience is both challenging and fulfilling.")
        ),
        figcaption(
          cls := "flex items-center justify-center"
        )(
          img(
            cls := "rounded-full w-9 h-9",
            src := "https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/roberta-casas.png",
            alt := "profile picture"
          ),
          div(
            cls := "space-y-0.5 font-medium dark:text-white text-left rtl:text-right ms-3"
          )(
            div("Bob Smith"),
            div(
              cls := "text-sm text-gray-500 dark:text-gray-400"
            )("Software Engineer at PureScript Solutions")
          )
        )
      ),
      figure(
        cls := "flex flex-col items-center justify-center p-8 text-center bg-white border-b border-gray-200 md:rounded-es-lg md:border-b-0 md:border-e dark:bg-gray-800 dark:border-gray-700"
      )(
        blockquote(
          cls := "max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 dark:text-gray-400"
        )(
          h3(
            cls := "text-lg font-semibold text-gray-900 dark:text-white"
          )("Impressed by PureScript's interop with JS"),
          p(
            cls := "my-4"
          )(
            "PureScript's seamless interoperation with JavaScript has made it practical for me to integrate functional programming into legacy codebases."
          )
        ),
        figcaption(
          cls := "flex items-center justify-center"
        )(
          img(
            cls := "rounded-full w-9 h-9",
            src := "https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/jese-leos.png",
            alt := "profile picture"
          ),
          div(
            cls := "space-y-0.5 font-medium dark:text-white text-left rtl:text-right ms-3"
          )(
            div("Charlie Davis"),
            div(
              cls := "text-sm text-gray-500 dark:text-gray-400"
            )("Senior Developer at CodeLabs")
          )
        )
      ),
      figure(
        cls := "flex flex-col items-center justify-center p-8 text-center bg-white border-gray-200 rounded-b-lg md:rounded-se-lg dark:bg-gray-800 dark:border-gray-700"
      )(
        blockquote(
          cls := "max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 dark:text-gray-400"
        )(
          h3(
            cls := "text-lg font-semibold text-gray-900 dark:text-white"
          )("Functional programming in Elm has been liberating"),
          p(
            cls := "my-4"
          )("Using Elm for web applications has freed me from dealing with unpredictable runtime errors, and I now work with confidence.")
        ),
        figcaption(
          cls := "flex items-center justify-center"
        )(
          img(
            cls := "rounded-full w-9 h-9",
            src := "https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/joseph-mcfall.png",
            alt := "profile picture"
          ),
          div(
            cls := "space-y-0.5 font-medium dark:text-white text-left rtl:text-right ms-3"
          )(
            div("Daniel Lee"),
            div(
              cls := "text-sm text-gray-500 dark:text-gray-400"
            )("Lead Developer at ElmWorks")
          )
        )
      )
    )
