package com.adf.rest.controller;

import java.time.LocalDate;
import java.time.Period;


import com.adf.rest.dao.*;
import com.adf.rest.exceptions.CustomException;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;
import com.adf.rest.request.CreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @Autowired
    BankDao dao;
    @Autowired
    TransactDao tdao;
    @Autowired
    TransactionController t ;

    @PostMapping(path="api/create",consumes={"application/json"})
    public BankAccount create(@RequestBody CreateRequest obj) throws CustomException{
        if (!(obj.getAccount_type().toLowerCase().equals("current")
                 || obj.getAccount_type().toLowerCase().equals("savings"))){
                     throw new CustomException("Account Type Should only be SAVINGS|CURRENT");
        }
        Period period = Period.between(obj.getDob(), LocalDate.now());
        if (Math.abs(period.getYears()) >= 100){
            throw new CustomException("Age is Exceeded");
        }
        BankAccount objAcc = new BankAccount();
        objAcc.setAccountHolderName(obj.getAccount_holder_name());
        objAcc.setAccountType(obj.getAccount_type());
        objAcc.setDateofBirth(obj.getDob());
        
        if ((obj.getAccount_type().toLowerCase()).equals("current")){
            objAcc.setTransactionFee(5.0);
        }

        dao.save(objAcc);
        if ((obj.getInitial_deposit() != 0.0)){
            
            Transaction tacc = new Transaction();
            tacc.setAmount(obj.getInitial_deposit());
            tacc.setType("Deposit");
            tdao.save(t.transact(objAcc.getAccountNumber(), tacc));
            objAcc.setTransaction(tdao.findAll());
            dao.save(objAcc);
        }
        return objAcc;
    }

    
}
