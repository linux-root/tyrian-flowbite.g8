package com.example.until

import com.example.common.BuildInfo
import com.example.common.http.BackendApiUrl
import com.example.common.http.PathDef
import com.example.common.model.RandomMessage
import com.example.common.model.LoginFailure
import com.example.common.model.LoginRequest
import com.example.common.model.LoginSuccess
import com.example.tfwithbackend.model.Msg
import com.example.tfwithbackend.model.Msg.*
import com.example.until.HttpHelper.ResponseDecoders.*
import tyrian.Cmd
import tyrian.http.*
import zio.Task
import zio.interop.catz.*
import zio.json.*

object HttpHelper:

  private def sendWithAuthToken[A, R <: Msg](request: Request[A], resultToMessage: Decoder[R]): Cmd[Task, Msg.SendHttpRequestWithAccessToken] =
    Cmd.Emit(
      Msg.SendHttpRequestWithAccessToken(accessToken =>
        Http.send(request.withHeaders(Header("Authorization", s"Bearer \$accessToken")), resultToMessage)
      )
    )

  def login(username: String, password: String): Cmd[Task, Msg.LoginSuccess | Msg.LoginFailure] = {
    val body    = LoginRequest(username, password)
    val request = Request.post(BackendApiUrl.login, Body.json(body.toJson))
    Http.send(request, loginDecoder)
  }

  val fetchServerMessage: Cmd[Task, Msg.SendHttpRequestWithAccessToken] =
    fetchServerMessage(BackendApiUrl.randomMessage)

  val fetchServerMessage2: Cmd[Task, Msg.SendHttpRequestWithAccessToken] =
    fetchServerMessage(BackendApiUrl.randomMessage2)

  private def fetchServerMessage(apiUrl: String): Cmd[Task, Msg.SendHttpRequestWithAccessToken] = {
    val request = Request.get(apiUrl)
    sendWithAuthToken(request, greetDecoder)
  }

  object ResponseDecoders {

    val loginDecoder: Decoder[Msg.LoginSuccess | Msg.LoginFailure] =
      given JsonDecoder[Either[LoginSuccess, LoginFailure]] =
        JsonDecoder[LoginSuccess] <+> JsonDecoder[LoginFailure]
      Decoder(
        _.body.fromJson[Either[LoginSuccess, LoginFailure]] match {
          case Right(Left(LoginSuccess(at, rt))) =>
            Msg.LoginSuccess(at, rt)
          case Right(Right(LoginFailure(msg))) =>
            Msg.LoginFailure(msg)
          case _ =>
            Msg.LoginFailure("Failed to parse login response")
        },
        error => Msg.LoginFailure(s"Server error. Make sure server is running on \${BuildInfo.backendBaseUrl}")
      )

    val greetDecoder: Decoder[Msg.GreetingFromBackend | Msg.Error | Msg.Logout.type] = Decoder(
      _.body.fromJson[RandomMessage] match {
        case Right(RandomMessage(text)) =>
          Msg.GreetingFromBackend(text)
        case _ =>
          Msg.Logout
      },
      error => Msg.Error(error.toString())
    )

  }
