package com.acme.dbo.restassure;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

import static com.acme.dbo.Constants.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

public class ClientTest {
    private RequestSpecification request;

    @BeforeEach
    void prepare() {
        request = given()
                .baseUri(HOST)
                .port(PORT)
                .basePath(HOME_PATH)
                .header(X_API_VERSION, 1)
                .contentType(JSON)
                .accept(JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Test for Get Client By Id")
    void shouldGetClientById() {
        request.when()
                .get(CLIENT_ID, 1)
                .then()
                .statusCode(SC_OK)
                .body("id", is(1),
                        "login", is("admin@acme.com"));
    }

    @Test
    @DisplayName("Test for DELETE client by login")
    public void shouldDeleteClientByLogin() {
        String secret = UUID.randomUUID().toString();
        String userLogin = "test_" + randomInt(1_000, 10_000) + "@test.com";

        request
                .when()
                .body(String.format("{\n" +
                        " \"login\": \"%s\",\n" +
                        " \"salt\": \"some-salt\",\n" +
                        " \"secret\": \"%s\"" +
                        "}", userLogin, secret))
                .post(CLIENTS)
                .then().statusCode(SC_CREATED);

        request.when()
                .delete(CLIENT_LOGIN, userLogin)
                .then()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test for DELETE client by id")
    public void shouldDeleteClientById() {
        String secret = UUID.randomUUID().toString();
        String userLogin = "test_" + randomInt(1_000, 10_000) + "@test.com";

        int userId = request
                .when()
                .body("{\n" +
                        " \"login\": \"" + userLogin + "\",\n" +
                        " \"salt\": \"some-salt\",\n" +
                        " \"secret\": \"" + secret + "\"" +
                        "}")
                .post(CLIENTS)
                .then().extract().body().path("id");

        request
                .when()
                .delete(CLIENT_ID, userId)
                .then()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test for GET all clients")
    void shouldGetAllClients() {
        request
                .when()
                .get(CLIENTS)
                .then()
                .statusCode(SC_OK);
    }

    public static int randomInt(int min, int max) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }
}
