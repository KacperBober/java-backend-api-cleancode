package com.kainos.ea.model;

public class JobRole {

    private String name;
    private int id;

    public String capability;
    public JobRole(String name, String capability, int id) {
        this.name = name;
        this.capability = capability;
        this.id = id;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
