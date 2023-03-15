scalaVersion := "3.0.0"
crossScalaVersions ++= Seq("2.13.6", "3.0.0")

lazy val main = project
  .in(file("."))
  .settings(
    name := "main",
    scalaVersion := "2.13.6",
    semanticdbEnabled := true,
    scalacOptions ++= Seq("-explaintypes", "-Wunused"),
    libraryDependencies ++= Seq(
      "org.typelevel"                    %% "cats-core"           % "2.2.0",
      "ch.epfl.scala"                     % "scalafix-interfaces" % "0.9.26",
      "ch.epfl.scala"                    %% "scalafix-rules"      % "0.9.26" % Test,
      "org.scalactic" %% "scalactic" % "3.2.9",
      "org.scalatest" %% "scalatest" % "3.2.9" % "test",
      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "com.google.inject" % "guice" % "5.0.1",
      "net.codingwell" %% "scala-guice" % "5.0.1",
      "org.scala-lang.modules" % "scala-xml_2.13" % "2.1.0",
      "com.typesafe.play" %% "play-json" % "2.10.0-RC7",
      "org.scalafx" %% "scalafx" % "16.0.0-R24",
      compilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
    )
  )