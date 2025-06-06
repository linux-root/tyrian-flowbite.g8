object Dependencies {
  val Tyrian    = "0.14.0"
  val Scalatest = "3.2.15"
  val Quicklens = "1.9.7"
  val JavaSecureRandom = "1.0.0"
  $if(use_zio.truthy || backend.truthy) $
  val ZioInteropCats = "23.1.0.3"
  $endif$
  $if(backend.truthy) $
  val ZioJson        = "0.7.8"
  val ZioHttp        = "3.0.1"
  val JwtScala       = "10.0.1"
  val Jansi          = "2.4.0"
  val ZioLogging     = "2.4.0"
  val LogbackClassic = "1.5.16"
  $endif$
}
