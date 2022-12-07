package com.kainos.ea.wrapper;

import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;

import java.util.List;

public class JobRoleInfo {
    private List<JobFamily> jobFamilyList;
    private List<Band> bandList;

    public JobRoleInfo(List<JobFamily> jobFamilyList, List<Band> bandList) {
        this.jobFamilyList = jobFamilyList;
        this.bandList = bandList;
    }

    public List<JobFamily> getJobFamilyList() {
        return jobFamilyList;
    }

    public List<Band> getBandList() {
        return bandList;
    }
}
