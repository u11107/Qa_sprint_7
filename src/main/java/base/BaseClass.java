package base;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClass {

    private final static String URL = "https://qa-scooter.praktikum-services.ru/api/v1";

    protected static RequestSpecification spec() {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(URL);
    }
}
