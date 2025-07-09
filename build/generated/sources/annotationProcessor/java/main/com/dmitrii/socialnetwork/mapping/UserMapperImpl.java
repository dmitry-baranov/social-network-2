package com.dmitrii.socialnetwork.mapping;

import com.dmitrii.socialnetwork.controller.model.UserDto;
import com.dmitrii.socialnetwork.controller.model.UserRegisterPostRequest;
import com.dmitrii.socialnetwork.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T22:51:33+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.7 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRegisterPostRequest request, PasswordEncoder encoder) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.passwordHash( mapPassword( request.getPassword(), encoder ) );
        user.username( request.getUsername() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.birthdate( request.getBirthdate() );
        user.biography( request.getBiography() );
        user.city( request.getCity() );

        user.id( java.util.UUID.randomUUID() );

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( user.getId() != null ) {
            userDto.setId( user.getId().toString() );
        }
        userDto.setFirstName( user.getFirstName() );
        userDto.setUsername( user.getUsername() );
        userDto.setLastName( user.getLastName() );
        userDto.setBirthdate( user.getBirthdate() );
        userDto.setBiography( user.getBiography() );
        userDto.setCity( user.getCity() );

        return userDto;
    }

    @Override
    public List<UserDto> toUserDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }
}
