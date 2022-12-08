package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.model.JobSpec;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    public JobRoleDAO jobRoleDao;
    public DatabaseConnector dbConnector;

    public JobRoleService(JobRoleDAO jobRoleDAO, DatabaseConnector dbConnector) {
        this.jobRoleDao =  jobRoleDAO;
        this.dbConnector = dbConnector;
    }

    public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return jobRoleDao.getJobRoles(connection);
    }

    public JobSpec getJobSpec(int job_role_id) throws DatabaseConnectionException, SQLException, JobRoleDoesNotExistException {
        Connection connection = dbConnector.getConnection();
        JobSpec jobSpec = jobRoleDao.getJobSpec(connection, job_role_id);

        if(jobSpec == null) {
            throw new JobRoleDoesNotExistException();
        }
        return jobSpec;
    }

    public int insertJobRole(JobRoleRequest jobRoleRequest) throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return jobRoleDao.insertJobRole(connection, jobRoleRequest);
    }

    public void deleteJobRole(int jobRoleID) throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        jobRoleDao.deleteJobRole(connection, jobRoleID);
    }
}
