package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {

        JobRoleService roleService = new JobRoleService();
        return roleService.getJobRoles();

    }
}
