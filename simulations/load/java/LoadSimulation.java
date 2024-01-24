package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadSimulation extends Simulation {
    final String API_ADDRESS = System.getenv("API_ADDRESS");
    final String API_USERNAME = System.getenv("API_USERNAME");
    final String API_PASSWORD = System.getenv("API_PASSWORD");

    final HttpProtocolBuilder httpProtocol = http.baseUrl(API_ADDRESS);

    final ScenarioBuilder test_case = scenario("LoadSimulation")
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
                rampConcurrentUsers(0).to(240).during(90),
                constantConcurrentUsers(240).during(120),
                rampConcurrentUsers(240).to(0).during(90)
            ).protocols(httpProtocol)
        );
    }
}
