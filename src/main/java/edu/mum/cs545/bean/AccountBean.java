/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.model.Account;
import edu.mum.cs545.service.AccountService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author zeriet
 */
@Named
@SessionScoped
public class AccountBean implements Serializable {

    private Account account = new Account();
    private AccountService accService = new AccountService();
    private String deactivateAccount;

    public String getDeactivateAccount() {
        return deactivateAccount;
    }

    public void setDeactivateAccount(String deactivateAccount) {
        this.deactivateAccount = deactivateAccount;
    }
    
   

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    
}
