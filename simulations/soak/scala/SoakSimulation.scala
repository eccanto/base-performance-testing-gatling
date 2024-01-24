package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SoakSimulation extends Simulation {
    val API_ADDRESS = sys.env.get("API_ADDRESS").get
    val API_USERNAME = sys.env.get("API_USERNAME").get
    val API_PASSWORD = sys.env.get("API_PASSWORD").get

    val httpProtocol = http.baseUrl(API_ADDRESS)

    val test_case = scenario("SoakSimulation")
        .exec(
            http("Authentication")
                .get("/api/token")
                .basicAuth(API_USERNAME, API_PASSWORD)
                .check(status.is(200))
                .check(jsonPath("$.access").saveAs("jwt_token"))
        )
        .exitHereIfFailed
        .exec(
            http("GetUsers")
                .get("/api/users")
                .header("Authorization", "JWT ${jwt_token}")
                .check(status.is(200))
        )

    setUp(
        test_case.inject(
            constantConcurrentUsers(220).during(600)
        ).protocols(httpProtocol)
    )
}
