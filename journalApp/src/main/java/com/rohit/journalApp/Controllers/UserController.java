package com.rohit.journalApp.Controllers;

import com.rohit.journalApp.Entity.Users;
import com.rohit.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // ✅ Get user by ID
    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId userId) {
        return userService.getUserById(userId);
    }

    // ✅ Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    // ✅ Update user by username
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserByUsername(@PathVariable String username, @RequestBody Users tempUser) {
        return userService.updateUserByUsername(username, tempUser);
    }

    // ✅ Update user by ID
    @PutMapping("/id/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable ObjectId userId, @RequestBody Users tempUser) {
        return userService.updateUserById(userId, tempUser);
    }

    // ✅ Delete all users
    @DeleteMapping
    public ResponseEntity<?> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    // ✅ Delete user by username
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/id/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId userId) {
        return userService.deleteUserById(userId);
    }
}
