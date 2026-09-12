package $package$.config

import zio._

/**
 * Runtime configuration, read from the environment.
 *
 * Every value has a development-friendly default, so `sbt backend/run` keeps working with no setup. A real
 * deployment is expected to override all of them -- in particular [[secret]], which must not stay at its default.
 */
case class AppConfig(host: String, port: Int, secret: String, allowedOrigins: List[String])

object AppConfig {

  /** Binds every interface so the dev server is reachable from a container or another host on the LAN. */
  private val DefaultHost = "0.0.0.0"

  private val DefaultPort = 8080

  private val DefaultSecret = "t0ps3cret-token-change-me-in-prod"

  /** The Vite dev server, i.e. where the frontend runs during `sbt dev`. */
  private val DefaultAllowedOrigins = List("http://localhost:9876")

  private def env(name: String): UIO[Option[String]] =
    System.env(name).orDie.map(_.filter(_.trim.nonEmpty))

  val live: ULayer[AppConfig] = ZLayer {
    for {
      host   <- env("BACKEND_HOST").map(_.getOrElse(DefaultHost))
      port   <- env("BACKEND_PORT").map(_.flatMap(_.trim.toIntOption).getOrElse(DefaultPort))
      secret <- env("BACKEND_JWT_SECRET").map(_.getOrElse(DefaultSecret))
      origins <- env("BACKEND_CORS_ORIGINS").map {
                   // Comma-separated, e.g. "https://app.example.com,https://staging.example.com"
                   case Some(raw) =>
                     val parsed = raw.split(",").map(_.trim).filter(_.nonEmpty).toList
                     if (parsed.isEmpty) DefaultAllowedOrigins else parsed
                   case None => DefaultAllowedOrigins
                 }
    } yield AppConfig(host, port, secret, origins)
  }
}
