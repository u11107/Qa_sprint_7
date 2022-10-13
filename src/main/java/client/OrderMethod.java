package client;

import base.BaseClass;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Orders;

import static io.restassured.RestAssured.given;

public class OrderMethod extends BaseClass {

    private final static String ORDER = "/api/v1/orders/";

    private final static String CANCEL = "cancel";

    @Step("Создание заказа")
    public ValidatableResponse createOrders(Orders orders) {
        return given()
                .spec(spec())
                .body(orders)
                .when()
                .post(ORDER)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(String track) {
        return given()
                .spec(spec())
                .body(track)
                .when()
                .put(ORDER + CANCEL)
                .then();
    }


    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(spec())
                .when()
                .get(ORDER)
                .then();
    }
}
