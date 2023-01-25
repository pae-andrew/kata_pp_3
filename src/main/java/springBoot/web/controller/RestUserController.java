package springBoot.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBoot.web.model.User;
import springBoot.web.service.UserService;

import java.util.List;


@RestController
@RequestMapping("api/admin")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "users")
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping(value = "remove")
    public ResponseEntity<List<?>> removeUser(@RequestParam Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update")
    public ResponseEntity<List<?>> updateUser(@RequestParam Long id, String firstName,
                                              String password, String lastName,
                                              String email, int age, String role) {

        password = userService.ifPasswordNull(id, password);
        userService.updateUser(new User(id, firstName, lastName, age, email, password), role);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addUser")
    public ResponseEntity<List<?>> addUser(@RequestParam String firstName,
                                           String password, String lastName,
                                           String email, int age, String role) {

        userService.addUser(new User(firstName, password, lastName, email, age), role);
        return ResponseEntity.ok().build();
    }
}