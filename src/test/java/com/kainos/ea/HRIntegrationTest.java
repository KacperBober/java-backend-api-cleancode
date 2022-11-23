package com.kainos.ea;


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
        List<JobRole> response = APP.client().target("http://localhost:8080/hr/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}