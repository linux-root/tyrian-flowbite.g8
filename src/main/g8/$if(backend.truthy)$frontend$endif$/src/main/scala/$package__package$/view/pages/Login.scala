package $package$.view.pages

import tyrian.Html.*
import tyrian.Html.{attribute => attr}
import tyrian.*
import $package$.model.Msg
import $package$.model.Model.LoginForm
import $package$.view.components.Icons
import $package$.util.ElementIdGenerator

object LoginPage {

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
          cls         := "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full ps-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
        )
      )
    )

  def apply(state: LoginForm): Html[Msg] =
    div(cls := "h-screen flex justify-center bg-base-150")(
      div(
        cls := "w-full max-w-sm my-auto p-4 bg-white border border-gray-200 rounded-lg shadow-sm sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700"
      )(
        form(cls := "space-y-6", attr("action", "#"))(
          h5(cls := "text-xl font-medium text-gray-900 dark:text-white")("Just click Login"),
          formField("Username", state.username, v => Msg.UpdateLoginForm(state.copy(username = v)), "text"),
          formField("Password", state.password, v => Msg.UpdateLoginForm(state.copy(password = v)), "password"),
          p(cls := "text-red-500")(state.error.getOrElse("")),
          div(cls := "flex items-start")(
            div(cls := "flex items-center h-5")(
              input(
                id     := "remember",
                `type` := "checkbox",
                cls    := "w-4 h-4 checked:bg-purple-500 dark:checked:bg-purple-400 border border-gray-300 rounded-sm bg-gray-50 focus:ring-3 focus:ring-purple-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-purple-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
              )
            ),
            label(`for` := "remember", cls := "ms-2 text-sm font-medium text-gray-900 dark:text-gray-300")("Remember me"),
            a(
              cls := "ms-auto text-sm text-purple-700 hover:underline dark:text-purple-400"
            )("Forgot Password?")
          ),
          button(
            onClick(Msg.SubmitLogin),
            disabled(state.isSubmitDisabled),
            cls := s"w-full text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800 \${if state.isSubmitDisabled then "btn-disabled" else ""}"
          )("Login"),
          span(
            style(if state.isLoading then CSS.display("block") else CSS.display("none")),
            cls := "loading loading-ring loading-lg text-info"
          )()
        )
      )
    )
}
