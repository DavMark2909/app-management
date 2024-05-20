package app.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateUserDto {
    private String name;
    private String lastname;
    private String username;
    private String password;
    private Set<String> authorities;
}
