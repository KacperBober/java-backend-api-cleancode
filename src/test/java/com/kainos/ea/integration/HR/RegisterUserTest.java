package com.kainos.ea.integration.HR;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.User;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
class RegisterUserTest {
    public static final String REGISTRATION_URL = "http://localhost:8080/hr/registration";
    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void registerUser_shouldReturn400_whenUserInvalid() {

        User user = new User("jamesjamesmail.com", "Password12!",1,"salt");

        Response response = APP.client().target(REGISTRATION_URL)
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(400, response.getStatus());
    }

    @Test
    void registerUser_shouldReturn201_whenUserValid() {

        User user = new User("james@jamesmail.com", "Password12!",1,"salt");

        Response response = APP.client().target(REGISTRATION_URL)
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(201, response.getStatus());
    }
}
