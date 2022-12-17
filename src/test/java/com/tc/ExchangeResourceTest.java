package com.tc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ExchangeResourceTest {
    @Test
    public void testShouldReturn200IfNoError() {
        given()
                .when().get("/exchanges/01-20-2014")
                .then()
                .statusCode(200);
    }

    @Test
    public void testShouldReturn204IfWrongDate() {
        given()
                .when().get("/exchanges/01-20-2024")
                .then()
                .statusCode(204);
    }

    @Test
    public void testShouldReturn400IfBadFormatDate() {
        given()
                .when().get("/exchanges/2024/02/11")
                .then()
                .statusCode(404);
    }

    @Test
    public void testShouldReturn500IfBadParamRequest() {
        given()
                .when().get("/exchanges/aaaa")
                .then()
                .statusCode(500);
    }


    @Test
    public void testShouldReturn400IfEmptyParamRequest() {
        given()
                .when().get("/exchanges/")
                .then()
                .statusCode(404);
    }

}

