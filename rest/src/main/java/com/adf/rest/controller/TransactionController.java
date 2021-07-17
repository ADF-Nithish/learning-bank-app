package com.adf.rest.controller;

import java.util.Locale;

import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactDao;
import com.adf.rest.exceptions.CustomBadRequest;
import com.adf.rest.exceptions.CustomNotFound;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/account/")
public class TransactionController {
    @Autowired
    TransactDao dao;
    @Autowired
    BankDao accDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping(path="transact/{AccountNumber}")
    public Transaction transactapi(@PathVariable("AccountNumber") Long accountNumber,@RequestBody Transaction obj) throws CustomNotFound{
        if (!accDao.existsById(accountNumber)){
            LOGGER.error("Account Not Found");
            throw new CustomNotFound("Account Not Found");
        }
        else{
            return transact(accountNumber, obj);
        }
        
    }

    public Transaction transact(Long accountNumber,Transaction obj) throws CustomBadRequest{
        LOGGER.info("Transaction Started");
        BankAccount objB = accDao.findById(accountNumber).get();
        obj.setAccount(objB);
        obj.setOldBalance(objB.getBalance());
        Double current = objB.getBalance();
        if ("withdrawal".equals(obj.getType().toLowerCase(Locale.getDefault()))){
            current = current - obj.getAmount() - objB.getTransactionFee();
            if (current < 0.0){
                LOGGER.error("Cannot Transact Insufficient Balance");
                throw new CustomBadRequest("Cannot Transact Insufficient Balance");
            }
        }
        else if ("deposit".equals(obj.getType().toLowerCase(Locale.getDefault()))){
            current += obj.getAmount() - objB.getTransactionFee();
        }
        obj.setNewBalance(current);
        objB.setBalance(current);
        obj.setTransactionStatus("Success");
        dao.save(obj);
        LOGGER.info("Transaction Successfully Completed");
        return obj;
    }
    
}
