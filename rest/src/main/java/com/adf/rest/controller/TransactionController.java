package com.adf.rest.controller;


import javax.security.auth.login.AccountNotFoundException;

import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactDao;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;
import com.adf.rest.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactDao dao;
    @Autowired
    BankDao accDao;


    @PostMapping(path="transact/{AccountNumber}")
    public Transaction transactapi(@PathVariable("AccountNumber") Long accountNumber,@RequestBody Transaction obj) throws CustomException{
        if (!accDao.existsById(accountNumber)){
            throw new CustomException("Account Not Found");
        }
        else{
            return transact(accountNumber, obj);
        }
        
    }

    public Transaction transact(Long accountNumber,Transaction obj) throws CustomException{
        BankAccount objB = accDao.findById(accountNumber).get();
        obj.setAccount(objB);
        obj.setOldBalance(objB.getBalance());
        Double Current = objB.getBalance();
        if (obj.getType().toLowerCase().equals("withdrawal")){
            Current = Current - obj.getAmount() - objB.getTransactionFee();
            if (Current <= 0.0){
                throw new CustomException("Cannot Transact Insufficient Balance");
            }
        }
        else if (obj.getType().toLowerCase().equals("deposit")){
            Current += obj.getAmount()-objB.getTransactionFee();
        }
        obj.setNewBalance(Current);
        objB.setBalance(Current);
        dao.save(obj);
        return obj;
    }
    
}
