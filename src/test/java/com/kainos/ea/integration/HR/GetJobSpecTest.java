package com.kainos.ea.integration.HR;

import com.kainos.ea.model.JobSpec;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class GetJobSpecTest extends HRTest{

    private static final String JOB_SPECIFICATION_URL = "http://localhost:8080/hr/job-specification/";

    @Test
    void getJobSpec_shouldReturn404StatusCode_whenJobRoleIdNotFound() {
        String nonExistingUID = "-10";

        Integer response_code = APP.client().target(JOB_SPECIFICATION_URL + nonExistingUID)
                .request()
                .get().getStatus();

        assertNotNull(response_code);
        assertEquals(404, response_code);
    }

    @Test
    void getJobSpec_shouldReturnJobSpec_whenJobRoleIDExists() {
        String existingUID = "1";
        JobSpec response = APP.client().target(JOB_SPECIFICATION_URL + existingUID)
                .request()
                .get(JobSpec.class);

        assertTrue(StringUtils.isNotEmpty(response.getJob_spec()));
        assertTrue(StringUtils.isNotEmpty(response.getJob_spec_link()));
        assertTrue(StringUtils.isNotEmpty(response.getJob_role()));
    }
}
