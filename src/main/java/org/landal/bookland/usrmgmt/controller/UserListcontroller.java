package org.landal.bookland.usrmgmt.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.service.UserService;

@Model
public class UserListcontroller {
	
	private static final List<String> STATES;
	
	static {
		STATES = Arrays.asList("Italia","Usa","Francia","Germania","Spagna");
		Collections.sort(STATES);
	}

    @Inject
    private UserService userService;

    @Produces
    @Named
    private User searchFilter;
    
    @Inject
    private FacesContext facesContext;
    
    private List<User> users; 
    
    @PostConstruct
    public void onPostConstruct() {
    	searchFilter = new User();
    }
    
    @Produces
    @Named
    public List<String> getStates() {
    	return STATES;
    }

    @Produces
    @Named
    public List<User> getUsers() {
    	
    	if(!PhaseId.RENDER_RESPONSE.equals(facesContext.getCurrentPhaseId())) {
    		return null;
    	}
    	
    	if(users == null) {
    		users = userService.findByExample(searchFilter);
    	}
    	
    	
		return users;
    }
    
    
    public void filter(AjaxBehaviorEvent event) throws Exception {
		System.out.println("FILTERING");
	}

}
