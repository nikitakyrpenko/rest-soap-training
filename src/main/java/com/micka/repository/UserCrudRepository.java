package com.micka.repository;

import com.micka.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {

   Optional<UserEntity> findByEmail(String username);

}
