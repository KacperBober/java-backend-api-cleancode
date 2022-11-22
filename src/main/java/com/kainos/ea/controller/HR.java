package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.util.DatabaseConnector;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// @Api("API for HR app")
@Path("/hr")
public class HR {
    private DatabaseConnector c;
    public HR() {
        this.c = new DatabaseConnector();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public void getEmployees() throws DatabaseConnectionException, SQLException {

    }
}
