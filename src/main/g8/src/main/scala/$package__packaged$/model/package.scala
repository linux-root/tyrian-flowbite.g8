package $package$

object model {
  enum Msg {
    case NoOp extends Msg

  }

  case class Model(message: String)

  object Model {

    def init: Model = Model("Hello world")

  }
}
