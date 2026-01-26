addSbtPlugin("com.github.sbt"     %% "sbt-native-packager" % "1.9.4")
addSbtPlugin("org.scala-js"        % "sbt-scalajs"         % "1.20.2")
addSbtPlugin("ch.epfl.scala"       % "sbt-scalafix"        % "0.10.4")
addSbtPlugin("com.github.reibitto" % "sbt-welcome"         % "0.5.0")
addSbtPlugin("com.w47s0n"          % "sbt-scalajs-cli"     % "0.1.1")
$if(backend.truthy) $
  addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.13.1")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.3.2")
addSbtPlugin("io.spray"           % "sbt-revolver"             % "0.10.0")
$endif$
