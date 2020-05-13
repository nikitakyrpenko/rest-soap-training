package com.micka.service;

import com.micka.dto.Account;
import java.util.List;

public interface AccountService {

    Account findById(Integer id);

    Integer save(Account account);

    Integer update(Account account);

    void delete(Integer id);

    List<Account> findAll();

    List<Account> findAllByUserId(Integer id);
}
