package com.library.prototype.Auth.Controller;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SignInDto {

    @NonNull
    private String email;

    @NonNull
    String password;

}
