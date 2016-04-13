package com.mnorris.jersey.springaop.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.mnorris.jersey.springaop.rest.ComponentResource;

public class JerseyApplicationConfig extends ResourceConfig
{
    public static final String REST_PACKAGE = ComponentResource.class.getPackage()
            .getName();

    public JerseyApplicationConfig()
    {
        packages(REST_PACKAGE);
    }
}
