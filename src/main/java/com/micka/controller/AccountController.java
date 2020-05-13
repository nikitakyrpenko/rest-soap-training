package com.micka.controller;

import com.micka.dto.Account;
import com.micka.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountService.findAll());
    }

    @GetMapping(path = "/user/{id}", produces = "application/json")
    public ResponseEntity<List<Account>> findAllByUserId(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountService.findAllByUserId(id));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Account> findById(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountService.findById(id));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Integer> saveAccount(@RequestBody Account account){
        Integer id = accountService.save(account);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping(path = "", produces = "application/json")
    public ResponseEntity<Integer> updateAccount(@RequestBody Account account){
        Integer id = accountService.update(account);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

   @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        accountService.delete(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
   }

}
