package com.dmitrii.socialnetwork.service;

import com.dmitrii.socialnetwork.exception.JdbcExceptionUtil;
import com.dmitrii.socialnetwork.exception.UsernameAlreadyExistsException;
import com.dmitrii.socialnetwork.model.User;
import com.dmitrii.socialnetwork.service.dao.UserDao;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserDao repo;

  public User register(User user) {
    try {
      log.info("Attempting to register user: {}", user.getUsername());
      repo.saveUser(user);
      log.info("User registered successfully: {}", user.getUsername());
      return user;
    } catch (DataIntegrityViolationException ex) {
      if (JdbcExceptionUtil.isDuplicateKey(ex)) {
        log.error("User name already exists: {}", user.getUsername());
        throw new UsernameAlreadyExistsException(user.getUsername(), ex);
      }
      throw ex;
    }
  }

  public User getUserById(UUID id) {
    log.debug("Looking up user by ID: {}", id);
    return repo.findById(id).orElseThrow(
        () -> {
          log.error("User not found by id: {}", id);
          return new NoSuchElementException(String.format("User not found by id: %s", id));
        }
    );
  }

  public User getUserByUsername(String username) {
    log.debug("Looking up user by username: {}", username);
    return repo.findByUsername(username).orElseThrow(
        () -> {
          log.error("User not found by username: {}", username);
          return new NoSuchElementException(
              String.format("User not found by username: %s", username));
        }
    );
  }

  public List<User> searchUser(String firstName, String lastName) {
    log.debug("Looking up user by first name: {} and last name: {}", firstName, lastName);
    return repo.findByFirstNameAndLastName(firstName, lastName);
  }
}
