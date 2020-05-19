package com.micka.service.impl;

import com.micka.dto.Account;
import com.micka.entity.AccountEntity;
import com.micka.entity.UserEntity;
import com.micka.exception.RecordNotFoundException;
import com.micka.repository.AccountCrudRepository;
import com.micka.repository.UserCrudRepository;
import com.micka.service.AccountService;
import com.micka.service.mapper.Mapper;
import com.micka.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountCrudRepository accountCrudRepository;
    private final UserCrudRepository userCrudRepository;
    private final Mapper<Account, AccountEntity> mapper;

    @Autowired
    public AccountServiceImpl(AccountCrudRepository accountCrudRepository, UserCrudRepository userCrudRepository, Mapper<Account, AccountEntity> mapper) {
        this.accountCrudRepository = accountCrudRepository;
        this.userCrudRepository = userCrudRepository;
        this.mapper = mapper;
    }


    @Override
    public Account findById(Integer id) {
        Utilities.checkForNull(id);

        return accountCrudRepository.findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> {throw new RecordNotFoundException("User with such id not found");});
    }

    @Override
    @Transactional
    public Integer save(Account account) {
        Utilities.checkForNull(account);

        AccountEntity entity = mapper.mapDomainToEntity(account);
        UserEntity holder = userCrudRepository
                .findById(account.getUserId())
                .orElseThrow(() -> {throw new RecordNotFoundException("Account with such id not found");});

        entity.setUser(holder);

        return accountCrudRepository
                .save(entity)
                .getId();
    }

    @Override
    @Transactional
    public Integer update(Account account) {
        Utilities.checkForNull(account);

        AccountEntity entity = mapper.mapDomainToEntity(account);
        UserEntity holder = userCrudRepository
                .findById(account.getUserId())
                .orElseThrow(() -> {throw new RecordNotFoundException("Account with such id not found");});

        entity.setUser(holder);

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
    public Page<Account> findAllByUserId(Integer id, Pageable pageable) {
        return accountCrudRepository
                .findAllByUserId(id, pageable)
                .map(mapper::mapEntityToDomain);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountCrudRepository.findAll(pageable)
                .map(mapper::mapEntityToDomain);

    }
}
