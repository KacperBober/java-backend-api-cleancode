package com.kainos.ea.unit;


import com.kainos.ea.dao.JobFamilyDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 *  Test Coverage
 *  1. database connection exception
 *  2. SQL Exception thrown from JobFamilyDAO
 *  3. Service returns EmptyList when JobFamilyDAO does
 *  4. Populated List returned from Service when DAO returns Populated List
 */
@ExtendWith(MockitoExtension.class)
public class JobFamilyServiceTest {

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection connector;
    JobFamilyDAO jobFamilyDAO = Mockito.mock(JobFamilyDAO.class);
    JobFamilyService jobFamilyService = new JobFamilyService(jobFamilyDAO, databaseConnector);


    @Test
    public void getJobFamily_shouldThrowSQLException_WhenDAOThrowsSqlException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(jobFamilyDAO.getJobFamilies(connector)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobFamilyService.getJobFamilies());
    }

    @Test
    public void getBands_shouldReturnEmptyList_WhenDAOReturnsEmptyList() throws DatabaseConnectionException, SQLException {
        List<JobFamily> jobFamily = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(jobFamilyDAO.getJobFamilies(connector)).thenReturn(jobFamily);

        assertSame(jobFamily, jobFamilyService.getJobFamilies());
    }

    @Test
    public void getBands_shouldReturnBands_WhenDAOReturnsBands() throws DatabaseConnectionException, SQLException {
        final JobFamily f1 = new JobFamily(1, "f1");
        final JobFamily f2 = new JobFamily(2, "f2");

        final List<JobFamily> jobFamily = Arrays.asList(f1, f2);

        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(jobFamilyDAO.getJobFamilies(connector)).thenReturn(jobFamily);

        final List<JobFamily> jobFamilyDB = jobFamilyService.getJobFamilies();
        assertSame(f1, jobFamilyDB.get(0));
        assertSame(f2, jobFamilyDB.get(1));
    }

    @Test
    public void getBands_shouldThrowDBConnectionException_WhenDBConnectorThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {
        final JobFamily f1 = new JobFamily(1, "f1");
        final JobFamily f2 = new JobFamily(2, "f2");

        final List<JobFamily> jobFamily = Arrays.asList(f1, f2);

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> jobFamilyService.getJobFamilies());
    }
}
