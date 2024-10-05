package $organization$.view

import tyrian.Html.*
import tyrian.Html
import $organization$.model.Msg

object MainContainer:

  def apply(content: Html[Msg]): Html[Msg] =
    div(cls := "flex items-center justify-center min-h-screen")(
      content
    )
