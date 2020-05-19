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
@RequestMapping("admin")
public class AdminController {

    private final AccountService accountService;

    @Autowired
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "accounts", produces = "application/json")
    public ResponseEntity<Page<Account>> findAll(@RequestParam Integer page,
                                                 @RequestParam Integer size,
                                                 @RequestParam String sort){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountService.findAll(PageRequest.of(page,size, Sort.by(sort))));
    }


    @DeleteMapping(path = "/accounts/{accountId}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable Integer accountId){
        accountService.delete(accountId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
