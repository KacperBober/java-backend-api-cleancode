package com.kainos.ea.controller;
import com.kainos.ea.dao.BandDAO;
import com.kainos.ea.dao.JobFamilyDAO;
import com.kainos.ea.dao.UserDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.*;

import com.kainos.ea.dao.BandDAO;
import com.kainos.ea.dao.JobFamilyDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.BandService;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.service.UserService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
import com.kainos.ea.validator.UserValidator;
import com.kainos.ea.wrapper.JobRoleInfo;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import com.kainos.ea.wrapper.JobRoleInfo;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

// @Api("API for HR app")
@Path("/hr")
@Api("Engineering Academy Dropwizard API")
public class HR {
    private DatabaseConnector c;
    private JobRoleService roleService;
    private JobRoleValidator jobRoleValidator;
    private JobFamilyService jobFamilyService;
    private BandService bandService;
    private UserService userService;
    private UserValidator userValidator;

    public HR() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        roleService = new JobRoleService(new JobRoleDAO(), databaseConnector);
        jobFamilyService = new JobFamilyService(new JobFamilyDAO(), databaseConnector);
        bandService = new BandService(new BandDAO(), databaseConnector);
        jobRoleValidator = new JobRoleValidator(jobFamilyService, bandService, roleService);
        userValidator = new UserValidator();
        userService = new UserService(databaseConnector, new UserDAO());
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

    @POST
    @Path("/job-roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRoleRequest) {
        try {
            if (jobRoleValidator.isValidJobRole(jobRoleRequest)) {
                try {
                    int id = roleService.insertJobRole(jobRoleRequest);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
                } catch (Exception | DatabaseConnectionException e) {
                    System.out.println(e);
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (Exception | DatabaseConnectionException e) {
            // TODO specific JobRoleValidation Exceptions
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @GET
    @Path("/job-role-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRolesInfo() {
        try {
            List<JobFamily> jobFamilies = jobFamilyService.getJobFamilies();
            List<Band> bands = bandService.getBands();
            return Response.ok(new JobRoleInfo(jobFamilies, bands)).build();
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
            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
    }

    @DELETE
    @Path("/job-roles/{JobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("JobRoleID") int jobRoleId) {
        try {
            if (jobRoleValidator.doesJobRoleExist(jobRoleId)) {
                roleService.deleteJobRole(jobRoleId);
                return Response.status(HttpStatus.NO_CONTENT_204).build();
            }else{
                return Response.status(HttpStatus.NOT_FOUND_404).build();
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        try {
            if (userValidator.isValidUser(user)) {
                    int id = userService.registerUser(user);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (Exception | DatabaseConnectionException e) {
             return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
