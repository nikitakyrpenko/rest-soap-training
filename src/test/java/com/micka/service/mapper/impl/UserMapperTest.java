package com.micka.service.mapper.impl;

import com.micka.ObjectProvider;
import com.micka.dto.User;
import com.micka.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    private static final String HASHED_PASSWORD = "2Secure4U";

    private static final UserEntity entity = (UserEntity) ObjectProvider.provideObjectByClass(UserEntity.class);

    private static final User domain = (User) ObjectProvider.provideObjectByClass(User.class);

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserMapper mapper;


    @Test
    void whenMapDomainToEntityShouldReturnEntityWithSameFieldPropertiesExceptPassword() {
        when(passwordEncoder.encode(any())).thenReturn(HASHED_PASSWORD);

        assertThat(
                mapper.mapDomainToEntity(domain))
                .isEqualToIgnoringGivenFields(entity, "password");

        verify(passwordEncoder, atLeastOnce()).encode(any());
    }

    @Test
    void whenMapEntityToDomainShouldReturnDomainWithSameFieldProperties(){
        assertThat(
                mapper.mapEntityToDomain(entity))
                .isEqualToIgnoringGivenFields(domain);
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