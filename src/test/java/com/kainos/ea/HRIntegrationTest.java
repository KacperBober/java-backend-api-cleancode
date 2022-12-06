package com.kainos.ea;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpec;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;
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

        assertTrue(JobRoleList.size() > 0);
        assertTrue(StringUtils.isNotBlank(JobRoleList.get(0).getCapability()));
        assertTrue(StringUtils.isNotBlank(JobRoleList.get(0).getName()));
        assertTrue(StringUtils.isNotBlank(JobRoleList.get(0).getBandName()));
    }

    @Test
    void getJobSpec_shouldReturn404StatusCode_whenJobRoleIdNotFound() {
        int job_role_id = -10;

        int response_code = APP.client().target("http://localhost:8080/hr/job-specification/" + job_role_id)
                .request()
                .get().getStatus();

       assertEquals(404, response_code);
    }

    @Test
    void getJobSpec_shouldReturnJobSpec_whenJobRoleIDExists() {
        int job_role_id = 1;
        String jobSpecURL = "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1";
        JobSpec jobSpec = new JobSpec("Software Engineer", "As a Software Engineer (Associate) in Kainos, " +
                "you’ll be responsible for developing high quality solutions which delight our " +
                "customers and impact the lives of users worldwide. You’ll do this whilst learning " +
                "about new technologies and approaches, with talented colleagues" +
                " that will help you to learn, develop and grow.", jobSpecURL);


        JobSpec response = APP.client().target("http://localhost:8080/hr/job-specification/" + job_role_id)
                .request()
                .get(JobSpec.class);

        assertEquals(jobSpec.getJob_spec(), response.getJob_spec());
        assertEquals(jobSpec.getJob_spec_link(), response.getJob_spec_link());
        assertEquals(jobSpec.getJob_role(), response.getJob_role());
    }
}