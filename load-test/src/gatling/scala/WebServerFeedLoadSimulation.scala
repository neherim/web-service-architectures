import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WebServerFeedLoadSimulation extends Simulation {
  val httpConf = http
    .baseURL("http://localhost:8080")
    .acceptHeader("*/*")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")


  val feed = scenario("Get feed load simulation 5000 users")
    .during(20 minutes) {
      exec(
        http("Feed request")
          .get("/feed")
          .check(status is 200))
    }

  setUp(
    feed.inject(rampUsers(5000) over (15 minutes))
  ).protocols(httpConf)
}
