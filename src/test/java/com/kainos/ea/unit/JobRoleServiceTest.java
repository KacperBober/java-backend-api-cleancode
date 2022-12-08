package com.kainos.ea.unit;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.model.JobSpec;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import java.sql.Connection;
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
    JobRoleService jobRoleService = new JobRoleService(jobRoleDAO, databaseConnector);

    @Test
    void getJobRoles_shouldThrowSqlException_WhenDAOThrowsSqlException() throws SQLException, DatabaseConnectionException {

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.getJobRoles());
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

    @Test
    void getJobSpec_shouldReturnJobSpec_whenDaoReturnsJobSpec() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException {

        JobSpec jobSpec = new JobSpec("Data Engineer", "data engineer does smth...", "link");
        int job_role_id = 1;

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobSpec(conn, job_role_id)).thenReturn(jobSpec);

        JobSpec result_job_spec = jobRoleService.getJobSpec(job_role_id);

        assertEquals(result_job_spec, jobSpec);
    }

    @Test
    void getJobSpec_shouldThrowJobRoleDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
        int job_role_id = -10;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobSpec(conn, job_role_id)).thenReturn(null);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.getJobSpec(job_role_id));
    }

    @Test
    void getJobSpec_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {

        int job_role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.getJobSpec(conn, job_role_id)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.getJobSpec(job_role_id));
    }

    @Test
    void getJobSpec_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        int job_role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> jobRoleService.getJobSpec(job_role_id));
    }

    @Test
    void insertJobRole_shouldReturnJobRoleID_whenDAOReturnsJobRoleID() throws SQLException, DatabaseConnectionException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest();
        int job_role_id = 1;

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.insertJobRole(conn, jobRoleRequest)).thenReturn(job_role_id);

        assertEquals(job_role_id, jobRoleService.insertJobRole(jobRoleRequest));
    }

    @Test
    void insertJobRole_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest();

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobRoleService.insertJobRole(jobRoleRequest));
    }

    @Test
    void insertJobRole_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest();

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDAO.insertJobRole(conn, jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.insertJobRole(jobRoleRequest));
    }

    @Test
    void deleteJobRole_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        final int job_role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> jobRoleService.deleteJobRole(job_role_id));
    }

    @Test
    void deleteJobRole_shouldThrowSQLExceptionException_whenDAOThrowsSQLException() throws SQLException, DatabaseConnectionException {
        final int jobRoleID = 1;
        Mockito.doThrow(SQLException.class).when(jobRoleDAO).deleteJobRole(conn, jobRoleID);
        assertThrows(SQLException.class, () -> jobRoleService.deleteJobRole(jobRoleID));
    }
}