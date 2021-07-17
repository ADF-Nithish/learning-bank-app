package com.adf.rest.dao;

import java.util.List;

import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactDao extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccount(BankAccount account);
    
}
