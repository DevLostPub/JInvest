package com.devlost.jinvest.bean;

import com.devlost.jinvest.dao.CustomerDAO;
import com.devlost.jinvest.dao.FundDAO;
import com.devlost.jinvest.dao.TransactionDAO;
import com.devlost.jinvest.model.Fund;
import com.devlost.jinvest.model.Transaction;
import com.devlost.jinvest.model.UserProfile;
import com.devlost.jinvest.model.UsersFunds;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User application bean
 * @author lucas
 */
@ManagedBean(name = "broker")
@SessionScoped
public class UserBean {

    private String name;
    private UserProfile user;
    private Set<UsersFunds> userFunds;
    private ArrayList<Fund> fundList;
    private Fund selectedFund;
    private Transaction selectedTransaction;
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.log(Level.INFO, "LOGGED USER: {0}", username);
        name = username;

        //Catch exception here if the username doest exist
        user = new CustomerDAO().get(username);
        userFunds = user.getUsersFundses();
        
        fundList  = new FundDAO().getAll();
    }

    public void prepareNewTransaction(){
        selectedTransaction = new Transaction();
    }
    
    public void addTransaction(String type) {
        selectedTransaction.setFund(selectedFund);
        selectedTransaction.setUserProfile(user);
        selectedTransaction.setType(Transaction.Type.valueOf(type).name());
        new TransactionDAO().insert(selectedTransaction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public Set<UsersFunds> getUserFunds() {
        return userFunds;
    }

    public void setUserFunds(Set<UsersFunds> userFunds) {
        this.userFunds = userFunds;
    }

    public Fund getSelectedFund() {
        return selectedFund;
    }

    public void setSelectedFund(Fund selectedFund) {
        this.selectedFund = selectedFund;
    }

    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public ArrayList<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(ArrayList<Fund> fundList) {
        this.fundList = fundList;
    }

    
}
