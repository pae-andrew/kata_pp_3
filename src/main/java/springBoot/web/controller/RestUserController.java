package springBoot.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBoot.web.model.User;
import springBoot.web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/*")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    public void removeUser(@RequestParam Long id) {
        userService.removeUser(id);
    }

    @PutMapping
    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public void updateUser(@RequestParam Long id, String firstName, String password, String lastName, String email, int age, String role) {
        password = userService.ifPasswordNull(id, password);
        userService.updateUser(new User(id, firstName, lastName, age, email, password), role);
    }

    @PostMapping
    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public void addUser(@RequestParam String firstName, String password, String lastName, String email, int age, String role) {
        userService.addUser(new User(firstName, password, lastName, email, age), role);
    }

    @GetMapping
    @RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUser(HttpSession session) {
        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}