import com.moplay.platform.plugin.PlatformPlugin._
import com.moplay.platform.plugin.ReleaseSettings.{ releaseProcess => MoPlayReleaseProcess, _ }
import com.typesafe.sbt.packager.MappingsHelper
import sbt.Keys.libraryDependencies
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

lazy val commonSettings = Seq(
  ecrId in ThisBuild := "$ecrId$",
  dockerRepository := Some(s"\${ecrId.value}.dkr.ecr.eu-west-2.amazonaws.com"),
  javaAgents += "io.kamon" % "kanela-agent" % "1.0.1",
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")
)

lazy val bcPrefix = "$boundedContextId$"

scalacOptions in ThisBuild ++= Seq(
  "-Ypartial-unification",
  "-deprecation",
  "-feature",
  "-language:higherKinds")

$if(createApi.truthy)$
lazy val $boundedContextId$Api = (project in file(s"\$bcPrefix-api"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api"
  )
  .dependsOn($boundedContextId$Sdk)

lazy val $boundedContextId$ApiExternal = (project in file(s"\$bcPrefix-api-external"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api-external"
  )
  .dependsOn($boundedContextId$Sdk)

lazy val $boundedContextId$ApiInternal = (project in file(s"$bcPrefix-api-internal"))
  .enablePlugins(JavaAgent, PlatformPlugin)
  .settings(commonSettings)
  .settings(
    name := s"\$bcPrefix-api-internal",
    libraryDependencies ++= Dependencies.Config
  )
  .dependsOn($boundedContextId$Sdk)
$endif$

lazy val $boundedContextId$Model = (project in file(s"\$bcPrefix-model"))
  .settings(
    name := s"\$bcPrefix-model",
    libraryDependencies ++=
      Dependencies.Circe ++
        Dependencies.Enumeratum
  )

lazy val $boundedContextId$Sdk = (project in file(s"\${bcPrefix}-sdk"))
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
  .dependsOn($boundedContextId$Model)

