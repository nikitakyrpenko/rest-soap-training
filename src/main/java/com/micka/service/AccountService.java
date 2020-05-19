package com.micka.service;

import com.micka.dto.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Account findById(Integer id);

    Integer save(Account account);

    Integer update(Account account);

    void delete(Integer id);

    Page<Account> findAllByUserId(Integer id, Pageable pageable);

    Page<Account> findAll(Pageable pageable);

}
