package com.kainos.ea.service;

import com.kainos.ea.dao.JobFamilyDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobFamilyService {

    public JobFamilyDAO jobFamilyDAO;
    public DatabaseConnector dbConnector;

    public JobFamilyService(JobFamilyDAO jobFamilyDAO, DatabaseConnector dbConnector) {
        this.jobFamilyDAO = jobFamilyDAO;
        this.dbConnector = dbConnector;
    }

    public List<JobFamily> getJobFamilies() throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return jobFamilyDAO.getJobFamilies(connection);
    }
}
