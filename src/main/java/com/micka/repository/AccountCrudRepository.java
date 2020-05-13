package com.micka.repository;

import com.micka.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCrudRepository extends CrudRepository<AccountEntity, Integer> {

    @Override
    List<AccountEntity> findAll();

    List<AccountEntity> findAllByUserId(Integer id);
}
