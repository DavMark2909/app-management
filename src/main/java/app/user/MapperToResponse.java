package app.user;

import app.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperToResponse {

    public UserResponseDto convertToResponse(User user){
        Set<Authority> authorities = user.getAuthorities();
        Set<String> strings = authorities.stream().map(auth -> auth.getName()).collect(Collectors.toSet());
        return UserResponseDto.builder().name(user.getName()).lastname(user.getLastName()).username(user.getUsername()).roles(strings).build();
    }
}
