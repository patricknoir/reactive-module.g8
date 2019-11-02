import com.moplay.platform.plugin.PlatformPlugin._
import com.moplay.platform.plugin.ReleaseSettings.{ releaseProcess => MoPlayReleaseProcess, _ }
import com.typesafe.sbt.packager.MappingsHelper
import sbt.Keys.libraryDependencies
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

scalaVersion := "2.12.10"

lazy val commonSettings = Seq(
  ecrId in ThisBuild := "$ecrId$",
  dockerRepository := Some(s"\${ecrId.value}.dkr.ecr.eu-west-2.amazonaws.com"),
  javaAgents += "io.kamon" % "kanela-agent" % "1.0.1",
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")
)

lazy val bcPrefix = "$bcId$"

scalacOptions in ThisBuild ++= Seq(
  "-Ypartial-unification",
  "-deprecation",
  "-feature",
  "-language:higherKinds")

enablePlugins(PlatformPlugin)

lazy val $bcId$Api = (project in file(s"\$bcPrefix-api"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api"
  )
  .dependsOn($bcId$Sdk)

lazy val $bcId$ApiExternal = (project in file(s"\$bcPrefix-api-external"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api-external",
    libraryDependencies ++= Dependencies.MoPlay
  )
  .dependsOn($bcId$Sdk)

lazy val $bcId$ApiInternal = (project in file(s"\$bcPrefix-api-internal"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api-internal",
    libraryDependencies ++= Dependencies.MoPlay
  )
  .dependsOn($bcId$Sdk)

lazy val $bcId$Model = (project in file(s"\$bcPrefix-model"))
  .settings(
    name := s"\$bcPrefix-model",
    libraryDependencies ++=
      Dependencies.Circe ++
        Dependencies.Enumeratum
  )

lazy val $bcId$Sdk = (project in file(s"\${bcPrefix}-sdk"))
  .settings(
    name := s"\$bcPrefix-sdk",
    libraryDependencies ++=
      Dependencies.Api ++
        Dependencies.ApiClient ++
        Dependencies.ApiDocs ++
        Dependencies.Circe ++
        Dependencies.DatabaseMigration ++
        Dependencies.HttpClient ++
        Dependencies.Logging
  )
  .dependsOn($bcId$Model)

