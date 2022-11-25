package com.kainos.ea;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.service.JobRoleService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

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

         //response = ObjectMapper.readValue(response, new TypeReference<List<JobRole>>() {});

//        JobRole response1 = response.stream()
//                .findFirst()
//                .get();


        //Assertions.assertTrue(response.size() > 0);

        //JobRole response1 = ((JobRole) response.get(0));

       // JobRole response1 = (JobRole) (response.get(0));

        Assertions.assertEquals(response.iterator().next().getCapability(), "Engineering");

        assertThat(bookList.get(0)).isInstanceOf(JobRole.class);
    }
}