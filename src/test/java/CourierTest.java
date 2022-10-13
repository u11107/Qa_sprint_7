/*
Логин курьера в системе
Создание курьера
 */

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
import static org.hamcrest.core.Is.is;

public class CourierTest {


    CourierMethod courierMethod;
    Courier courier;
    int courierId;
    LoginDetailsCourier loginDetailsCourier;



    @Before
    public void setUp() {
        courierMethod = new CourierMethod();
        courier = DataGeneratorCourier.getCourier();
        loginDetailsCourier = new LoginDetailsCourier(courier.getLogin(), courier.getPassword());
    }

    @After
    public void tearDown() {
        courierMethod.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Создание курьера с валидными данными")
    public void shouldCreatingCourierWithValidData() {
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание курьера без поля FirstName")
    public void shouldCreationCourierNoFirstName() {
        courier.setFirstName(null);
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("ok", is(true));
    }


    @Test
    @DisplayName("Создание курьера с пустым поле FirstName {}")
    public void shouldCreationCourierBlankFieldFirstName() {
        courier.setFirstName("");
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без поля Password")
    public void shouldCreationCourierNoPassword() {
        courier.setPassword(null);
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без поля Login")
    public void shouldCreationCourierNoLogin() {
        courier.setLogin(null);
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым поля Password")
    public void shouldCreationCourierBlankFieldsPassword() {
        courier.setPassword("");
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым поля Login")
    public void shouldCreationCourierBlankFieldsLogin() {
        courier.setLogin("");
        ValidatableResponse response = courierMethod.createCourier(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }


    @Test
    @DisplayName("Создание курьера c существующим в базе логином")
    public void shouldCreationCourierExistingFieldLogin() {
        Courier courier1 =new Courier("Ivan","123456","Robin");
        Courier courier2 =new Courier("Ivan","123456","Batman");
        ValidatableResponse response = courierMethod.createCourier(courier1);
        ValidatableResponse response1 = courierMethod.createCourier(courier2);
        response1.assertThat().statusCode(SC_CONFLICT)
                .and()
                .body("message", is("Этот логин уже используется"));
    }




}
