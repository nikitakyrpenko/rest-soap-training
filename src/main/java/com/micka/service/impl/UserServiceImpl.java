package com.micka.service.impl;

import com.micka.dto.User;
import com.micka.entity.UserEntity;
import com.micka.exception.RecordNotFoundException;
import com.micka.repository.UserCrudRepository;
import com.micka.service.UserService;
import com.micka.service.mapper.Mapper;
import com.micka.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserCrudRepository userCrudRepository;
    private final Mapper<User, UserEntity> mapper;

    @Autowired
    public UserServiceImpl(UserCrudRepository userCrudRepository, Mapper<User, UserEntity> mapper) {
        this.userCrudRepository = userCrudRepository;
        this.mapper = mapper;
    }


    @Override
    public User findById(Integer id) {
        Utilities.checkForNull(id);

        return userCrudRepository.findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> {throw new RecordNotFoundException("User with such id not found");});
    }

    @Override
    public Integer save(User user) {
        Utilities.checkForNull(user);

        UserEntity entity = mapper.mapDomainToEntity(user);

        return userCrudRepository
                .save(entity)
                .getId();
    }

    @Override
    public Integer update(User user) {
        Utilities.checkForNull(user);

        UserEntity entity = mapper.mapDomainToEntity(user);

        return userCrudRepository
                .save(entity)
                .getId();
    }

    @Override
    public void delete(Integer id) {
        Utilities.checkForNull(id);

        userCrudRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username){
        return userCrudRepository
                .findByEmail(username)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> {throw new UsernameNotFoundException("User with such email does not exists");});
    }
}
