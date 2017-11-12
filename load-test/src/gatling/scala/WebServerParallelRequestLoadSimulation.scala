import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WebServerParallelRequestLoadSimulation extends Simulation {
  val httpConf = http
    .baseURL("http://localhost:8080")
    .acceptHeader("*/*")
    .userAgentHeader("Gatling")

  val feed = scenario("Get feed load simulation 1000 users")
    .during(10 minutes) {
      exec(
        http("Feed request")
          .get("/feed")
          .check(status is 200))
    }

  setUp(
    feed.inject(rampUsers(1000) over (5 minutes))
  ).protocols(httpConf)
}
