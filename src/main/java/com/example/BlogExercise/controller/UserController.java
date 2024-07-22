package com.example.BlogExercise.controller;

import com.example.BlogExercise.domain.User;
import com.example.BlogExercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.updateUser(user, userId);

    }
}
