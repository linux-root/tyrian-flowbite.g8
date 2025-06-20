addSbtPlugin("com.github.sbt"     %% "sbt-native-packager" % "1.9.4")
addSbtPlugin("org.scala-js"        % "sbt-scalajs"         % "1.19.0")
addSbtPlugin("ch.epfl.scala"       % "sbt-scalafix"        % "0.10.4")
addSbtPlugin("com.github.reibitto" % "sbt-welcome"         % "0.5.0")
$if(backend.truthy)$
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.13.1")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.3.2")
$endif$

libraryDependencies += "com.w47s0n" %% "consolebox" % "0.2.1"
