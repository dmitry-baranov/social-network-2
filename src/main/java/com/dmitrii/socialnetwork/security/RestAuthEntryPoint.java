package com.dmitrii.socialnetwork.security;

import com.dmitrii.socialnetwork.controller.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestAuthEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;
  private final BearerTokenAuthenticationEntryPoint defaultEntryPoint =
      new BearerTokenAuthenticationEntryPoint();

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException {
    if (authException instanceof InsufficientAuthenticationException
        || authException.getCause() instanceof JwtException) {
      ErrorResponse error = new ErrorResponse(
          LocalDateTime.now(),
          HttpStatus.UNAUTHORIZED.value(),
          "Unauthorized",
          authException.getMessage()
      );
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write(mapper.writeValueAsString(error));
    } else {
      defaultEntryPoint.commence(request, response, authException);
    }
  }
}
