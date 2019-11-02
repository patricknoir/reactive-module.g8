package $organization;format="package"$.$bcName$.api.external

import akka.actor.ActorSystem
import com.moplay.http.akkahttp.AkkaHttpApp
import $organization;format="package"$.$bcName$.api.external.endpoints.ExternalEndpointsV1
import com.softwaremill.sttp.{ SttpBackend, SttpBackendOptions }
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import tapir.server.akkahttp._
import scala.concurrent.duration._
import scala.concurrent.{ ExecutionContext, Future }

object Main extends AkkaHttpApp {

  implicit override def system: ActorSystem = ActorSystem("$bcId$ApiInternal")

  implicit val sttpBackend: SttpBackend[Future, Nothing] =
    AkkaHttpBackend.usingActorSystem(
      actorSystem = system,
      options = SttpBackendOptions.connectionTimeout(5 seconds))(ExecutionContext.global)

  override protected def routes =
    ExternalEndpointsV1.helloworld.toRoute(_ => Future.successful(Right("Hello World!")))

  startServer("0.0.0.0", 8080, system)
}
