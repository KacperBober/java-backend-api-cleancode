package com.kainos.ea.model;

public class JobSpec {
    private String job_spec;
    private String job_spec_link;

    public JobSpec() {

    }

    public JobSpec(String job_spec, String job_spec_link) {
        this.job_spec = job_spec;
        this.job_spec_link = job_spec_link;
    }

    public String getJob_spec_link() {
        return job_spec_link;
    }

    public void setJob_spec_link(String job_spec_link) {
        this.job_spec_link = job_spec_link;
    }

    public String getJob_spec() {
        return job_spec;
    }

    public void setJob_spec(String job_spec) {
        this.job_spec = job_spec;
    }
}
