/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

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
    AccountDAO accountDAO = factory.getAccountDAO();

    public Long accountNumber() {

        return null;

    }

    public int generatePIN() {
        //generate a 4 digit integer 1000 <10000
        return (int) (Math.random() * 9000) + 1000;

    }

    public void save(Account account) {
        AccountDAO accDao = factory.getAccountDAO();
        accDao.beginTransaction();
        accDao.save(account);
        accDao.commitTransaction();
    }

    public String savingsCreator(Customer cust, double amt) {
        //generate account number.
        AccountDAO accDao = factory.getAccountDAO();
        CustomerDAO custDao = factory.getCustomerDAO();
        accDao.beginTransaction();
        String result = "";

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int CurrentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);
        int hhh = localCalendar.get(Calendar.HOUR);
        int xxx = localCalendar.get(Calendar.MINUTE);
        int year = localCalendar.get(Calendar.YEAR) - 2000;
        Long acct = Long.parseLong(year + "44" + CurrentDayOfYear + hhh + xxx);
        System.out.println("account number " + acct);

        Long id = (Long) cust.getCustomerId();
        System.out.println("cust id : " + id);
        Customer cust1 = (Customer) custDao.findByPrimaryKey(new Long(1));
        List accts = cust1.getAccounts();
        Iterator it = accts.iterator();
        while (it.hasNext()) {
            Account ac = (Account) it.next();
            System.out.println("Serv detail: " + ac.getAccountType() + " " + ac.getBalance());
            if (ac.getAccountType().equalsIgnoreCase("checking")) {
                if (ac.getBalance() > amt) {
                    Account savings = new Account();
                    savings.setAccountNumber(acct);
                    savings.setAccountType("Saving");
                    savings.setCustomer(cust);
                    savings.setBalance(amt);

                    ac.setBalance(ac.getBalance() - amt);

                    accDao.save(ac);
                    accDao.save(savings); //saving instance to the database...
                    result = "success";
                    System.out.println("Saved savings Account");
                } else {
                    result = "failure";
                    System.out.println("adsfasdasfasdfaf");
                }
            }
        }
        accDao.commitTransaction();
        return result;
    }

    public Customer getCustomer(String accountNumber) {
        Account account;
        try {

            accountDAO.beginTransaction();
            account = accountDAO.findByPrimaryKey(new Long(accountNumber));
//            accountDAO.commitTransaction();
        } catch (Exception e) {
            System.out.println("AccountService" + e.getMessage());
            return null;

        }
        System.out.println("Customer - Account Owner  -" + account.getCustomer().getFirstName());
        return account.getCustomer();

    }

}
