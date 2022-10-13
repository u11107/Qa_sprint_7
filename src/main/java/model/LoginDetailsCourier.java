package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDetailsCourier {
    private String login;
    private String password;
}
