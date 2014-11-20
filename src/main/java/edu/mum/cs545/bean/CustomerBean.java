/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;


import edu.mum.cs545.Messages;

import edu.mum.cs545.model.*;
import edu.mum.cs545.service.AccountService;
import edu.mum.cs545.service.CustomerService;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author zeriet
 */
@Named
@SessionScoped
public class CustomerBean implements Serializable {

   
    private String password;
    private static final int accountNoStart = 422424251;
    private CustomerService custService = new CustomerService();
    private AccountService accService = new AccountService();
  
    private Messages msgs=new Messages();

    private Customer customer = new Customer();
    private Account account = new Account();

    public Messages getMsgs() {
        return msgs;
    }

    public void setMsgs(Messages msgs) {
        this.msgs = msgs;
    }
    

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String register() {
        System.out.println("registering customer.....");
        account.setCustomer(customer);

        int custId = custService.save(customer); // thsi customer Id is used to generate unique account Number
        

        account.setPIN(accService.generatePIN());        
        account.setAccountNumber(Long.valueOf(accountNoStart+custId));// setting account number
        accService.save(account);

        return "registrationSummary.faces";

    }

   

    private void createAccount() {
        // Generate a random password; an 8-digit number in base 36.      
        int BASE = 36;
        int LENGTH = 8;
        password = Long.toString((long) (Math.pow(BASE, LENGTH) * Math.random()), BASE);
        /*
         * In a real application, we would now make sure that the username is available
         * and save the username/password in a database. 
         */
    }


}
