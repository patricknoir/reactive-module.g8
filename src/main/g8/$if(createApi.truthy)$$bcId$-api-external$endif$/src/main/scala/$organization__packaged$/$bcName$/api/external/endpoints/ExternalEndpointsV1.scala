package $organization;format="package"$.$bcName$.api.external.endpoints

import tapir._
import tapir.json.circe._

object ExternalEndpointsV1 {

  private val baseEndpoint: Endpoint[Unit, String, Unit, Nothing] =
    endpoint
      .in("api" / "v1.0")
      .errorOut(jsonBody[String])

  val helloworld: Endpoint[Unit, String, String, Nothing] =
    baseEndpoint
      .in("helloworld")
      .out(jsonBody[String])

  val endpoints = List(helloworld)

}
