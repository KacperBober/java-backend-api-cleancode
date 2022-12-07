package com.kainos.ea.integration.HR;

import com.kainos.ea.model.JobRoleRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PostJobRoleTest extends HRTest{

    private static final String POST_JOB_ROLES_URL = "http://localhost:8080/hr/job-roles";

    @Test
    void postJobRole_shouldReturnJobRoleID_whenJobRoleAdded() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a new engineer role.",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

         Response response = APP.client().target(POST_JOB_ROLES_URL)
                .request()
                .post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE));

        Integer job_role_id = response.readEntity(Integer.class);
        int response_code = response.getStatus();

        Assertions.assertNotNull(job_role_id);
        Assertions.assertEquals(201, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleNameMoreThan100Char() {

        String name = StringUtils.repeat("*", 101);

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                name,
                "This is a new engineer role.",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleNameBlank() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "     ",
                "This is a new engineer role.",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleNameEmpty() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "",
                "This is a new engineer role.",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleSpecEmpty() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "tetsts",
                "",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleSpecBlank() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "tetsts",
                "     ",
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleSpecURLBlank() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "tetsts",
                "cdcdcdaa",
                "    ",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleSpecURLEmpty() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "tetsts",
                "cdcdcdaa",
                "",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobRoleSpecURLInvalid() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "tetsts",
                "cdcdcdaa",
                "htww.samanthaming.com/tidbits/94-how-to-check-if-object-is-empty/",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobSpecMoreThan1000Char() {

        String job_spec = StringUtils.repeat("*", 1001);

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                job_spec,
                "https://stackoverflow.com/questions/41255800/stop-page-from-reloading-on-failed-validation",
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobSpecURLMoreThan1000Char() {

        String job_spec_url = StringUtils.repeat("*", 1001);

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a new engineer role.",
                job_spec_url,
                14,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobSpecBandIDNotPresent() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a new engineer role.",
                StringUtils.repeat("*", 900),
                14,
                -1
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    @Test
    void postJobRole_shouldReturn400StatusCode_whenJobSpecJobFamilyIDNotPresent() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a new engineer role.",
                StringUtils.repeat("*", 900),
                -1,
                6
        );

        int response_code = getStatusCodePostJobRole(jobRoleRequest);

        Assertions.assertEquals(400, response_code);
    }

    private static int getStatusCodePostJobRole(JobRoleRequest jobRoleRequest) {
        int response_code = APP.client().target(POST_JOB_ROLES_URL)
                .request()
                .post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE))
                .getStatus();
        return response_code;
    }
}
