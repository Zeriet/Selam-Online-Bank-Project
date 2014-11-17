/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.service.UserService;
import edu.mum.cs545.service.UserService.USERType;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
    private UserService userService = new UserService();

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

        switch (userService.checkLogin(uname, password)) {
            case CUSTOMMER:
                userType = USERType.CUSTOMMER;
                loggedIn=true;
                return "customerIndex";

            case STAFF:
                userType = USERType.STAFF;
                loggedIn=true;
                return "";

            case NONE:
                userType = USERType.NONE;
                loggedIn=false;
                return "loginOnline";

        }
       return "loginOnline";
    }
}
