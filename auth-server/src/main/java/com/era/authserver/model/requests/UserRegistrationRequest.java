package com.era.authserver.model.requests;

import com.era.authserver.model.validators.Email;
import com.era.authserver.model.validators.UsernameExists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @UsernameExists
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Pattern(regexp = "/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/",
            flags = Pattern.Flag.UNICODE_CASE,
            message = "Не корректное имя пользователя")
    private String username;

    @NotBlank(message = "Электронная почта не может быть пустой")
    @Email(message = "Некорректная электронная почта")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 10, max = 64, message = "Пароль должен содержать от 10 до 64 символов")
    private String password;

}
