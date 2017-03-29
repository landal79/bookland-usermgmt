package org.landal.bookland.usrmgmt.jsf.converters;

import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserConverter implements Converter {

    @Inject
    private UserService userService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Long id = Long.valueOf(value);
            return userService.findById(id);
        } catch (NumberFormatException e) {
            throw new ConverterException("The value is not a valid ID: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof User) {
            Long id = ((User) value).getId();
            return (id != null) ? String.valueOf(id) : null;
        } else {
            throw new ConverterException("The value is not a valid instance: " + value);
        }
    }
}
