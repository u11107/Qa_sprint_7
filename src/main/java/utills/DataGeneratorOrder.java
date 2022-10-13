package utills;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import model.Orders;
import net.datafaker.Faker;

import java.util.Collections;



@AllArgsConstructor
public class DataGeneratorOrder {


    @Step("Создание случайных данных для курьера (логин, пароль, имя)")
    public static Orders getOrders() {
        Faker faker = new Faker();
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var address = faker.address().fullAddress();
        var metroStation = faker.address().state();
        var phone = faker.phoneNumber().phoneNumber();
        var rentTime = faker.number().randomDigit();
        var deliveryDate = faker.date().birthday().toString();
        var comment = faker.construction().materials();
        var scooterColor = Collections.singletonList(faker.color().name());
        return new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, scooterColor);
    }

    public static void main(String[] args) {
        System.out.println(getOrders() );
    }

}
