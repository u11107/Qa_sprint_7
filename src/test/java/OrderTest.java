import client.OrderMethod;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Orders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utills.DataGeneratorOrder;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

//@RunWith(Parameterized.class)
public class OrderTest {

    OrderMethod orderMethod;
    Orders orders;
    Integer track;

    @Before
    public void setUp() {
        orderMethod = new OrderMethod();
        orders = DataGeneratorOrder.getOrders();
        orderMethod.createOrders(orders);
    }

    @After
    public void tearDown() {
        orderMethod.cancelOrder(track.toString());
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrder() {
        ValidatableResponse response = orderMethod.createOrders(orders);
        track = response.extract().path("track");
        response.assertThat().statusCode(SC_CREATED)
                .and()
                .body("track", is(notNullValue()));
    }


}
