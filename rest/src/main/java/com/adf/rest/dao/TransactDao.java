package com.adf.rest.dao;

import com.adf.rest.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactDao extends JpaRepository<Transaction,Long> {
    
}
