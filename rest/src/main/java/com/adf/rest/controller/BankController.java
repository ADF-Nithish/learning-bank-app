package com.adf.rest.controller;

// import java.time.LocalDate;
import java.util.List;

import com.adf.rest.dao.BankDao;
import com.adf.rest.models.BankAccount;
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

    @PostMapping(path="api/create",consumes={"application/json"})
    public List<BankAccount> home(@RequestBody CreateRequest obj){
        System.out.println(obj.getAccountType()+obj.getAccountHolderName()+obj.getDateofBirth());
        BankAccount objAcc = new BankAccount();
        objAcc.setAccountHolderName(obj.getAccountHolderName());
        objAcc.setAccountType(obj.getAccountType());
        objAcc.setDateofBirth(obj.getDateofBirth());
        if ((obj.getInitialDeposit() != 0.0)){
            System.out.println("Set deposit");
        }
        if ((obj.getAccountType().toLowerCase()).equals("current")){
            objAcc.setTransactionFee(5.0);
        }

        dao.save(objAcc);
        return dao.findAll();
    }

    @PostMapping(path="/")
    public String insert(@RequestBody BankAccount obj){
        return "f";
    }
}
