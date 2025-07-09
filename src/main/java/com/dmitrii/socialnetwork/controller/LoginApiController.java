package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.api.LoginApi;
import com.dmitrii.socialnetwork.controller.model.LoginPost200Response;
import com.dmitrii.socialnetwork.controller.model.LoginPost500Response;
import com.dmitrii.socialnetwork.controller.model.LoginPostRequest;
import com.dmitrii.socialnetwork.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginApiController implements LoginApi {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginPostRequest.getUsername(),
            loginPostRequest.getPassword()
        )
    );

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String jwt = jwtService.generateToken(userDetails);
    return ResponseEntity.ok(new LoginPost200Response().token(jwt));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<LoginPost500Response> handleBadCredentials(BadCredentialsException ex) {
    LoginPost500Response errorResponse = new LoginPost500Response()
        .message("Invalid username or password")
        .requestId(null)
        .code(500);
    return ResponseEntity.status(500).body(errorResponse);
  }
}
