object Dependencies {
  val Tyrian    = "0.11.0"
  val Scalatest = "3.2.15"
  val Quicklens = "1.9.7"

  $if(use_zio.truthy) $
  val ZioInteropCats = "23.1.0.3"
  $endif$
  val JavaSecureRandom = "1.0.0"
}
