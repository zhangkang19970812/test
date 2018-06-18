package com.nju.account_service.dao;

import com.nju.account_service.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findAccountEntityByUsername(String username);

    @Query("select max(entity.id) from AccountEntity entity")
    Integer findMaxId();
}
