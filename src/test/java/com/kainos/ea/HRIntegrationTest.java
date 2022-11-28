package com.kainos.ea;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
class HRIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = APP.client().target("http://localhost:8080/hr/job-roles")
                .request()
                .get(JsonNode.class);

        List<JobRole> JobRoleList = mapper.convertValue(
                response,
                new TypeReference<List<JobRole>>(){}
        );

        Assertions.assertTrue(JobRoleList.size() > 0);
        Assertions.assertEquals(JobRoleList.get(0).getCapability(), "Engineering");
        Assertions.assertEquals(JobRoleList.get(0).getName(), "Software Engineer");
        Assertions.assertEquals(JobRoleList.get(0).getBandName(), "Associate");
    }
}