package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

/**
 * User
 */

@Setter
public class UserDto {

  private @Nullable String id;

  private @Nullable String username;

  private @Nullable String firstName;

  private @Nullable String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate birthdate;

  private @Nullable String biography;

  private @Nullable String city;

  public UserDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор пользователя
   *
   * @return id
   */

  @Schema(name = "id", description = "Идентификатор пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public UserDto firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Имя пользователя
   *
   * @return username
   */

  @Schema(name = "username", example = "Имя пользователя", description = "Имя пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public UserDto lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Имя
   *
   * @return firstName
   */

  @Schema(name = "first_name", example = "Имя", description = "Имя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  /**
   * Фамилия
   *
   * @return lastName
   */

  @Schema(name = "last_name", example = "Фамилия", description = "Фамилия", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_name")
  public String getLastName() {
    return lastName;
  }

  public UserDto birthdate(LocalDate birthdate) {
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

  public UserDto biography(String biography) {
    this.biography = biography;
    return this;
  }

  /**
   * Интересы
   *
   * @return biography
   */

  @Schema(name = "biography", example = "Хобби, интересы и т.п.", description = "Интересы", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("biography")
  public String getBiography() {
    return biography;
  }

  public UserDto city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Город
   *
   * @return city
   */

  @Schema(name = "city", example = "Москва", description = "Город", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDto userDto = (UserDto) o;
    return Objects.equals(this.id, userDto.id) &&
        Objects.equals(this.username, userDto.username) &&
        Objects.equals(this.firstName, userDto.firstName) &&
        Objects.equals(this.lastName, userDto.lastName) &&
        Objects.equals(this.birthdate, userDto.birthdate) &&
        Objects.equals(this.biography, userDto.biography) &&
        Objects.equals(this.city, userDto.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstName, lastName, birthdate, biography, city);
  }

  @Override
  public String toString() {
    String sb = "class User {\n" +
        "    id: " + toIndentedString(id) + "\n" +
        "    username: " + toIndentedString(username) + "\n" +
        "    firstName: " + toIndentedString(firstName) + "\n" +
        "    lastName: " + toIndentedString(lastName) + "\n" +
        "    birthdate: " + toIndentedString(birthdate) + "\n" +
        "    biography: " + toIndentedString(biography) + "\n" +
        "    city: " + toIndentedString(city) + "\n" +
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

