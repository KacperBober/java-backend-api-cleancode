package com.kainos.ea.controller;

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
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
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
    private static JobRoleService roleService;
    private static JobRoleValidator jobRoleValidator;
    private static JobFamilyService jobFamilyService;
    private static BandService bandService;
    public HR() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        roleService = new JobRoleService(new JobRoleDAO(), databaseConnector);
        jobFamilyService = new JobFamilyService(new JobFamilyDAO(), databaseConnector);
        bandService = new BandService(new BandDAO(), databaseConnector);
        jobRoleValidator = new JobRoleValidator(jobFamilyService, bandService);
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

    @POST
    @Path("/job-roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRoleRequest) {
        try{
            if (jobRoleValidator.isValidJobRole(jobRoleRequest)) {
                int id = roleService.insertJobRole(jobRoleRequest);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            }else{
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        }catch (Exception | DatabaseConnectionException e ){
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
}
