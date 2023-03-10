package springBoot.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springBoot.web.model.User;
import springBoot.web.service.UserService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/admin")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@AuthenticationPrincipal User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.addAll(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping
    public ResponseEntity<List<?>> removeUser(@RequestParam Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<List<?>> updateUser(@RequestParam Long id, String firstName,
                                              String password, String lastName,
                                              String email, int age, String role) {

        password = userService.ifPasswordNull(id, password);
        userService.updateUser(new User(id, firstName, lastName, age, email, password), role);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<List<?>> addUser(@RequestParam String firstName,
                                           String password, String lastName,
                                           String email, int age, String role) {

        userService.addUser(new User(firstName, password, lastName, email, age), role);
        return ResponseEntity.ok().build();
    }
}