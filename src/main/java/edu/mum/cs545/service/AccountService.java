/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.bean.*;
import edu.mum.cs545.dao.AccountDAO;
import edu.mum.cs545.dao.CustomerDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.model.Account;
import edu.mum.cs545.model.Customer;
import java.util.*;

/**
 *
 * @author zeriet
 */
public class AccountService {
    DAOFactory factory = DAOFactory.getFactory();
    
    public Long accountNumber() {

        return null;

    }

    public int generatePIN() {        
       //generate a 4 digit integer 1000 <10000
       return (int)(Math.random()*9000)+1000;

    }
    
    public void save(Account account) {
        AccountDAO accDao = factory.getAccountDAO();
        accDao.beginTransaction();
        accDao.save(account);
        accDao.commitTransaction();
    }
    
    public void savingsCreator(Account account) {
        //generate account number.
        AccountDAO accDao = factory.getAccountDAO();
        accDao.beginTransaction();
        accDao.save(account);
        accDao.commitTransaction();        
    }
    
    public List<Account> customerAccountsList(Long id)
    {
        //we have the customer, we find by example and we return the list of all his accounts
        CustomerDAO custDao = factory.getCustomerDAO();
        custDao.beginTransaction();
        Customer cust = (Customer)custDao.findByPrimaryKey(id);
        System.out.println(cust.getEmail() +" "+cust.getCustomerId());
        List<Account> accts = cust.getAccounts();
        custDao.commitTransaction();
        Iterator it = accts.iterator();
        while(it.hasNext())
        {
            Account ac = (Account)it.next();
            System.out.println("detail: "+ac.getAccountType() + " "+ac.getBalance());
        }
        
        return accts;
    }

}
