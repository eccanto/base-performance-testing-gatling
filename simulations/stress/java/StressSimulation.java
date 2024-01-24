package computerdatabase;

import java.time.Duration;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StressSimulation extends Simulation {
    final String API_ADDRESS = System.getenv("API_ADDRESS");
    final String API_USERNAME = System.getenv("API_USERNAME");
    final String API_PASSWORD = System.getenv("API_PASSWORD");

    final HttpProtocolBuilder httpProtocol = http.baseUrl(API_ADDRESS);

    final ScenarioBuilder test_case = scenario("StressSimulation")
        .exec(
            http("Authentication")
                .get("/api/token")
                .basicAuth(API_USERNAME, API_PASSWORD)
                .check(status().is(200))
                .check(jsonPath("$.access").saveAs("jwt_token"))
        )
        .exitHereIfFailed()
        .exec(
            http("GetUsers")
                .get("/api/users")
                .header("Authorization", "JWT ${jwt_token}")
                .check(status().is(200))
        );

    {
        setUp(
            test_case.injectClosed(
                rampConcurrentUsers(0).to(360).during(300)
            ).protocols(httpProtocol)
        );
    }
}
