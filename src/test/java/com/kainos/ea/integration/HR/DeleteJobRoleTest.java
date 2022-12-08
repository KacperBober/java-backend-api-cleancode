package com.kainos.ea.integration.HR;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import org.eclipse.jetty.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Test Coverage
 *  1. delete a newly created jobRole
 *  2. attempt to delete a jobRole that doesnt exist
 */
public class DeleteJobRoleTest extends HRTest{

    private static final String JOB_ROLES_URL = "http://localhost:8080/hr/job-roles/";

    @Test
    void deleteJobRole_JobRoleDeleted() {

        final Integer jobRoleID;
        final Integer response_code;

        try (Response insertJobRoleResponse = createJobRole(new JobRoleRequest(
                "Created JobRole To Delete",
                "This is a new engineer role.",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        ))) {

            jobRoleID = insertJobRoleResponse.readEntity(Integer.class);
            response_code = insertJobRoleResponse.getStatus();
        }

        assertNotNull(jobRoleID);
        assertEquals(201, response_code);

        // Delete the created job role
        try (Response response = APP.client().target(JOB_ROLES_URL + jobRoleID)
                .request()
                .delete()){

            assertEquals(HttpStatus.NO_CONTENT_204, response.getStatus());
        }

        List<Integer> jobRoles = getJobRoleIDs();
        assertFalse(jobRoles.contains(jobRoleID));
    }

    @Test
    void deleteJobRole_InvalidID() {

        final int jobRoleIDDoesNotExist = -1;

        // Delete the created job role
        try (Response response = APP.client().target(JOB_ROLES_URL + jobRoleIDDoesNotExist)
                .request()
                .delete()) {

            assertEquals(HttpStatus.NOT_FOUND_404, response.getStatus());
        }
    }

    private Response createJobRole(JobRoleRequest jobRoleRequest){
        return APP.client().target(JOB_ROLES_URL)
                .request()
                .post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE));
    }

    private List<Integer> getJobRoleIDs() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = APP.client().target(JOB_ROLES_URL)
                .request()
                .get(JsonNode.class);

        List<JobRole> jobRoles = mapper.convertValue(
                response,
                new TypeReference<List<JobRole>>(){}
        );

        return jobRoles.parallelStream().map(JobRole::getId).collect(Collectors.toList());
    }
}
