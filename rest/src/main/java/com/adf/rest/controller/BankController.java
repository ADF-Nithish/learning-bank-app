package com.adf.rest.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactDao;
import com.adf.rest.exceptions.CustomBadRequest;
import com.adf.rest.exceptions.CustomNotFound;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;
import com.adf.rest.request.CreateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/account/")
public class BankController {
    @Autowired
    BankDao dao;
    @Autowired
    TransactDao tdao;
    @Autowired
    TransactionController t ;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    EntityManager entityManager;

    @PostMapping(path="create",consumes={"application/json"})
    public BankAccount create(@RequestBody CreateRequest obj) throws CustomBadRequest{
        if (!("current".equals(obj.getAccountType().toLowerCase(Locale.getDefault()))
                 || "savings".equals(obj.getAccountType().toLowerCase(Locale.getDefault())))){
                    LOGGER.error("Account Type Should only be SAVINGS|CURRENT");
                    throw new CustomBadRequest("Account Type Should only be SAVINGS|CURRENT");
        }
        Period period = Period.between(obj.getDob(), LocalDate.now());
        if (Math.abs(period.getYears()) >= 100){
            LOGGER.error("Age is Exceeded");
            throw new CustomBadRequest("Age is Exceeded");
        }
        LOGGER.info("BankAccount Creation Starting");
        BankAccount objAcc = new BankAccount();
        objAcc.setAccountHolderName(obj.getAccountHolderName());
        objAcc.setAccountType(obj.getAccountType());
        objAcc.setDateofBirth(obj.getDob());
        
        if ("current".equals(obj.getAccountType().toLowerCase(Locale.getDefault()))){
            objAcc.setTransactionFee(5.0);
        }
        entityManager.getTransaction().begin();
        entityManager.persist(objAcc);
        long accNo = objAcc.getAccountNumber();
        
        if (obj.getInitialDeposit() != 0.0){
            
            Transaction tacc = new Transaction();
            tacc.setAmount(obj.getInitialDeposit());
            tacc.setType("Deposit");
            LOGGER.info("Transaction Successfully Completed");
            tacc.setTransactionStatus("Success");
            tdao.save(t.transact(accNo, tacc));
            
            objAcc.setTransaction(tdao.findByAccount(tacc.getAccount()));
            dao.save(objAcc);
        }
        
        return objAcc;
    }
    @GetMapping(path = "/getFromTo/{accountNo}", produces = { "application/json" })
    public Map<String, Object> accountStatement(@PathVariable Long accountNo, @RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) {
        if (!dao.existsById(accountNo)){
            LOGGER.error("Account Not Found");
            throw new CustomNotFound("Account Not Found");
        }
        else
        {
            BankAccount objB = dao.getById(accountNo);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("account_number", accountNo);
            map.put("account_holder_name", objB.getAccountHolderName());
            map.put("dob", objB.getDateofBirth());
            map.put("account_type", objB.getAccountType());
            map.put("from_date", fromDate);
            map.put("to_date", toDate);
            map.put("transactions", dao.getById(accountNo).getTransaction().stream()
            .filter(a -> Period.between(LocalDate.parse(fromDate), a.getTransactionDate()).getDays() >= 0
                        && Period.between(LocalDate.parse(toDate), a.getTransactionDate()).getDays() <= 0)
            .collect(Collectors.toList()));

            return map;
    }
    }
   
}
