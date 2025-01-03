package $package$
import $package$.page.*
import tyrian.Location

object model {
  enum Msg {
    case NoOp                    extends Msg
    case LogMessage(msg: String) extends Msg
    case NavigateTo(page: Page)
    case DoNavigate(page: Page)
    case UnhandledRoute(path: String)
    case GoToInternet(loc: Location.External)
  }

  case class Model(currentPage: Page)

  object Model {
    val init: Model = Model(Page.Home)
  }
}
