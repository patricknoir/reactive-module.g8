package $organization$.$bcId$.api.internal

import akka.actor.ActorSystem
import com.moplay.platform.akka.AkkaHttpApp
import com.moplay.platform.http.RESTError
import tapir._

object Main extends AkkaHttp {
	
	implicit override val system = ActorSystem("$bcId$ApiInternal")

	private val baseEndpoint: Endpoint[Unit, String, Unit, Nothing] =
    endpoint
      .in("api" / "v1.0")
      .errorOut(jsonBody[String])

}