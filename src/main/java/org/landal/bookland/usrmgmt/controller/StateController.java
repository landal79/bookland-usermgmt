package org.landal.bookland.usrmgmt.controller;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Model
public class StateController {

    private static final List<String> STATES;

    private static final  Multimap<String, String> statesMap;

    static {
        STATES = Arrays.asList("Italia","Usa","Francia","Germania","Spagna");
        Collections.sort(STATES);

        statesMap = HashMultimap.create();
        statesMap.putAll("Italia", Arrays.asList("Bologna","Milano","Roma"));
        statesMap.putAll("Francia", Arrays.asList("Parigi","Marsiglia","Nizza"));
        statesMap.putAll("Germania", Arrays.asList("Berlino","Monaco","Dusseldorf"));
        statesMap.putAll("Spagna", Arrays.asList("Madrid","Barcellona","Valencia"));

    }

    private String selectedState;

    public void onStateSelected(ValueChangeEvent event) {
        selectedState = (String) event.getNewValue();

    }

    @Produces
    @Named
    public List<String> getStates() {
        return STATES;
    }

    public Collection<String> getCities() {
        return selectedState == null ? Collections.<String>emptyList() : statesMap.get(selectedState);
    }
}
