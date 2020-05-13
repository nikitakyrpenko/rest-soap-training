package com.micka.controller;

import com.micka.dto.User;
import com.micka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findById(id));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Integer> saveUser(@RequestBody User user){
        Integer id = userService.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping(path = "", produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody User user){
        Integer id = userService.update(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @DeleteMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.delete(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
