package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import java.util.UUID;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * UserRegisterPost200Response
 */

@Setter
@JsonTypeName("_user_register_post_200_response")
public class UserRegisterPost200Response {

  private @Nullable UUID userId;

  public UserRegisterPost200Response userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   *
   * @return userId
   */

  @Schema(name = "user_id", example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user_id")
  public UUID getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegisterPost200Response userRegisterPost200Response = (UserRegisterPost200Response) o;
    return Objects.equals(this.userId, userRegisterPost200Response.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    String sb = "class UserRegisterPost200Response {\n" +
        "    userId: " + toIndentedString(userId) + "\n" +
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

