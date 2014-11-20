/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.bean;

import edu.mum.cs545.model.Account;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import edu.mum.cs545.service.AccountService;
//import javax.enterprise.context.RequestScoped;
import edu.mum.cs545.model.Customer;
import java.util.*;



/**
 *
 * @author zeriet
 */
@Named
@SessionScoped
//@RequestScoped
public class AccountBean implements Serializable
{
    private double amount;
    private AccountService acctServ = new AccountService();
    private List<Account> accts = new ArrayList();
    private Customer custExample = new Customer(new Long(1), "Francis", "Joseph", "cesc.joseph@gmail.com", 52557, "1000N 4th ST", "IOWA");
    private Account account = new Account();
    private String status;


    public Account getAccount() {
        return account;
    }
       
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustExample() {
        return custExample;
    }

    public void setCustExample(Customer custExample) {
        this.custExample = custExample;
    }

    public List<Account> getAccts() {
        return accts;
    }

    public void setAccts(List<Account> accts) {
        this.accts = accts;
    }

    public AccountService getAcctServ() {
        return acctServ;
    }

    public void setAcctServ(AccountService acctServ) {
        this.acctServ = acctServ;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String createSavings()
    {
        if((acctServ.savingsCreator(custExample, amount)).equals("success"))
        {
            status = "Savings Account created Successfully with initial balance of "+amount;
        }
        else
        {
            status = "Initial Balance was Bigger than Your Checking Account Balance, Try Again";
        }
        return "savingsAccount.faces";
    }

}
