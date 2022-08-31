package com.era.apiuser.model.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Pattern(regexp = "/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/",
            flags = Pattern.Flag.UNICODE_CASE,
            message = "Не корректное имя пользователя")
    private String username;

    @NotBlank(message = "Электронная почта не может быть пустой")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.UNICODE_CASE,
            message = "Не корректный email")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 10, max = 64, message = "Пароль должен содержать от 10 до 64 символов")
    private String password;

}
