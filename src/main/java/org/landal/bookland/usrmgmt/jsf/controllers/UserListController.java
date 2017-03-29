package org.landal.bookland.usrmgmt.jsf.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.services.UserService;

@Named
@ViewScoped
public class UserListController implements Serializable {


    @Inject
    private UserService userService;
    
    @Inject
    private FacesContext facesContext;
    
    private List<User> users;

    private User searchFilter;
    
    @PostConstruct
    public void onPostConstruct() {
        searchFilter = new User();
        users = userService.findByExample(searchFilter);
    }


    public void onDeleteUser(User user) {
        userService.remove(user);
        users = userService.findByExample(searchFilter);
    }

    public void onFilter(AjaxBehaviorEvent event) {
        users = userService.findByExample(searchFilter);
    }

    ////// getters/setters

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(User searchFilter) {
        this.searchFilter = searchFilter;
    }
}
