package com.tc;

import com.tc.exceptions.BusinessException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ExchangeServiceTest {
    @Test
    public void testExtensionIdAsyncEndpoint() {
        given()
                .when().get("/exchanges/01-20-2014")
                .then()
                .statusCode(200);
    }
}

