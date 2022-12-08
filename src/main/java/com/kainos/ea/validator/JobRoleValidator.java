package com.kainos.ea.validator;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.BandService;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.service.JobRoleService;

import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.BandService;
import com.kainos.ea.service.JobFamilyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JobRoleValidator {

    private JobFamilyService jobFamilyService;
    private BandService bandService;
    private JobRoleService jobRoleService;

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_JOB_SPEC_LENGTH = 1000;
    private static final int MAX_JOB_SPEC_URL_LENGTH = 1000;

    public JobRoleValidator(JobFamilyService jobFamilyService, BandService bandService, JobRoleService jobRoleService) {
        this.jobFamilyService = jobFamilyService;
        this.bandService = bandService;
        this.jobRoleService = jobRoleService;
    }

    public boolean isValidJobRole(JobRoleRequest jobRoleRequest) throws DatabaseConnectionException, SQLException {

        List<Integer> bandListIDs = getBandIDs();
        List<Integer> jobFamilyListIDs = getJobFamilyIDs();

        boolean isNameLengthValid = isValidStringLength(jobRoleRequest.getName(), MAX_NAME_LENGTH);
        boolean isJobSpecLengthValid = isValidStringLength(jobRoleRequest.getJob_spec(), MAX_JOB_SPEC_LENGTH);
        boolean isJobSpecURLLengthValid = isValidStringLength(jobRoleRequest.getJob_spec_url(), MAX_JOB_SPEC_URL_LENGTH);
        boolean isJobFamilyIDValid = jobFamilyListIDs.contains(jobRoleRequest.getJob_family_id());
        boolean isBandIDValid = bandListIDs.contains(jobRoleRequest.getBand_level_id());
        boolean isURLAValidLink = isValidURL(jobRoleRequest);

        return isURLAValidLink && isNameLengthValid && isJobSpecLengthValid && isJobSpecURLLengthValid && isJobFamilyIDValid && isBandIDValid ;
    }

    public boolean doesJobRoleExist(int jobRoleID) throws DatabaseConnectionException, SQLException {
        return jobRoleService.getJobRoles().parallelStream().map(JobRole::getId).collect(Collectors.toList()).contains(jobRoleID);
    }

    public List<Integer> getBandIDs() throws DatabaseConnectionException, SQLException {
        return bandService.getBands().parallelStream().map(Band::getBand_level_id).collect(Collectors.toList());
    }

    public List<Integer> getJobFamilyIDs() throws DatabaseConnectionException, SQLException {
        return jobFamilyService.getJobFamilies().parallelStream().map(JobFamily::getJob_family_id).collect(Collectors.toList());
    }

    private boolean isValidStringLength(String value, int maxLength){
       return StringUtils.isNotBlank(value) && value.length() <=maxLength;
    }

    private boolean isValidURL(JobRoleRequest request) {
        try{
            // as seen from logic encapsulated within the URLValidator class
            URL url = new URL(request.getJob_spec_url());
            return true;
        }catch (MalformedURLException e){
            return false;
        }
    }
}
