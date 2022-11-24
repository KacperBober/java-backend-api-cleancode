package com.kainos.ea;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection conn;
    JobRoleDAO jobRoleDAO = Mockito.mock(JobRoleDAO.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDAO,databaseConnector);

    @Test
    void getJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleService.getJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getJobRoles());
    }

    @Test
    void getJobRoles_shouldThrowException_whenListisNull() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobRoles(conn)).thenReturn(new ArrayList<>());

        assertEquals(Collections.EMPTY_LIST, jobRoleService.getJobRoles());
    }

    @Test
    void getJobRoles_shouldThrowDatabaseConnectionException_whenDAOThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobRoleService.getJobRoles());
    }

    @Test
    void getJobRoles_shouldReturnList_whenDaoReturnsList() throws SQLException, DatabaseConnectionException {
        List<JobRole> job = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobRoles(conn)).thenReturn(job);

        List<JobRole> result = jobRoleDAO.getJobRoles(conn);

        assertEquals(result, job);
    }
}