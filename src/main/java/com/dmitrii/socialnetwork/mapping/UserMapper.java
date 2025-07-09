package com.dmitrii.socialnetwork.mapping;

import com.dmitrii.socialnetwork.controller.model.UserDto;
import com.dmitrii.socialnetwork.controller.model.UserRegisterPostRequest;
import com.dmitrii.socialnetwork.model.User;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
  @Mapping(target = "passwordHash", source = "password", qualifiedByName = "mapPassword")
  User toUser(UserRegisterPostRequest request, @Context PasswordEncoder encoder);


  UserDto toUserDto(User user);
  List<UserDto> toUserDto(List<User> users);

  @Named("mapPassword")
  default String mapPassword(String rawPassword, @Context PasswordEncoder encoder) {
    return encoder.encode(rawPassword);
  }


}