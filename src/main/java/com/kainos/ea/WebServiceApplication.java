package com.kainos.ea;

import com.kainos.ea.controller.HR;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebServiceApplication extends Application<WebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "WebService";
    }

    @Override
    public void initialize(final Bootstrap<WebServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final WebServiceConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new HR());
    }

}
