package com.micka.controller;

import com.micka.dto.User;
import com.micka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findById(id));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Integer> saveUser(@RequestBody @Valid User user){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody @Valid User user){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(user));
    }

    @DeleteMapping(path = "{id}",produces = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
