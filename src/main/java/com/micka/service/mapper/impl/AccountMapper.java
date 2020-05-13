package com.micka.service.mapper.impl;

import com.micka.dto.Account;
import com.micka.entity.AccountEntity;
import com.micka.entity.UserEntity;
import com.micka.service.mapper.Mapper;
import com.micka.utils.Utilities;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountMapper implements Mapper<Account, AccountEntity> {

    @Override
    public Account mapEntityToDomain(AccountEntity accountEntity) {
        Utilities.checkForNull(accountEntity);

        return new Account(
                accountEntity.getId(),
                accountEntity.getBalance(),
                accountEntity.getCharge(),
                accountEntity.getHolder().getId());
    }

    @Override
    public AccountEntity mapDomainToEntity(Account account) {
        Utilities.checkForNull(account);

        if (Objects.nonNull(account.getId())){
            return new AccountEntity(
                    account.getId(),
                    account.getBalance(),
                    account.getCharge(),
                    new UserEntity(account.getId()));
        }
        return new AccountEntity(
                account.getBalance(),
                account.getCharge(),
                new UserEntity(account.getId()));
    }
}
