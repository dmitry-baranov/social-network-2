package com.dmitrii.socialnetwork.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * Пост пользователя
 */

@Setter
@Schema(name = "Post", description = "Пост пользователя")
public class PostDto {

  private @Nullable String id;

  private @Nullable String text;

  private @Nullable String authorUserId;

  public PostDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор поста
   *
   * @return id
   */

  @Schema(name = "id", example = "1d535fd6-7521-4cb1-aa6d-031be7123c4d", description = "Идентификатор поста", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public PostDto text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Текст поста
   *
   * @return text
   */

  @Schema(name = "text", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus mauris ultrices eros in cursus turpis massa.", description = "Текст поста", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public PostDto authorUserId(String authorUserId) {
    this.authorUserId = authorUserId;
    return this;
  }

  /**
   * Идентификатор пользователя
   *
   * @return authorUserId
   */

  @Schema(name = "author_user_id", description = "Идентификатор пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("author_user_id")
  public String getAuthorUserId() {
    return authorUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostDto postDto = (PostDto) o;
    return Objects.equals(this.id, postDto.id) &&
        Objects.equals(this.text, postDto.text) &&
        Objects.equals(this.authorUserId, postDto.authorUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, authorUserId);
  }

  @Override
  public String toString() {
    String sb = "class Post {\n" +
        "    id: " + toIndentedString(id) + "\n" +
        "    text: " + toIndentedString(text) + "\n" +
        "    authorUserId: " + toIndentedString(authorUserId) + "\n" +
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

