package $package$.http

import $package$.common.http.PathDef
import $package$.common.model.LoginFailure
import $package$.common.model.LoginRequest
import $package$.common.model.LoginSuccess
import $package$.common.model.RandomMessage
import $package$.common.util.JwtHelper.UserClaim
import $package$.config.AppConfig
import $package$.service.JWTIssuer
import $package$.service.JWTVerifier
import $package$.services.RandomQuotes
import zio.IO
import zio.ZIO
import zio.http.*
import zio.http.Charsets
import zio.http.Header.AccessControlAllowOrigin
import zio.http.Header.HeaderType
import zio.http.Header.Origin
import zio.http.Middleware
import zio.http.Middleware.CorsConfig
import zio.http.Middleware.cors
import zio.http.Request
import zio.http.Response
import zio.http.Status
import zio.json.*
import zio.json.JsonDecoder

import scala.reflect.ClassTag
import scala.reflect.classTag
import scala.util.Random

object DefaultRoutes {

  private def parseBody[T: JsonDecoder: ClassTag](request: Request): IO[Response, T] =
    for {
      bodyStr <- request.body.asString(Charsets.Utf8).orElseFail(Response.text("Not supported request body format").status(Status.BadRequest))
      result <- ZIO
                  .fromEither[String, T](JsonDecoder[T].decodeJson(bodyStr))
                  .orElseFail(Response.text(s"Cannot parse body as \${classTag[T].runtimeClass.getName}").status(Status.BadRequest))
    } yield result

  private def withAuthMiddleware[R](routes: Routes[R, Nothing]): Routes[JWTVerifier & R, Nothing] = {
    val authMiddleware: HandlerAspect[JWTVerifier, UserClaim] = Middleware.customAuthProvidingZIO[JWTVerifier, UserClaim](
      request =>
        request.header(Header.Authorization) match {
          case Some(Header.Authorization.Bearer(token)) =>
            JWTVerifier.decode(token.value.asString).map(Some(_)).mapError { case message =>
              Response.text(message).status(Status.Unauthorized)
            }

          case _ => ZIO.none
        },
      Headers(Header.WWWAuthenticate.Bearer(realm = "Access"))
    )
    routes.@@[JWTVerifier & R](authMiddleware)
  }
  private val parseJson: Handler[Any, Response, Request, LoginRequest] =
    Handler.fromFunctionZIO(parseBody[LoginRequest](_))

  private val processLogin: Handler[JWTIssuer, Nothing, LoginRequest, Response] =
    val verifyUserLogic =
      (username: String, password: String) => username.equalsIgnoreCase("Scala") && password == "nopassword" // TODO: Create your own verify logic
    Handler.fromFunctionZIO { loginRequest =>
      val valid = verifyUserLogic(loginRequest.username, loginRequest.password)
      if (valid) {
        val user = JWTIssuer.User(loginRequest.username)
        JWTIssuer.issueToken(user).map(issuedToken => Response.json(LoginSuccess(issuedToken, "not-implemented").toJson))
      } else {
        ZIO.succeed(Response.json(LoginFailure("Username or Password is not correct").toJson))
      }
    }

  /**
   * Browsers reject a wildcard origin once credentials are involved, so each configured origin is echoed back
   * verbatim. Unparseable entries are dropped rather than failing startup. The remaining CorsConfig defaults
   * already allow any requested header (so `Authorization` works) and answer OPTIONS preflights.
   */
  private def corsMiddleWare(config: AppConfig) = {
    val allowedOrigins = config.allowedOrigins.flatMap(Origin.parse(_).toOption).toSet
    cors(
      CorsConfig(
        allowedOrigin = origin => Option.when(allowedOrigins.contains(origin))(AccessControlAllowOrigin.Specific(origin))
      )
    )
  }

  def public(config: AppConfig) = Routes(
    Method.GET / Root ->
      handler(
        Response.redirect(URL.decode(PathDef.ping.toString).toOption.get)
      ),
    Method.GET / PathDef.ping ->
      handler(
        Response(
          status = Status.Ok,
          headers = Headers(Header.ContentType(MediaType.text.html)),
          body = Body.fromString(HtmlContent.value)
        )
      ),
    Method.GET / PathDef.ping ->
      handler(
        Response(
          status = Status.Ok,
          headers = Headers(Header.ContentType(MediaType.text.html)),
          body = Body.fromString(HtmlContent.value)
        )
      ),
    Method.POST / PathDef.login ->
      handler(parseJson >>> processLogin)
  ) @@ corsMiddleWare(config)

  def authenticated(config: AppConfig) = withAuthMiddleware {
    Routes(
      Method.GET / PathDef.randomMessage -> handler(RandomQuotes.getRandomMessage.map(msg => Response.json(RandomMessage(msg).toJson))),
      Method.GET / PathDef.randomMessage2 -> handler(RandomQuotes.getRandomMessage.map(msg => Response.json(RandomMessage(msg).toJson)))
    )
  } @@ corsMiddleWare(config)

}
