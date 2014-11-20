/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.model.Customer;
import edu.mum.cs545.service.AccountService;
import edu.mum.cs545.service.UserService;
import edu.mum.cs545.service.UserService.USERType;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Senai
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String uname;
    private String password;
    private USERType userType;
    private boolean loggedIn;
    private Customer loggedinCustomer;
    private UserService userService = new UserService();
    private AccountService accountService = new AccountService();

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        System.out.println("Login Beannnnnnnnnn");
        switch (userService.checkLogin(uname, password)) {
            case CUSTOMMER:
                userType = USERType.CUSTOMMER;
                loggedIn = true;
                loggedinCustomer = accountService.getCustomer(uname);//uname is an account Number
                System.out.println("Customer LoggedIn");
                return "customerHome.faces";

            case STAFF:
                userType = USERType.STAFF;
                loggedIn = true;
                return "staffHome.faces";

            default:
                userType = USERType.NONE;
                loggedIn = false;
                return "loginOnline.faces";
        }
    }

    public String logout() {
        loggedIn = false;
        loggedinCustomer=null;
        return "welcome.faces";
    }

    public void checkLogin() {
        if (!loggedIn) {
            FacesContext context = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
            handler.performNavigation("loginOnline.faces");
        }
    }
}