package com.kainos.ea.integration.HR;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DropwizardExtensionsSupport.class)
public class GetJobRolesTest extends HRTest{

    private static final String JOB_ROLES_URL = "http://localhost:8080/hr/job-roles";

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = APP.client().target(JOB_ROLES_URL)
                .request()
                .get(JsonNode.class);

        List<JobRole> JobRoleList = mapper.convertValue(
                response,
                new TypeReference<List<JobRole>>(){}
        );

        assertTrue(JobRoleList.size() > 0);
        assertTrue(StringUtils.isNotEmpty(JobRoleList.get(0).getCapability()));
        assertTrue(StringUtils.isNotEmpty(JobRoleList.get(0).getName()));
        assertTrue(StringUtils.isNotEmpty(JobRoleList.get(0).getBandName()));
        // since getId would return primitive int type it is always populated
    }
}
