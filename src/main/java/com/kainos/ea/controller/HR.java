package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    private static JobRoleService roleService;
    public HR() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        roleService = new JobRoleService(new JobRoleDAO(), databaseConnector);
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

        try {
            return Response.ok(roleService.getJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }

    }

    @GET
    @Path("/job-specification/{JobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("JobRoleID") int job_role_id) {

        try {
            return Response.ok(roleService.getJobSpec(job_role_id)).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (JobRoleDoesNotExistException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }

    }
}
