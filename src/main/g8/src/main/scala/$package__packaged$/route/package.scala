package $package$

import $package$.page.*
import scala.util.Try

package object route {
  object Route {
    object Home:
      def unapply(path: String): Option[Unit] = if path == "/" then Some(()) else None

    object ComponentDemo:
      def unapply(path: String): Option[Page] = Try {
        path.split("/components/")(1) match {
          case "alerts"    => Page.Alerts
          case "accordion" => Page.Accordion
          case "buttons"   => Page.Buttons
          case "badges"    => Page.Badges
          case "cards"     => Page.Cards
          case "carousel"  => Page.Carousel
        }
      }.toOption

  }

}
