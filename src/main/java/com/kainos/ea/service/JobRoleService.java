package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
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

}
