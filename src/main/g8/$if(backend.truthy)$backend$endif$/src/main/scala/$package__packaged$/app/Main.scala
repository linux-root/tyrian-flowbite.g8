package $package$.app

import $package$.config.AppConfig
import $package$.http.DefaultRoutes
import $package$.service.JWTIssuer
import $package$.service.JWTVerifier
import $package$.services.RandomQuotes
import zio.*
import zio.http.*
import zio.logging.backend.SLF4J

object Main extends ZIOAppDefault {

  /**
   * The bind address comes from [[AppConfig]] rather than `Server.defaultWithPort`, which always listens on
   * 0.0.0.0. Deployments that sit behind a reverse proxy want to bind a private address instead.
   */
  private val server: ZLayer[AppConfig, Throwable, Server] =
    ZLayer.fromFunction((config: AppConfig) => Server.Config.default.binding(config.host, config.port)) >>> Server.live

  override def run =
    ZIO
      .serviceWithZIO[AppConfig](config => Server.serve(DefaultRoutes.public(config) ++ DefaultRoutes.authenticated(config)))
      .provide(
        server,
        JWTVerifier.live,
        JWTIssuer.live,
        AppConfig.live,
        RandomQuotes.live
      )

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j

}
