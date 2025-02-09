package com.example.common

import zio.http.Path
import zio.http.Root
import zio.http.Scheme
import zio.http.URL
import zio.http.codec.PathCodec
import zio.http.codec.PathCodec.*

package object http {

  object PathDef {

    /**
     * * Used in Backend project
     */
    val prefix: PathCodec[Unit]                 = Root / "api" / "v1"
    val login: PathCodec[Unit]                  = prefix / "login"
    val randomMessage: PathCodec[Unit]          = prefix / "random-message"
    val subscribeServerMessage: PathCodec[Unit] = prefix / "subscribe"
  }

  /**
   * * Used in Frontend project
   */
  object BackendApiUrl {
    import com.example.common.BuildInfo.backendBaseUrl

    private def getBackendWebSocketBaseUrl =
      URL
        .decode(backendBaseUrl)
        .map { url =>
          url.scheme match
            case Some(Scheme.HTTP) =>
              backendBaseUrl.replace("http", "ws")
            case Some(Scheme.HTTPS) =>
              backendBaseUrl.replace("https", "wss")
            case _ =>
              "invalid_backendBaseUrl"
        }
        .getOrElse("")

    private def generateUrl(maybePath: Either[String, zio.http.Path]): String =
      backendBaseUrl + maybePath.toOption.get.toString

    private def generateWsUrl(maybePath: Either[String, zio.http.Path]): String =
      getBackendWebSocketBaseUrl + maybePath.toOption.get.toString

    val login: String                  = generateUrl(PathDef.login.format(()))
    val randomMessage: String          = generateUrl(PathDef.randomMessage.format(()))
    val subscribeServerMessage: String = generateWsUrl(PathDef.subscribeServerMessage.format(()))
  }
}
