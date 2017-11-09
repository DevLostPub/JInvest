package com.devlost.jinvest.bean;

import com.devlost.jinvest.dao.TransactionDAO;
import com.devlost.jinvest.model.Transaction;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Default bean to transaction related operations
 * @author lucas
 */
@ManagedBean(name = "transactions")
@ViewScoped
public class TransactionBean {
    
    private ArrayList<Transaction> transactionList;
    
    @PostConstruct
    private void init(){
         transactionList = new TransactionDAO().getAll();
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
    
    
}
