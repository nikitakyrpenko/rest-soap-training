package com.micka.service.mapper.impl;

import com.micka.ObjectProvider;
import com.micka.dto.Account;
import com.micka.entity.AccountEntity;
import com.micka.service.mapper.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AccountMapperTest {

    Mapper<Account, AccountEntity> mapper;

    @BeforeEach
    void init() {
        mapper = new AccountMapper();
    }

    @Test
    void whenMapDomainToEntityShouldReturnEntityWithSameFieldPropertiesExceptUserReference() {
        Account domain = (Account) ObjectProvider.provideObjectByClass(Account.class);

        AccountEntity actual = mapper.mapDomainToEntity(domain);

        assertThat(actual)
                .isEqualToIgnoringGivenFields(domain, "user");
    }

    @Test
    void whenMapEntityToDomainShouldReturnDomainWithSameFieldPropertiesExceptUserReference() {
        AccountEntity entity = (AccountEntity) ObjectProvider.provideObjectByClass(AccountEntity.class);

        Account actual = mapper.mapEntityToDomain(entity);

        assertThat(actual)
                .isEqualToIgnoringGivenFields(entity, "userId");
    }

    @Test
    void whenMapDomainToEntityShouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mapper.mapDomainToEntity(null));
    }

    @Test
    void whenMapEntityToDomainShouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mapper.mapEntityToDomain(null));
    }
}