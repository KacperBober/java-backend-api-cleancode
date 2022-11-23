package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDAO jobRoleDao;
    private DatabaseConnector dbConnector;

    public JobRoleService() {
        this.jobRoleDao = new JobRoleDAO();
        this.dbConnector = new DatabaseConnector();
    }

    public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return jobRoleDao.getJobRoles(connection);
    }

}
