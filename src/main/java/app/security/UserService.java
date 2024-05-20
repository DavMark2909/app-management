package app.security;

import app.dto.CreateUserDto;
import app.dto.UserResponseDto;
import app.repository.AuthorityRepository;
import app.repository.TaskRepository;
import app.repository.UserRepository;
import app.user.Authority;
import app.user.MapperToResponse;
import app.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private MapperToResponse mapperToResponse;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Could not find username: " + username));
    }

    public UserResponseDto getUser(int id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find a user"));
        return mapperToResponse.convertToResponse(user);
    }

    @Transactional
    public UserResponseDto saveUser(CreateUserDto dto){
        Set<Authority> authoritySet = new HashSet<>();
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setLastName(dto.getLastname());
        user.setName(dto.getName());
        for (String authName : dto.getAuthorities()){
            Authority auth = authorityRepository.findByName(authName).orElseGet(() -> {
                Authority newAuth = new Authority();
                newAuth.setName(authName);
                return authorityRepository.save(newAuth);
            });
            authoritySet.add(auth);
        }
        user.setAuthorities(authoritySet);
        User u = userRepository.save(user);
        return mapperToResponse.convertToResponse(u);
    }

    public Authority getAuthority(int id){
        return authorityRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find authority"));
    }

    public Authority saveAuthority(Authority authority){
        return authorityRepository.save(authority);
    }

    public Authority getAuthority(String name){
        return authorityRepository.findByName(name).orElseThrow(() -> new RuntimeException("Could not find authority"));
    }
}
