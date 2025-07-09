package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.model.ErrorResponse;
import io.jsonwebtoken.JwtException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//  @ExceptionHandler(JwtException.class)
//  public ResponseEntity<ErrorResponse> handleAllExceptions(JwtException ex) {
//    ErrorResponse error = new ErrorResponse(
//        LocalDateTime.now(),
//        401,
//        "Unauthorized",
//        ex.getMessage()
//    );
//    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//  }
}
