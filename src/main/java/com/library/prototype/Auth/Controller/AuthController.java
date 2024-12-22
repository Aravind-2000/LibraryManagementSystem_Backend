package com.library.prototype.Auth.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto) {
        return authService.registerUserService(signUpDto);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpDto signUpDto) {
        return authService.registerAdminService(signUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInDto signInDto) {
        return authService.loginService(signInDto);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPass(@Valid @RequestBody PasswordChangeRequest request) {
        return authService.updatePassword(request);
    }

    /*
     * @PostMapping("/logout")
     * public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest
     * logoutRequest){
     * return authService.logoutService(logoutRequest);
     * }
     */

}
