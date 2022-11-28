package com.kainos.ea.model;

public class JobRole {

    //empty constructor necessary for creation JSON in integration testing
    public JobRole () {
    }

    public JobRole(String name, String capability, String bandLevel) {
        this.name = name;
        this.capability = capability;
        this.bandName = bandLevel;
    }

    private String name, capability, bandName;

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
