package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Setter;

/**
 * PostCreatePostRequest
 */

@Setter
@JsonTypeName("_post_create_post_request")
public class PostCreatePostRequest {

  private String text;

  public PostCreatePostRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostCreatePostRequest(String text) {
    this.text = text;
  }

  public PostCreatePostRequest text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Текст поста
   *
   * @return text
   */
  @NotNull
  @Schema(name = "text", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus mauris ultrices eros in cursus turpis massa.", description = "Текст поста", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostCreatePostRequest postCreatePostRequest = (PostCreatePostRequest) o;
    return Objects.equals(this.text, postCreatePostRequest.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    String sb = "class PostCreatePostRequest {\n" +
        "    text: " + toIndentedString(text) + "\n" +
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

