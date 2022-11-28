package com.kainos.ea.model;

public class JobRole {

    private String name;

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String capability;
    public JobRole(String name, String capability) {
        this.name = name;
        this.capability = capability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
