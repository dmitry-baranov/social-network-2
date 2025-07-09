package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Setter;

/**
 * PostUpdatePutRequest
 */

@Setter
@JsonTypeName("_post_update_put_request")
public class PostUpdatePutRequest {

  private String id;

  private String text;

  public PostUpdatePutRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostUpdatePutRequest(String id, String text) {
    this.id = id;
    this.text = text;
  }

  public PostUpdatePutRequest id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор поста
   *
   * @return id
   */
  @NotNull
  @Schema(name = "id", example = "1d535fd6-7521-4cb1-aa6d-031be7123c4d", description = "Идентификатор поста", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public PostUpdatePutRequest text(String text) {
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
    PostUpdatePutRequest postUpdatePutRequest = (PostUpdatePutRequest) o;
    return Objects.equals(this.id, postUpdatePutRequest.id) &&
        Objects.equals(this.text, postUpdatePutRequest.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text);
  }

  @Override
  public String toString() {
    String sb = "class PostUpdatePutRequest {\n" +
        "    id: " + toIndentedString(id) + "\n" +
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

