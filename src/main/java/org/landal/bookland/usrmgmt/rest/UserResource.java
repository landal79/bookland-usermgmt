package org.landal.bookland.usrmgmt.rest;

import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    public List<User> findAll() {
        return userService.findAll();
    }
}
