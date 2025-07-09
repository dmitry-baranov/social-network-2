package com.dmitrii.socialnetwork.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

  public UsernameAlreadyExistsException(String username) {
    super(String.format("User with username '%s' already exists", username));
  }

  public UsernameAlreadyExistsException(String username, Throwable cause) {
    super(String.format("User with username '%s' already exists", username), cause);
  }
}
