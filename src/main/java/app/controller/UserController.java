package app.controller;

import app.dto.CreateUserDto;
import app.security.UserService;
import app.user.Authority;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getItem(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUser(Integer.parseInt(id)));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> save(@RequestBody CreateUserDto user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/createAuthority/{name}")
    public ResponseEntity<?> createNewTask(@PathVariable("name") String name){
        Authority authority = new Authority();
        authority.setName(name);
        return ResponseEntity.ok(userService.saveAuthority(authority));
    }

    @GetMapping("/getAuth")
    public ResponseEntity<?> find(){
        return ResponseEntity.ok(userService.getAuthority(1));
    }
}
