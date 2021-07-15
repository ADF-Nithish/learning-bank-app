package com.adf.rest.dao;

import com.adf.rest.models.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "accounts",path = "accounts")
public interface BankDao  extends JpaRepository<BankAccount,Long>
{
    
}
