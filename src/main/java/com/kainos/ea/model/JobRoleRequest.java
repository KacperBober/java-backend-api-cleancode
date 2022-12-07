package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private String name;
    private String job_spec;
    private String job_spec_url;
    private int job_family_id;
    private int band_level_id;

    public JobRoleRequest() {
    }

    @JsonCreator
    public JobRoleRequest(@JsonProperty("jobRoleName") String name, @JsonProperty("jobSpec") String job_spec, @JsonProperty("jobSpecURL") String job_spec_url,
                          @JsonProperty("jobFamily") int job_family_id, @JsonProperty("bandLevel") int band_level_id) {
        this.name = name;
        this.job_spec = job_spec;
        this.job_spec_url = job_spec_url;
        this.job_family_id = job_family_id;
        this.band_level_id = band_level_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_spec() {
        return job_spec;
    }

    public void setJob_spec(String job_spec) {
        this.job_spec = job_spec;
    }

    public String getJob_spec_url() {
        return job_spec_url;
    }

    public void setJob_spec_url(String job_spec_url) {
        this.job_spec_url = job_spec_url;
    }

    public int getJob_family_id() {
        return job_family_id;
    }

    public void setJob_family_id(int job_family_id) {
        this.job_family_id = job_family_id;
    }

    public int getBand_level_id() {
        return band_level_id;
    }

    public void setBand_level_id(int band_level_id) {
        this.band_level_id = band_level_id;
    }
}
