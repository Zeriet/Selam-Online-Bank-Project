/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.bean;

import edu.mum.cs545.model.Account;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import edu.mum.cs545.service.AccountService;
//import javax.enterprise.context.RequestScoped;
import edu.mum.cs545.model.Customer;
import java.util.*;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;



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
	 private AccountService accService = new AccountService();
    private List<Account> accts = new ArrayList();
    private Customer custExample = new Customer(new Long(1), "Francis", "Joseph", "cesc.joseph@gmail.com", 52557, "1000N 4th ST", "IOWA");

       private Account account = new Account();


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
        
    public String createSavings()
    {
        Account savings = new Account();
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int CurrentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);
        int xxx = localCalendar.get(Calendar.MINUTE);
        int year = localCalendar.get(Calendar.YEAR)-2000;
        Long acct = Long.parseLong(year+"44"+CurrentDayOfYear+xxx);
        savings.setAccountNumber(acct);
        savings.setAccountType("Saving");
        
        savings.setCardNumber(123); //cardno is null for savings acct
        savings.setPIN(111); //pin is null for savings account
        
        savings.setCustomer(custExample);
        Long id = (Long)custExample.getCustomerId(); // for finding the list of customer accounts
        
        accts = (List<Account>)acctServ.customerAccountsList(id);
        Iterator it = accts.iterator();
        while(it.hasNext())
        {
            Account ac = (Account)it.next();
            //System.out.println("bean detail: "+ac.getAccountType() + " "+ac.getBalance());
            //check if balance on checkings is greater than initial balance
            if(ac.getAccountType().equalsIgnoreCase("checking"))
            {
                if(ac.getBalance() > amount)
                {
                    savings.setBalance(amount); //set savings balance to this
                    acctServ.savingsCreator(savings);
                    return "faces/welcome";
                }
            }
        }
        return "faces/savingsAccount";
    }

}
