package com.dmitrii.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "passwordHash")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  private UUID id;

  @NotBlank(message = "Username is required")
  private String username;

  @NotBlank(message = "First name is required")
  private String firstName;

  private String lastName;

  private LocalDate birthdate;

  private String biography;

  private String city;

  @JsonIgnore
  private String passwordHash;
}

