/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
 
import org.primefaces.event.ToggleEvent;
/**
 *
 * @author fjoseph1313
 */
@Named("pfb")
public class PrimeFacesBean implements Serializable
{
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Visibility:" + event.getVisibility());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
