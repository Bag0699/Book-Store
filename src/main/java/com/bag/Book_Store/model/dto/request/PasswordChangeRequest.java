package com.bag.Book_Store.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {

    private Long userId;

    @NotBlank(message = "La nueva contraseña no puede estar vacía.")
    private String newPassword;

    @NotBlank(message = "La nueva contraseña no puede estar vacía.")
    private String confirmPassword;
}
