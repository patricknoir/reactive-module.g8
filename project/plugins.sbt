resolvers += Resolver.url(
  "sbts3 ivy resolver",
  url("https://dl.bintray.com/emersonloureiro/sbt-plugins")
)(Resolver.ivyStylePatterns)


addSbtPlugin("com.moplay" % "platform-plugin" % "3.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.3.2")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.2")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.11")

addSbtPlugin("ch.epfl.scala" % "sbt-bloop" % "1.3.2")

addSbtPlugin("com.lightbend.sbt" % "sbt-javaagent" % "0.1.5")

addSbtPlugin("net.vonbuchholtz" % "sbt-dependency-check" % "1.3.0")
