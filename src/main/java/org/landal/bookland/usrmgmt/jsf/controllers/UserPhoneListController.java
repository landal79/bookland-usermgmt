package org.landal.bookland.usrmgmt.jsf.controllers;

import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.landal.bookland.usrmgmt.model.PhoneNumber;
import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.services.UserService;

@Model
public class UserPhoneListController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private UserService userService;

	@NotNull
	private String phoneNumber;

	private User user;

	@PostConstruct
	protected void onPostConstruct() {

		String userId = facesContext.getExternalContext().getRequestParameterMap().get("userId");
		if (StringUtils.isBlank(userId)) {
			return;
		}

		user = userService.findById(Long.valueOf(userId));
	}

	public void save(AjaxBehaviorEvent event) throws Exception {
		try {
			user.addPhone(phoneNumber);
			userService.save(user);
		} catch (Exception e) {
			String errorMessage = ExceptionUtils.getRootCauseMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}

	}
	
	@Named
	@Produces
	public Set<PhoneNumber> getPhones() {
		
		if(!PhaseId.RENDER_RESPONSE.equals(facesContext.getCurrentPhaseId())) {
    		return null;
    	}
		
		if (user == null) {
			return Collections.emptySet();
		}

		return user.getPhoneNumbers();
	}

	@Named
	@Produces
	public Long getUserId() {
		if (user == null) {
			return null;
		}

		return user.getId();

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
