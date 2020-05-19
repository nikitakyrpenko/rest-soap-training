package com.micka.service.impl;

import com.micka.ObjectProvider;
import com.micka.dto.Account;
import com.micka.entity.AccountEntity;
import com.micka.repository.AccountCrudRepository;
import com.micka.service.mapper.impl.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private static final AccountEntity ENTITY = (AccountEntity) ObjectProvider.provideObjectByClass(AccountEntity.class);
    private static final Account DOMAIN = (Account) ObjectProvider.provideObjectByClass(Account.class);

    @Mock
    AccountCrudRepository accountCrudRepository;

    @Mock
    AccountMapper accountMapper;

    @InjectMocks
    AccountServiceImpl accountService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(AccountServiceImplTest.class);
    }

    @Test
    void whenAccountServiceFindByIdCorrectThenOK(){
        when(accountCrudRepository.findById(anyInt())).thenReturn(Optional.of(ENTITY));
        when(accountMapper.mapEntityToDomain(any(AccountEntity.class))).thenReturn(DOMAIN);

        accountService.findById(1);

        verify(accountCrudRepository,times(1)).findById(anyInt());
        verify(accountMapper,times(1)).mapEntityToDomain(any(AccountEntity.class));
    }

    @Test
    void whenAccountServiceFindByIdNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.findById(null));
        verifyNoInteractions(accountCrudRepository, accountMapper);
    }

    @Test
    void whenAccountServiceSaveNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.save(null));
        verifyNoInteractions(accountCrudRepository, accountMapper);
    }

    @Test
    void whenAccountServiceUpdateNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.update(null));
        verifyNoInteractions(accountCrudRepository, accountMapper);
    }

    @Test
    void whenAccountServiceDeleteCorrectThenOK(){
        doNothing().when(accountCrudRepository).deleteById(anyInt());

        accountService.delete(1);

        verify(accountCrudRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void whenAccountServiceDeleteNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.delete(null));
        verifyNoInteractions(accountCrudRepository);
    }

    @Test
    void whenAccountServiceFindAllCorrectThenOK(){
        when(accountMapper.mapEntityToDomain(any(AccountEntity.class))).thenReturn(DOMAIN);
        when(accountCrudRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(ENTITY,ENTITY)));

        accountService.findAll(PageRequest.of(0,1));

        verify(accountCrudRepository,times(1)).findAll(any(Pageable.class));
        verify(accountMapper,atLeastOnce()).mapEntityToDomain(any(AccountEntity.class));
    }

    @Test
    void whenAccountServiceFindAllByUserIdCorrectThenOk(){
        when(accountMapper.mapEntityToDomain(any(AccountEntity.class))).thenReturn(DOMAIN);
        when(accountCrudRepository.findAllByUserId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(ENTITY,ENTITY)));

        accountService.findAllByUserId(1, PageRequest.of(0,1));

        verify(accountCrudRepository,times(1)).findAllByUserId(anyInt(), any(Pageable.class));
        verify(accountMapper,atLeastOnce()).mapEntityToDomain(any(AccountEntity.class));
    }
}