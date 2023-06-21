package com.library.prototype.Auth.Controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String responseMessage;

    private HttpStatus httpStatus;
    
    private Object data;

    private String token;

    private String refreshToken;
}
