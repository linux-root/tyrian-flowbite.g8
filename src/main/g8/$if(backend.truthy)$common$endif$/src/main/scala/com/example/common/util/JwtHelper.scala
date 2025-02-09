package com.example.common.util

import com.example.common.util.JwtHelper.UserClaim
import pdi.jwt.Jwt
import pdi.jwt.JwtAlgorithm
import pdi.jwt.JwtBase64
import pdi.jwt.JwtClaim
import zio.json.*
import zio.json.DecoderOps
import zio.json.ast.Json

import java.time.Instant
import scala.util.Try

/**
 *   - Centralized JWT protocol setup for both Backend and Frontend.
 *   - A single source of truth for updating the JWT algorithm, JWT content ensuring consistency
 *     across the system.
 */
object JwtHelper:

  private val ALG = JwtAlgorithm.HS256 // can be udpated quickly

  case class UserClaim(username: String) // Add more fields on demand

  given JsonCodec[UserClaim] = DeriveJsonCodec.gen[UserClaim]

  def decode(accessToken: String, secretKey: String): Either[String, UserClaim] =
    Jwt
      .decode(accessToken, secretKey, List(ALG))
      .map(_.content)
      .fold[Either[String, String]](e => Left(s"Invalid token: \${e.getMessage()}"), Right(_))
      .flatMap(_.fromJson[UserClaim])

  def getUnverifiedUserClaim(accessToken: String): Either[String, UserClaim] =
    val payload     = new String(JwtBase64.decode(accessToken.split("\\\.")(1)))
    val jsonPayload = payload.fromJson[Json.Obj].getOrElse(Json.Obj())
    (for {
      username <- jsonPayload.get("username").flatMap(_.asString)
      // email   <- jsonPayload.get("email").flatMap(_.asString)
      // subject <- jsonPayload.get("sub").flatMap(_.asString)
    } yield UserClaim(username)) match
      case None =>
        Left("Invalid access token payload")
      case Some(claim) =>
        Right(claim)

  def issueToken(user: UserClaim, secretKey: String): String = {
    val content    = user.toJson
    val issuedAt   = Instant.now.getEpochSecond
    val expiration = Instant.now.plusSeconds(300).getEpochSecond
    val claim      = JwtClaim(content, issuedAt = Some(issuedAt), expiration = Some(expiration))
    Jwt.encode(claim, secretKey, ALG)
  }
