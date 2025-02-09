package com.example.tfwithbackend

import com.example.tfwithbackend.page.*
import scala.util.Try

package object route {
  object Route {
    object Home:
      def unapply(path: String): Option[Unit] = if path == "/" then Some(()) else None

    object ComponentDemo:
      def unapply(path: String): Option[Page] = Try {
        path.split("/components/")(1) match {
          case "alerts"     => Page.Alerts
          case "accordion"  => Page.Accordion
          case "buttons"    => Page.Buttons
          case "badges"     => Page.Badges
          case "cards"      => Page.Cards
          case "carousel"   => Page.Carousel
          case "toggle"     => Page.Toggle
          case "datepicker" => Page.Datepicker
        }
      }.toOption

    object Login:
      def unapply(path: String): Option[Unit] = if path.startsWith("login") then Some(()) else None

  }

}
