/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.model;

import java.util.Date;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author ZWoldeab
 */
@Entity
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
    private String gender="M";
    private int zipCode;
    private String street;
    private String state;
    private Long customerId;  
    private List<Account> accounts = new Vector<Account>();
    
    public Customer(){}

    public Customer(Long id, String firstName, String lastName, String email, int zipCode, String street, String state) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.zipCode = zipCode;
        this.street = street;
        this.state = state;
    }
    
    @Id
    @GeneratedValue
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId()
    {
        this.customerId = customerId;
    }
    
    @OneToMany(mappedBy="customer", targetEntity=Account.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    public void addAccount()
    {
    }
    
}
