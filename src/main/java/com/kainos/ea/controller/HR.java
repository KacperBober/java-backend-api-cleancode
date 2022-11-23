package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

        Connection conn = c.getConnection();
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * from test;");


        while (rs.next()) {
            System.out.println(rs.getString("test"));
        }
    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {

        JobRoleService roleService = new JobRoleService();

        try {
            return Response.ok(roleService.getJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }

    }
}
