package com.rohit.journalApp.Service;

import com.rohit.journalApp.Entity.Users;
import com.rohit.journalApp.Repos.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Get all users
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    // ✅ Get user by username
    public ResponseEntity<?> getUserByUsername(String username) {
        Users user = userRepository.findUsersByName(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok(user);
    }

    // ✅ Get user by ID
    public ResponseEntity<?> getUserById(ObjectId id) {
        Users user = userRepository.findUsersByUserId(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok(user);
    }

    // ✅ Create a new user
    public ResponseEntity<?> createUser(Users tempUser) {
        if (tempUser.getName() == null || tempUser.getAge() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name and Age are required fields!");
        }
        userRepository.save(tempUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully!");
    }

    // ✅ Update user by username
    public ResponseEntity<?> updateUserByUsername(String username, Users tempUser) {
        Users existingUser = userRepository.findUsersByName(username);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        if (tempUser.getName() != null) {
            existingUser.setName(tempUser.getName());
        }
        if (tempUser.getStatus() != null) {
            existingUser.setStatus(tempUser.getStatus());
        }
        if (tempUser.getAge() != null) {
            existingUser.setAge(tempUser.getAge());
        }

        userRepository.save(existingUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(existingUser);
    }

    // ✅ Update user by ID
    public ResponseEntity<?> updateUserById(ObjectId myId, Users tempUser) {
        Users existingUser = userRepository.findUsersByUserId(myId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        if (tempUser.getName() != null) {
            existingUser.setName(tempUser.getName());
        }
        if (tempUser.getStatus() != null) {
            existingUser.setStatus(tempUser.getStatus());
        }
        if (tempUser.getAge() != null) {
            existingUser.setAge(tempUser.getAge());
        }

        userRepository.save(existingUser);
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    // ✅ Delete all users
    public ResponseEntity<?> deleteAllUsers() {
        userRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All users deleted successfully.");
    }

    // ✅ Delete user by username
    public ResponseEntity<?> deleteUserByUsername(String username) {
        Users existingUser = userRepository.findUsersByName(username);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        userRepository.deleteUsersByName(username);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    // ✅ Delete user by ID
    public ResponseEntity<?> deleteUserById(ObjectId id) {
        Users existingUser = userRepository.findUsersByUserId(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        userRepository.deleteUsersByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}
