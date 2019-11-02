import sbt._

object Dependencies {
  val MoPlay = Seq(
    "com.moplay" %% "sdk-http" % Versions.moPlay
  )

  val Circe = Seq(
    "io.circe" %% "circe-core" % Versions.circe,
    "io.circe" %% "circe-generic" % Versions.circe,
    "io.circe" %% "circe-parser" % Versions.circe,
    "io.circe" %% "circe-generic-extras" % Versions.circe
  )

  val Database = Seq(
    "org.tpolecat" %% "doobie-core" % Versions.doobie,
    "org.tpolecat" %% "doobie-postgres" % Versions.doobie,
    "org.tpolecat" %% "doobie-postgres-circe" % Versions.doobie
  )

  val DatabaseMigration = Seq(
    "org.flywaydb" % "flyway-core" % Versions.flyway
  )

  val Enumeratum = Seq(
    "com.beachape" %% "enumeratum" % Versions.enumeratum,
    "com.beachape" %% "enumeratum-circe" % Versions.enumeratumCirce
  )

  val Akka = Seq(
    "com.typesafe.akka" %% "akka-actor" % Versions.akka,
    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka
  )

  val Config = Seq(
    "com.github.pureconfig" %% "pureconfig" % Versions.pureconfig
  )

  val Cornichon = Seq(
    "com.github.agourlay" %% "cornichon-kafka" % "0.18.1" % "it",
    "com.github.agourlay" %% "cornichon-test-framework" % "0.18.1" % "it"
  )

  val Wiremock = Seq(
    "com.github.tomakehurst" % "wiremock" % "2.24.1"
  )

  val Api = Seq(
    "com.softwaremill.tapir" %% "tapir-core" % Versions.tapir,
    "com.softwaremill.tapir" %% "tapir-json-circe" % Versions.tapir,
    "com.softwaremill.tapir" %% "tapir-akka-http-server" % Versions.tapir
  )

  val ApiClient = Seq(
    "com.softwaremill.tapir" %% "tapir-sttp-client" % Versions.tapir
  )

  val ApiDocs = Seq(
    "com.softwaremill.tapir" %% "tapir-openapi-circe-yaml" % Versions.tapir,
    "com.softwaremill.tapir" %% "tapir-openapi-docs" % Versions.tapir,
    "com.softwaremill.tapir" %% "tapir-swagger-ui-akka-http" % Versions.tapir
  )

  val HttpClient = Seq(
    "com.softwaremill.sttp" %% "core" % Versions.sttp,
    "com.softwaremill.sttp" %% "akka-http-backend" % Versions.sttp,
    "com.softwaremill.sttp" %% "circe" % Versions.sttp
  )

  val Testing = Seq(
    "com.typesafe.akka" %% "akka-testkit" % Versions.akka % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % Versions.akka % Test,
    "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % Test,
    "org.scalatest" %% "scalatest" % Versions.scalaTest % Test,
    "org.scalatestplus" %% "scalatestplus-scalacheck" % Versions.scalaTestPlusScalacheck % Test
  )

  val IO = Seq(
    "org.typelevel" %% "cats-effect" % Versions.cats
  )

  val Logging = Seq(
    "com.emarsys" %% "scala-logger" % Versions.scalaLogger
  )
}
