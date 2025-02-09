package com.example.http

import com.example.common.http.PathDef
import com.example.common.model.LoginFailure
import com.example.common.model.LoginRequest
import com.example.common.model.LoginSuccess
import com.example.common.model.RandomMessage
import com.example.common.util.JwtHelper.UserClaim
import com.example.service.JWTIssuer
import com.example.service.JWTVerifier
import com.example.services.RandomQuotes
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

  private val corsMiddleWare =
    cors(
      CorsConfig(
        allowedOrigin = {
          case origin if origin == Origin.parse("http://localhost:9876").toOption.get => // TODO: Move to  config
            Some(AccessControlAllowOrigin.Specific(origin))
          case _ => None
        }
      )
    )

  val public = Routes(
    Method.POST / PathDef.login ->
      handler(parseJson >>> processLogin)
  ) @@ corsMiddleWare

  val authenticated = withAuthMiddleware {
    Routes(
      Method.GET / PathDef.randomMessage -> handler(RandomQuotes.getRandomMessage.map(msg => Response.json(RandomMessage(msg).toJson)))
    )
  } @@ corsMiddleWare

}
