package utills;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.Courier;
import net.datafaker.Faker;


@Data
@AllArgsConstructor
public class DataGeneratorCourier {

    @Step("Создание случайных данных для курьера (логин, пароль, имя)")
    public static Courier getCourier() {
        Faker faker = new Faker();
        var login = faker.name().firstName();
        var password = faker.random().hex(9);
        var firstName = faker.name().firstName();
        return new Courier(login, password, firstName);
    }
}
