package com.kainos.ea.model;

public class JobRole {

    public JobRole(String name, String capability, String bandLevel) {
        this.name = name;
        this.capability = capability;
        this.bandLevel = bandLevel;
    }

    private String name, capability, bandLevel;

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
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
