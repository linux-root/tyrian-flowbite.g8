package $package$.view

import tyrian.Html.*
import tyrian.Html
import $package$.model.Msg

/** Shared page shell for every component demo: a centered, padded column with a heading. */
object ComponentShell:
  def apply(title: String, content: Html[Msg]): Html[Msg] =
    div(cls := "w-full max-w-5xl mx-auto px-4 py-10")(
      h1(cls := "mb-8 text-3xl font-bold tracking-tight text-gray-900 dark:text-white")(title),
      content
    )
