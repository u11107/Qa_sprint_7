import client.OrderMethod;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderListTest {

    OrderMethod orderMethod;

    @Before
    public void setUp() {
        orderMethod = new OrderMethod();
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderList() {
        ValidatableResponse response = orderMethod.getOrderList();
        response.assertThat().statusCode(SC_OK)
                .and()
                .body("orders", is(notNullValue()));
    }
}
