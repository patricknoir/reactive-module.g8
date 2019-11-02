package $organization$.$boundedContextId$.api.internal

import akka.actor.ActorSystem
import com.moplay.platform.akka.AkkaHttpApp
import com.moplay.platform.http.RESTError
import tapir._

object Main extends AkkaHttp {
	
	implicit override val system = ActorSystem("$boundedContextId$ApiInternal")

	private val baseEndpoint: Endpoint[Unit, RESTError, Unit, Nothing] =
    endpoint
      .in("api" / "v1.0")
      .errorOut(jsonBody[RESTError])

}