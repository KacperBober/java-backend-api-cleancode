package com.kainos.ea;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpec;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import java.util.LinkedHashMap;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
class HRIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        List<LinkedHashMap> response = APP.client().target("http://localhost:8080/hr/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
        Assertions.assertEquals(response.get(0).get("capability"), "Engineering");
        Assertions.assertEquals(response.get(0).get("name"), "Software Engineer");

    }

    @Test
    void getJobSpec_shouldReturnNull_whenJobRoleIdNotFound() {
        String job_role_id = "-10";

        JobSpec response = APP.client().target("http://localhost:8080/hr/job-specification/" + job_role_id)
                .request()
                .get(JobSpec.class);

        Assertions.assertEquals(null, response);
    }
}