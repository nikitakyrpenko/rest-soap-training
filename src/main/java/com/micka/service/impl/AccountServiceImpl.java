package com.micka.service.impl;

import com.micka.dto.Account;
import com.micka.entity.AccountEntity;
import com.micka.repository.AccountCrudRepository;
import com.micka.service.AccountService;
import com.micka.service.mapper.Mapper;
import com.micka.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountCrudRepository accountCrudRepository;
    private final Mapper<Account, AccountEntity> mapper;

    @Autowired
    public AccountServiceImpl(AccountCrudRepository accountCrudRepository, Mapper<Account, AccountEntity> mapper) {
        this.accountCrudRepository = accountCrudRepository;
        this.mapper = mapper;
    }


    @Override
    public Account findById(Integer id) {
        Utilities.checkForNull(id);

        return accountCrudRepository.findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow();
    }

    @Override
    public Integer save(Account account) {
        Utilities.checkForNull(account);

        AccountEntity entity = mapper.mapDomainToEntity(account);

        return accountCrudRepository
                .save(entity)
                .getId();
    }

    @Override
    public Integer update(Account account) {
        Utilities.checkForNull(account);

        AccountEntity entity = mapper.mapDomainToEntity(account);

        return accountCrudRepository
                .save(entity)
                .getId();
    }

    @Override
    public void delete(Integer id) {
        Utilities.checkForNull(id);

        accountCrudRepository.deleteById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountCrudRepository.findAll()
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Account> findAllByUserId(Integer id) {
        Utilities.checkForNull(id);

        return accountCrudRepository.findAllByUserId(id)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toUnmodifiableList());
    }
}
