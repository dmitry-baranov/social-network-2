package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * LoginPost200Response
 */
@Setter
@JsonTypeName("_login_post_200_response")
public class LoginPost200Response {

  private @Nullable String token;

  public LoginPost200Response token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   *
   * @return token
   */

  @Schema(name = "token", example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginPost200Response loginPost200Response = (LoginPost200Response) o;
    return Objects.equals(this.token, loginPost200Response.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public String toString() {
    String sb = "class LoginPost200Response {\n" +
        "    token: " + toIndentedString(token) + "\n" +
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

