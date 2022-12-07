package com.kainos.ea.model;

public class JobFamily {
    private int job_family_id;
    private String family_name;

    public JobFamily() {
    }

    public JobFamily(int job_family_id, String family_name) {
        this.job_family_id = job_family_id;
        this.family_name = family_name;
    }

    public int getJob_family_id() {
        return job_family_id;
    }

    public void setJob_family_id(int job_family_id) {
        this.job_family_id = job_family_id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

}
