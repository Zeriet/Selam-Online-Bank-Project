/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.dao.UserDAO;
import edu.mum.cs545.model.User;

/**
 *
 * @author Senai
 */
public class UserService {

    DAOFactory factory = DAOFactory.getFactory();
    private UserDAO userDAO = factory.getUserDAO();

    public enum USERType {

        STAFF, CUSTOMMER, NONE
    };

    public USERType checkLogin(String userName, String password) {
        if (userName.equals("BankStaff") && password.equals("staffPassword")) {
            return USERType.STAFF;
        }

        User loggingUser = new User();
        loggingUser.setUserName(userName);
        loggingUser.setPassword(password);
        userDAO.beginTransaction();
        if (userDAO.findByExample(loggingUser, new String[]{"userId"}).size() > 0) {
            return USERType.CUSTOMMER;
        }
        return USERType.NONE;

    }
    
}
