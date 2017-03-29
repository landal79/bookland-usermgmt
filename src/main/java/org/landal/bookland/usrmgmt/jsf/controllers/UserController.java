package org.landal.bookland.usrmgmt.jsf.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.landal.bookland.usrmgmt.model.Sex;
import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.services.UserService;

import java.io.Serializable;


@Named
@ViewScoped
public class UserController implements Serializable{

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    private Long userId;

    private User user;

    @PostConstruct
    public void initialize() {


    }

    public void onload() {
        if (!facesContext.isPostback() && userId != null) {
            user = userService.findById(userId);
        }
        else if (user == null) {
            user = new User();
        }
    }

    @Produces
    @Named
    public Sex[] sexList() {
        return Sex.values();
    }

    public String save() throws Exception {
        try {
            userService.save(user);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initialize();
        } catch (Exception e) {
        	String errorMessage = ExceptionUtils.getRootCauseMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }

        return "/pages//index?faces-redirect=true";
    }

   ///////// getters/Setters

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
