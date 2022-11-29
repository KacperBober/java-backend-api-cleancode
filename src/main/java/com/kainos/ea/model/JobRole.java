package com.kainos.ea.model;

public class JobRole {


    private String name, capability, bandName;
    private int id;

    //empty constructor necessary for creation JSON in integration testing
    public JobRole() {
    }

    public JobRole(String name, String capability, String bandName, int id) {
        this.name = name;
        this.capability = capability;
        this.id = id;
        this.bandName = bandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}

