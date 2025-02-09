package com.example.common

import zio.Schedule
import zio.json.*
import zio.json.ast.Json
import zio.schema.annotation.description

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import scala.util.Random
import scala.util.Try

/**
 * Shared models between Backend and Frontend
 */
package object model {
  case class LoginRequest(username: String, password: String)
  case class LoginSuccess(accessToken: String, refreshToken: String)
  case class LoginFailure(reason: String)
  case class RandomMessage(text: String)

  object LoginRequest {
    given JsonCodec[LoginRequest] = DeriveJsonCodec.gen[LoginRequest]
  }

  object LoginSuccess {
    given JsonCodec[LoginSuccess] = DeriveJsonCodec.gen[LoginSuccess]
  }

  object LoginFailure {
    given JsonCodec[LoginFailure] = DeriveJsonCodec.gen[LoginFailure]
  }

  object RandomMessage {
    given JsonCodec[RandomMessage] = DeriveJsonCodec.gen[RandomMessage]
  }
}
