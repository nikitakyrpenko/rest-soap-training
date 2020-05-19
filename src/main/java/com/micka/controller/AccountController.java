package com.micka.controller;

import com.micka.dto.Account;
import com.micka.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/{userId}/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Account>> findAllByUserId(@PathVariable Integer userId,
                                                 @RequestParam Integer page,
                                                 @RequestParam Integer size,
                                                 @RequestParam String sort){

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountService.findAllByUserId(userId, PageRequest.of(page, size, Sort.by(sort))));
    }


    @PostMapping(produces = "application/json")
    public ResponseEntity<Integer> saveAccount(@PathVariable Integer userId, @RequestBody Account account){
        account.setUserId(userId);
        Integer id = accountService.save(account);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<Integer> updateAccount(@PathVariable Integer userId, @RequestBody Account account){
        account.setUserId(userId);
        Integer id = accountService.update(account);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

   @DeleteMapping(path = "{accountId}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable Integer userId, @PathVariable Integer accountId){
        accountService.delete(accountId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
   }

}
