package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * LoginPostRequest
 */

@Setter
@JsonTypeName("_login_post_request")
public class LoginPostRequest {

  private @Nullable String username;

  private @Nullable String password;

  public LoginPostRequest username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Идентификатор пользователя
   *
   * @return username
   */

  @Schema(name = "username", description = "Идентификатор пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public LoginPostRequest password(String password) {
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
    LoginPostRequest loginPostRequest = (LoginPostRequest) o;
    return Objects.equals(this.username, loginPostRequest.username) &&
        Objects.equals(this.password, loginPostRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    String sb = "class LoginPostRequest {\n" +
        "    username: " + toIndentedString(username) + "\n" +
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

