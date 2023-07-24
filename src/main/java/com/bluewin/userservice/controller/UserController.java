package com.bluewin.userservice.controller;

import com.bluewin.userservice.model.User;
import com.bluewin.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bluewin.userservice.exceptions.*;



@ControllerAdvice
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // To enable Testing with postman, Post endpoint for adding users
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Put endpoint for updating
    @PutMapping("/{id}/quota")
    public ResponseEntity<User> updateUserQuota(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUserQuota(id, user.getEmailQuota());
        return ResponseEntity.ok(updatedUser);
    }

    // Get endpoint for retreiving
    @GetMapping("/{id}/quota")
    public ResponseEntity<Integer> getUserQuota(@PathVariable Long id) {
        int userQuota = userService.getUserQuota(id);
        return ResponseEntity.ok(userQuota);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}


