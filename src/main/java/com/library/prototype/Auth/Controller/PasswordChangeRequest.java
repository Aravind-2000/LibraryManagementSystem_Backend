package com.library.prototype.Auth.Controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {

    @NotBlank
    public String email;

    @NotBlank
    public String password;
}
