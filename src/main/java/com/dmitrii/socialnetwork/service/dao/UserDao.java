package com.dmitrii.socialnetwork.service.dao;

import com.dmitrii.socialnetwork.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

  private static final String SQL_USER_FIND_BY_ID = "SELECT * FROM users WHERE id = :id";

  private static final String SQL_USER_FIND_BY_USERNAME = "SELECT * FROM users WHERE username = :username";

  private static final String SQL_USER_FIND_BY_USERNAME_AND_LASTNAME = "SELECT * FROM users WHERE first_name LIKE :firstName AND last_name LIKE :lastName";

  private static final String SQL_USER_CREATE =
      "INSERT INTO users (id, username, first_name, last_name, birthdate, biography, city, password_hash) "
          +
          "VALUES (:id, :username, :firstName, :lastName, :birthdate, :biography, :city, :passwordHash)";

  private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> User.builder()
      .id((UUID) rs.getObject("id"))
      .username(rs.getString("username"))
      .firstName(rs.getString("first_name"))
      .lastName(rs.getString("last_name"))
      .birthdate(rs.getObject("birthdate", java.time.LocalDate.class))
      .biography(rs.getString("biography"))
      .city(rs.getString("city"))
      .passwordHash(rs.getString("password_hash"))
      .build();

  private final NamedParameterJdbcTemplate jdbc;

  public UserDao(NamedParameterJdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Transactional
  public boolean saveUser(User user) {
    return jdbc.update(SQL_USER_CREATE, new MapSqlParameterSource()
        .addValue("id", user.getId())
        .addValue("username", user.getUsername())
        .addValue("firstName", user.getFirstName())
        .addValue("lastName", user.getLastName())
        .addValue("birthdate", user.getBirthdate())
        .addValue("biography", user.getBiography())
        .addValue("city", user.getCity())
        .addValue("passwordHash", user.getPasswordHash())) > 0;
  }

  public Optional<User> findById(UUID id) {
    return jdbc.query(SQL_USER_FIND_BY_ID, new MapSqlParameterSource()
        .addValue("id", id), USER_ROW_MAPPER).stream().findAny();
  }

  public Optional<User> findByUsername(String username) {
    return jdbc.query(SQL_USER_FIND_BY_USERNAME, new MapSqlParameterSource()
        .addValue("username", username), USER_ROW_MAPPER).stream().findAny();
  }

  public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
    return jdbc.query(SQL_USER_FIND_BY_USERNAME_AND_LASTNAME, new MapSqlParameterSource()
        .addValue("firstName", firstName + "%")
        .addValue("lastName", lastName + "%"), USER_ROW_MAPPER);
  }
}
