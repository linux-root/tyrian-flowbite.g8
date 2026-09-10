package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.*
import $package$.model.Msg
import $package$.model.Model.LoginForm
import $package$.view.components.Icons
import $package$.util.ElementIdGenerator

object LoginPage {

  private val spinner =
    Html.raw("div")(
      """<svg class="w-4 h-4 animate-spin" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle><path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 0 1 8-8V0C5.373 0 0 5.373 0 12h4z"></path></svg>"""
    )

  private def formField(
    name: String,
    _value: String,
    _onInput: String => Msg,
    _type: "password" | "text"
  ): Html[Msg] =
    val icon         = if _type == "password" then Icons.lock else Icons.user
    val _placeholder = if _type == "password" then "••••••••" else ""
    val _id          = ElementIdGenerator.generate("login-")
    div()(
      label(`for` := _id, cls := "block mb-2 text-sm font-medium text-gray-900 dark:text-white")(name),
      div(cls := "relative")(
        div(cls := "absolute inset-y-0 start-0 flex items-center ps-3.5 pointer-events-none")(icon),
        input(
          onInput(_onInput),
          id          := _id,
          `type`      := _type,
          value       := _value,
          placeholder := _placeholder,
          cls         := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
        )
      )
    )

  def apply(state: LoginForm): Html[Msg] =
    div(cls := "min-h-[70vh] flex items-center justify-center px-4 py-10")(
      div(
        cls := "w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-sm sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700"
      )(
        form(cls := "space-y-6", attr("action", "#"))(
          h5(cls := "text-xl font-medium text-gray-900 dark:text-white")("Sign in"),
          p(cls := "text-sm text-gray-500 dark:text-gray-400")("Authentication is mocked — just click Login."),
          formField("Username", state.username, v => Msg.UpdateLoginForm(state.copy(username = v)), "text"),
          formField("Password", state.password, v => Msg.UpdateLoginForm(state.copy(password = v)), "password"),
          p(cls := "text-sm text-red-500")(state.error.getOrElse("")),
          div(cls := "flex items-center")(
            input(
              id     := "remember",
              `type` := "checkbox",
              cls    := "w-4 h-4 border border-gray-300 rounded-sm bg-gray-50 focus:ring-3 focus:ring-purple-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-purple-600 dark:ring-offset-gray-800"
            ),
            label(`for` := "remember", cls := "ms-2 text-sm font-medium text-gray-900 dark:text-gray-300")("Remember me"),
            a(cls := "ms-auto text-sm text-purple-700 hover:underline dark:text-purple-400")("Forgot Password?")
          ),
          button(
            onClick(Msg.SubmitLogin),
            disabled(state.isSubmitDisabled),
            cls := "w-full inline-flex items-center justify-center gap-2 text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800 disabled:opacity-60 disabled:cursor-not-allowed"
          )(
            if state.isLoading then spinner else text(""),
            text("Login")
          )
        )
      )
    )
}
