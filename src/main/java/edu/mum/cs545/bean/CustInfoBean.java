/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.model.Customer;
import edu.mum.cs545.service.CustInfoService;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author fjoseph1313
 */
@Named("custInfo")
@SessionScoped
public class CustInfoBean implements Serializable
{
    private List customers;
    private CustInfoService ciServ;

    public List getCustomers() {
        return customers;
    }

    public void setCustomers(List customers) {
        this.customers = customers;
    }
    
    
    public String populateCustomer()
    {
        ciServ = new CustInfoService();
        customers = ciServ.customerFinder();
        Iterator it = customers.iterator();
        while(it.hasNext())
        {
            Customer ac = (Customer)it.next();
            System.out.println("Customer detail: "+ac.getFirstName() + " "+ac.getEmail());
        }
        return "";
    }
    
    //prime faces edit config
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Customer Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
