package engine.rest;

import engine.model.User;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> register(@Valid @RequestBody User user) {

        return userService.saveUser(user) ? ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }
}