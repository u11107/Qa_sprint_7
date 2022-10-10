import io.restassured.RestAssured;
import model.Courier;
import org.junit.After;
import org.junit.Before;

public class BaseClass {

    Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @After
    public void after() {

    }

}
