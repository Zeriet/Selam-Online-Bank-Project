/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.MailSender;

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
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author zeriet
 */
@Named
@SessionScoped

public class CustomerBean implements Serializable {

    @Resource(name = "mail/gmailAccount")
    private Session mailSession;
    private String password;
    private static final int accountNoStart = 422424251;
    private CustomerService custService = new CustomerService();
    private AccountService accService = new AccountService();
    private MailSender mailsend = new MailSender();

    private Customer customer = new Customer();
    private Account account = new Account();

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
        account.setAccountNumber(Long.valueOf(accountNoStart+custId));// setting account number
        accService.save(account);

        return "faces/RegistrationSuccess";

    }

    public String create() {
        try {
            createAccount();
            sendNotification();
            return "done";
        } catch (Exception ex) {
            Logger.getLogger("com.corejsf").log(Level.SEVERE, "login failed", ex);
            return "error";
        }
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

    private void sendNotification() throws MessagingException {
//        ResourceBundle bundle = ResourceBundle.getBundle("com.corejsf.messages");
        String subject = "Your Registration Information";
        String body = "You are now registered with user name {0} and password {1}";

        String messageText = MessageFormat.format(body, customer.getFirstName(), password);
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);

        Address toAddress = new InternetAddress(customer.getEmail());
        message.setRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setText(messageText);
        message.saveChanges();

        Transport tr = mailSession.getTransport();
        String serverPassword = mailSession.getProperty("mail.password");

        System.out.println("check name.............." + serverPassword);
        tr.connect(null, serverPassword);
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }

}
