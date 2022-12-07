package com.kainos.ea.integration.HR;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class GetJobRoleInfoTest extends HRTest{

    private static final String JOB_ROLE_INFO_URL = "http://localhost:8080/hr/job-role-info";
    private static final String JOB_FAMILY = "jobFamilyList";
    private static final String BAND = "bandList";

    @Test
    public void getJobRoleInfo_GetLists(){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = APP.client().target(JOB_ROLE_INFO_URL)
                .request()
                .get(JsonNode.class);

        List<JobFamily> jobFamilyList = mapper.convertValue(
                response.get(JOB_FAMILY),
                new TypeReference<List<JobFamily>>(){}
        );

        List<Band> bandList = mapper.convertValue(
                response.get(BAND),
                new TypeReference<List<Band>>(){}
        );


        assertNotNull(jobFamilyList);
        assertNotNull(bandList);

        assertFalse(jobFamilyList.isEmpty());
        assertFalse(bandList.isEmpty());
    }
}
