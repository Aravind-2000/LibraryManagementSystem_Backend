package com.library.prototype.Auth.Controller;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.library.prototype.Auth.User.Role;
import com.library.prototype.Auth.User.User;
import com.library.prototype.Auth.User.UserDto;
import com.library.prototype.Auth.User.UserRepository;
import com.library.prototype.Auth.config.JwtService;
import com.library.prototype.Auth.token.Token;
import com.library.prototype.Auth.token.TokenRepository;
import com.library.prototype.Auth.token.TokenType;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;
    

    public ResponseEntity<?> registerUserService(SignUpDto signUpDto) {

        if (repository.existsByUserEmail(signUpDto.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        var user = User.builder()
            .userId(signUpDto.getUserEmail())
            .screenName(signUpDto.getScreenName())
            .userEmail(signUpDto.getUserEmail())
            .password(passwordEncoder.encode(signUpDto.getPassword()))
            .role(Role.USER)
            .occupation(signUpDto.getOccupation())
            .studentObject(signUpDto.getStudent())
            .teacherObject(signUpDto.getTeacher())
            .build();
        repository.save(user);
        /*
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken); */
        new AuthenticationResponse();
        return new ResponseEntity<>(AuthenticationResponse.builder()
                        .responseMessage("NEW USER HAS BEEN REGISTERED")
                        .data(null).httpStatus(HttpStatus.OK).build(), HttpStatus.CREATED);
    }

    /* User DTO data
    new UserDto(user.getUserId(),user
                                        .getFirstName(),
                                        user.getLastName(), user
                                                        .getUsername(),
                                        user.getUserEmail(), user.getRole(), user.getAdditionalDetails())
     */

    public ResponseEntity<?> registerAdminService(SignUpDto signUpDto) {

        if (repository.existsByUserEmail(signUpDto.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        var user = User.builder()
            .userId(signUpDto.getUserEmail())
            .screenName(signUpDto.getScreenName())
            .userEmail(signUpDto.getUserEmail())
            .password(passwordEncoder.encode(signUpDto.getPassword()))
            .role(Role.ADMIN)
            .occupation(signUpDto.getOccupation())
                .build();
        repository.save(user);
        /*
         var savedUser = repository.save(user);
         var jwtToken = jwtService.generateToken(user);
         var refreshToken = jwtService.generateRefreshToken(user);
         saveUserToken(savedUser, jwtToken);
        */
        new AuthenticationResponse();
        return new ResponseEntity<>(AuthenticationResponse.builder()
                        .responseMessage("NEW ADMIN HAS BEEN REGISTERED")
                        .data(null).httpStatus(HttpStatus.OK).build(), HttpStatus.CREATED);
    }
    
    public ResponseEntity<?> loginService(SignInDto signInDto) {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var user = repository.findByUserEmail(signInDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not found in this E-Mail"));
            user = (User) authentication.getPrincipal();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken, refreshToken);
            return new ResponseEntity<>(AuthenticationResponse.builder()
                    .responseMessage("LOGIN SUCCESSFUL")
                    .data(new UserDto(user.getUserId(), user.getScreenName(),
                                      user.getUserEmail(), user.getRole(), 
                                      user.getOccupation(), user.getStudentObject(), user.getTeacherObject()))
                    .token(jwtToken).refreshToken(refreshToken).httpStatus(HttpStatus.OK).build(), HttpStatus.OK);

    }

    public ResponseEntity<?> updatePassword(PasswordChangeRequest passwordChangeRequest){
        if(!repository.existsByUserEmail(passwordChangeRequest.getEmail().trim())) {
            return new ResponseEntity<>(AuthenticationResponse.builder()
                    .responseMessage("E-Mail Doesn't Exist")
                    .httpStatus(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }
        User user = repository.findUserByEmail(passwordChangeRequest.getEmail().trim());
        user.setPassword(encoder.encode(passwordChangeRequest.getPassword()));
        repository.save(user);
        return new ResponseEntity<>(AuthenticationResponse.builder()
                .responseMessage("Password Change successfully")
                .httpStatus(HttpStatus.OK).build(), HttpStatus.OK);
    }


    public ResponseEntity<?> logoutService(LogoutRequest logoutRequest){

        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(logoutRequest.getUserId(), logoutRequest.getRefreshToken());
        if (validUserTokens.isEmpty()){
            return new ResponseEntity<>(AuthenticationResponse.builder().
                    httpStatus(HttpStatus.UNAUTHORIZED).responseMessage("NO DATA FOUND").build(), HttpStatus.NO_CONTENT);
        }
        tokenRepository.deleteAll(validUserTokens);
        return new ResponseEntity<>(AuthenticationResponse.builder()
                .httpStatus(HttpStatus.OK).responseMessage("LOGOUT SUCCESSFUL").build(), HttpStatus.OK);
    }


    private void saveUserToken(User user, String jwtToken, String refreshToken) {
            var token = Token.builder()
                            .user(user)
                            .token(jwtToken)
                            .tokenType(TokenType.BEARER)
                            .expired(false)
                            .revoked(false)
                            .refreshToken(refreshToken)
                            .build();
            tokenRepository.save(token);
    }
    
    private void revokeAllUserTokens(User user) {
            List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
            if (validUserTokens.isEmpty())
                    return;
            validUserTokens.forEach(token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
    }


//    public void refreshToken(
//                    HttpServletRequest request,
//                    HttpServletResponse response) throws IOException {
//            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//            final String refreshToken;
//            final String userEmail;
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                    return;
//            }
//            refreshToken = authHeader.substring(7);
//            userEmail = jwtService.extractUserName(refreshToken);
//            if (userEmail != null) {
//                    var user = this.repository.findByUserEmail(userEmail)
//                                    .orElseThrow();
//                    if (jwtService.isTokenValid(refreshToken, user)) {
//                            var accessToken = jwtService.generateToken(user);
//                            revokeAllUserTokens(user);
//                            saveUserToken(user, accessToken);
//                            var authResponse = AuthenticationResponse.builder()
//                                            .token(accessToken)
//                                            .refreshToken(refreshToken)
//                                            .build();
//                            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//                    }
//            }
//        }

}
