package com.micka.service.mapper.impl;

import com.micka.dto.User;
import com.micka.entity.UserEntity;
import com.micka.service.mapper.Mapper;
import com.micka.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements Mapper<User, UserEntity> {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapEntityToDomain(UserEntity userEntity) {
        Utilities.checkForNull(userEntity);

        return User.builder()
                .withId(userEntity.getId())
                .withFirstName(userEntity.getFirstName())
                .withLastName(userEntity.getLastName())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        Utilities.checkForNull(user);

        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
    }


}
