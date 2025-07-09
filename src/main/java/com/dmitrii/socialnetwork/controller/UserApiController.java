package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.api.UserApi;
import com.dmitrii.socialnetwork.controller.model.ErrorResponse;
import com.dmitrii.socialnetwork.controller.model.UserDto;
import com.dmitrii.socialnetwork.controller.model.UserRegisterPost200Response;
import com.dmitrii.socialnetwork.controller.model.UserRegisterPostRequest;
import com.dmitrii.socialnetwork.exception.UsernameAlreadyExistsException;
import com.dmitrii.socialnetwork.mapping.UserMapper;
import com.dmitrii.socialnetwork.model.User;
import com.dmitrii.socialnetwork.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.social-network.base-path:}")
@RequiredArgsConstructor
public class UserApiController implements UserApi {

  private final UserService userService;
  private final UserMapper userMapper;
  private final PasswordEncoder encoder;

  @Override
  public ResponseEntity<UserDto> getUserById(UUID id) {
    User user = userService.getUserById(id);
    UserDto userDto = userMapper.toUserDto(user);
    return ResponseEntity.ok(userDto);
  }

  @Override
  public ResponseEntity<UserDto> getUserByUsername(String username) {
    User user = userService.getUserByUsername(username);
    UserDto userDto = userMapper.toUserDto(user);
    return ResponseEntity.ok(userDto);
  }

  @Override
  public ResponseEntity<UserRegisterPost200Response> register(
      UserRegisterPostRequest userRegisterPostRequest
  ) {
    User user = userService.register(userMapper.toUser(userRegisterPostRequest, encoder));
    UserRegisterPost200Response response = new UserRegisterPost200Response().userId(user.getId());
    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<List<UserDto>> searchUser(String firstName, String lastName) {
    List<User> users = userService.searchUser(firstName, lastName);
    return ResponseEntity.ok().body(userMapper.toUserDto(users));
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(NoSuchElementException ex) {
    ErrorResponse error = new ErrorResponse(
        LocalDateTime.now(),
        404,
        "User not found",
        ex.getMessage()
    );
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UsernameAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(
      UsernameAlreadyExistsException ex
  ) {
    ErrorResponse error = new ErrorResponse(
        LocalDateTime.now(),
        400,
        "Username already exist",
        ex.getMessage()
    );
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
