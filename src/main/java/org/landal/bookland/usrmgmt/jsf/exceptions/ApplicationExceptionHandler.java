package org.landal.bookland.usrmgmt.jsf.exceptions;


import javax.faces.context.FacesContext;
import javax.inject.Inject;

//@ExceptionHandler
public class ApplicationExceptionHandler {

    @Inject
    private FacesContext facesContext;

//    public void handleViewExpiredException(@Handles ExceptionEvent<ViewExpiredException> event) {
//    //    FacesContext facesContext = FacesContext.getCurrentInstance();
//
//        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "page/error");
//
//        // required - "stops" the JSF lifecycle
//        facesContext.responseComplete();
//
//        // no other JSF ExceptionHandler should handle this exception...
//        event.handled();
//    }

}
