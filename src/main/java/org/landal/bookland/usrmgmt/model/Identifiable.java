package org.landal.bookland.usrmgmt.model;


import java.io.Serializable;

public interface Identifiable<PK extends Serializable> {

    PK getId();
}

