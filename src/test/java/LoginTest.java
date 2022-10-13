import client.CourierMethod;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.LoginDetailsCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utills.DataGeneratorCourier;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;


public class LoginTest {

    CourierMethod courierMethod;
    Courier courier;
    int courierId;
    LoginDetailsCourier loginDetailsCourier;


    @Before
    public void setUp() {
        courierMethod = new CourierMethod();
        courier = DataGeneratorCourier.getCourier();
        courierMethod.createCourier(courier);
        loginDetailsCourier = new LoginDetailsCourier(courier.getLogin(), courier.getPassword());
    }

    @After
    public void tearDown() {
        courierMethod.deleteCourier(courierId);
    }


    @Test
    @DisplayName("Авторизация курьера в системе с валидными значениями")
    public void shouldLoginCourier() {
        ValidatableResponse response = courierMethod.loginCourier(loginDetailsCourier);
        response.assertThat().statusCode(SC_OK)
                .and()
                .body("id", is(notNullValue()));
    }


    @Test
    @DisplayName("Попытка авторизации в системе с несуществющиеми Login в системе")
    public void shouldNotLoginCourier() {
        ValidatableResponse response = courierMethod
                .loginCourier(new LoginDetailsCourier("Marks", courier.getPassword()));
        response.assertThat().statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }


    @Test
    @DisplayName("Попытка авторизации в системе с несуществющиеми Password в системе")
    public void shouldNotPasswordCourier() {
        ValidatableResponse response = courierMethod
                .loginCourier(new LoginDetailsCourier(courier.getLogin(), "9374hfih"));
        response.assertThat().statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Aвторизация без значения поля password в запросе")
    public void shouldCourierLoginWithoutPasswordField() {
        ValidatableResponse response = courierMethod
                .loginCourier(new LoginDetailsCourier(courier.getLogin(), null));
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Авторизации без значения поля Login в запросе")
    public void shouldCourierLoginWithoutLoginField() {
        ValidatableResponse response = courierMethod
                .loginCourier(new LoginDetailsCourier(null, courier.getPassword()));
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }
}


