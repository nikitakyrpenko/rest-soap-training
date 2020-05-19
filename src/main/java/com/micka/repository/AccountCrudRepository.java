package com.micka.repository;

import com.micka.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCrudRepository extends PagingAndSortingRepository<AccountEntity, Integer> {

    @Override
    Page<AccountEntity> findAll(Pageable pageable);

    Page<AccountEntity> findAllByUserId(Integer id, Pageable pageable);

}
