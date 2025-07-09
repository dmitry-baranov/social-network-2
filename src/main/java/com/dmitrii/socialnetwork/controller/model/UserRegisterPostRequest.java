package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

/**
 * UserRegisterPostRequest
 */

@Setter
@JsonTypeName("_user_register_post_request")
public class UserRegisterPostRequest {

  private @Nullable String username;

  private @Nullable String firstName;

  private @Nullable String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate birthdate;

  private @Nullable String biography;

  private @Nullable String city;

  private @Nullable String password;

  public UserRegisterPostRequest username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   *
   * @return username
   */

  @Schema(name = "username", example = "Имя пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public UserRegisterPostRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   *
   * @return firstName
   */

  @Schema(name = "first_name", example = "Имя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public UserRegisterPostRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   *
   * @return lastName
   */

  @Schema(name = "last_name", example = "Фамилия", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_name")
  public String getLastName() {
    return lastName;
  }

  public UserRegisterPostRequest birthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Дата рождения
   *
   * @return birthdate
   */
  @Valid
  @Schema(name = "birthdate", example = "2017-02-01", description = "Дата рождения", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("birthdate")
  public LocalDate getBirthdate() {
    return birthdate;
  }

  public UserRegisterPostRequest biography(String biography) {
    this.biography = biography;
    return this;
  }

  /**
   * Get biography
   *
   * @return biography
   */

  @Schema(name = "biography", example = "Хобби, интересы и т.п.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("biography")
  public String getBiography() {
    return biography;
  }

  public UserRegisterPostRequest city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   *
   * @return city
   */

  @Schema(name = "city", example = "Москва", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public UserRegisterPostRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   *
   * @return password
   */

  @Schema(name = "password", example = "Секретная строка", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegisterPostRequest userRegisterPostRequest = (UserRegisterPostRequest) o;
    return Objects.equals(this.username, userRegisterPostRequest.username) &&
        Objects.equals(this.firstName, userRegisterPostRequest.firstName) &&
        Objects.equals(this.lastName, userRegisterPostRequest.lastName) &&
        Objects.equals(this.birthdate, userRegisterPostRequest.birthdate) &&
        Objects.equals(this.biography, userRegisterPostRequest.biography) &&
        Objects.equals(this.city, userRegisterPostRequest.city) &&
        Objects.equals(this.password, userRegisterPostRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, firstName, lastName, birthdate, biography, city, password);
  }

  @Override
  public String toString() {
    String sb = "class UserRegisterPostRequest {\n" +
        "    username: " + toIndentedString(username) + "\n" +
        "    firstName: " + toIndentedString(firstName) + "\n" +
        "    lastName: " + toIndentedString(lastName) + "\n" +
        "    birthdate: " + toIndentedString(birthdate) + "\n" +
        "    biography: " + toIndentedString(biography) + "\n" +
        "    city: " + toIndentedString(city) + "\n" +
        "    password: " + toIndentedString(password) + "\n" +
        "}";
    return sb;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

