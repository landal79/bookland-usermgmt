package org.landal.bookland.usrmgmt.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.service.UserService;


@Model
public class UserController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Produces
    @Named
    private User newUser;

    @PostConstruct
    public void initialize() {
        newUser = new User();
    }

    public void save(AjaxBehaviorEvent event) throws Exception {
        try {
            userService.save(newUser);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initialize();
        } catch (Exception e) {
        	String errorMessage = ExceptionUtils.getRootCauseMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }


}
