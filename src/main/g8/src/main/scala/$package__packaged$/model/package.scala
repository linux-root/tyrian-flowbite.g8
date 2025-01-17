package $package$
import $package$.page.*
import tyrian.Location

object model {
  enum Msg {
    case NoOp
    case LogMessage(msg: String)
    case NavigateTo(page: Page)
    case DoNavigate(page: Page)
    case UnhandledRoute(path: String)
    case GoToInternet(loc: Location.External)
    case ToggleDarkMode
  }

  case class Model(currentPage: Page, isDarkMode: Boolean) {
    def toggleDarkMode: Model =
      copy(isDarkMode = !isDarkMode)
  }

  object Model {
    val init: Model = Model(Page.Home, isDarkMode = false)
  }
}
