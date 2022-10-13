package client;

import base.BaseClass;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.LoginDetailsCourier;

import static io.restassured.RestAssured.given;

public class CourierMethod extends BaseClass {

    private static final String COURIER = "api/v1/courier/";
    private static final String LOGIN = "login";
    private static final String COURIER_ID = "courierId";

    @Step("Создание курьера с параметрами")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(spec())
                .body(courier)
                .when()
                .post(COURIER)
                .then()
                .log().all();
    }

    @Step("Вход курьера в системе {loginDetails}")
    public ValidatableResponse loginCourier(LoginDetailsCourier loginDetails) {
        return given()
                .spec(spec())
                .log().all()
                .body(loginDetails)
                .when()
                .post(COURIER + LOGIN)
                .then()
                .log().all();
    }

    @Step("Удаление курьера с id {courierId}")
    public void deleteCourier(int courierId) {
        given()
                .spec(spec())
                .log().all()
                .when()
                .delete(COURIER + COURIER_ID)
                .then()
                .log().all();
    }
}
