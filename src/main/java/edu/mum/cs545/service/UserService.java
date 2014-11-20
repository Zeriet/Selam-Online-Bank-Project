/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.dao.AccountDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.dao.UserDAO;
import edu.mum.cs545.model.Account;
import edu.mum.cs545.model.User;

/**
 *
 * @author Senai
 */
public class UserService {

    DAOFactory factory = DAOFactory.getFactory();
    private UserDAO userDAO = factory.getUserDAO();
    private AccountDAO accountDAO = factory.getAccountDAO();

    public enum USERType {

        STAFF, CUSTOMMER, NONE
    };

    public USERType checkLogin(String userName, String password) {
        System.out.println("checkLoginnnnnnnnnnnn");
        if (userName.equals("BankStaff") && password.equals("staffPassword")) {
            return USERType.STAFF;
        }

        try {
            accountDAO.beginTransaction();
            System.out.println("UserService - " + Long.parseLong(userName));
            Account loggingAccount = accountDAO.findByPrimaryKey(Long.parseLong(userName));//for customers they login with their account Number

            System.out.println("UserService - ");
            System.out.println("UserService - " + loggingAccount.getPIN());
            if (loggingAccount.getPIN() == new Integer(password)) {                   //for customers the password is a pin
                System.out.println("UserService-Customer Loggedin");
                accountDAO.commitTransaction();
                return USERType.CUSTOMMER;
            }
            
        } catch (NumberFormatException e) {
            System.out.println("UserName or Password datatype Esception ");
            return USERType.NONE;
        }

        return USERType.NONE;

    }

}
