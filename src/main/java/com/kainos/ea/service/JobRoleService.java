package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {
        return JobRoleDAO.getJobRoles(DatabaseConnector.getConnection());
    }

}
