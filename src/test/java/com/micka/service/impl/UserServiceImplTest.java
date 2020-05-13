package com.micka.service.impl;

import com.micka.ObjectProvider;
import com.micka.dto.User;
import com.micka.entity.UserEntity;
import com.micka.repository.UserCrudRepository;
import com.micka.service.mapper.impl.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final UserEntity ENTITY = (UserEntity) ObjectProvider.provideObjectByClass(UserEntity.class);
    private static final User DOMAIN = (User) ObjectProvider.provideObjectByClass(User.class);

    @Mock
    UserCrudRepository userCrudRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(UserServiceImplTest.class);
    }

    @Test
    void whenUserServiceFindByIdCorrectThenOK(){
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(DOMAIN);
        when(userCrudRepository.findById(anyInt())).thenReturn(Optional.of(ENTITY));

        userService.findById(1);

        verify(userCrudRepository,times(1)).findById(anyInt());
        verify(userMapper,times(1)).mapEntityToDomain(any(UserEntity.class));
    }

    @Test
    void whenUserServiceFindByIdNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.findById(null));
        verifyNoInteractions(userCrudRepository, userMapper);
    }

    @Test
    void whenUserServiceSaveCorrectThenOK(){
        when(userMapper.mapDomainToEntity(any(User.class))).thenReturn(ENTITY);
        when(userCrudRepository.save(any(UserEntity.class))).thenReturn(ENTITY);

        userService.save(DOMAIN);

        verify(userMapper,times(1)).mapDomainToEntity(any(User.class));
        verify(userCrudRepository,times(1)).save(any(UserEntity.class));
    }

    @Test
    void whenUserServiceSaveNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.save(null));
        verifyNoInteractions(userCrudRepository, userMapper);
    }

    @Test
    void whenUserServiceUpdateNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.update(null));
        verifyNoInteractions(userCrudRepository, userMapper);
    }

    @Test
    void whenUserServiceDeleteCorrectThenOK(){
        doNothing().when(userCrudRepository).deleteById(anyInt());

        userService.delete(1);

        verify(userCrudRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void whenUserServiceDeleteNullThenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.delete(null));
        verifyNoInteractions(userCrudRepository);
    }
}