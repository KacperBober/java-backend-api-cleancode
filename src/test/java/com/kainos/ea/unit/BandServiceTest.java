package com.kainos.ea.unit;

import com.kainos.ea.dao.BandDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.service.BandService;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
*  Test Coverage
*  1. database connection exception
*  2. SQL Exception thrown from BandDAO
*  3. Service returns EmptyList when BandDAO does
*  4. Populated List returned from Service when DAO returns Populated List
*/
@ExtendWith(MockitoExtension.class)
public class BandServiceTest {

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection connector;
    BandDAO bandDAO = Mockito.mock(BandDAO.class);
    BandService bandService = new BandService(bandDAO, databaseConnector);

    @Test
    public void getBands_shouldThrowSQLException_WhenDAOThrowsSqlException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(bandDAO.getBands(connector)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> bandService.getBands());
    }

    @Test
    public void getBands_shouldReturnEmptyList_WhenDAOReturnsEmptyList() throws DatabaseConnectionException, SQLException {
        List<Band> bands = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(bandDAO.getBands(connector)).thenReturn(bands);

        assertSame(bands, bandService.getBands());
    }

    @Test
    public void getBands_shouldReturnBands_WhenDAOReturnsBands() throws DatabaseConnectionException, SQLException {
        final Band b1 = new Band(1, "b1");
        final Band b2 = new Band(2, "b2");

        final List<Band> bands = Arrays.asList(b1, b2);

        Mockito.when(databaseConnector.getConnection()).thenReturn(connector);
        Mockito.when(bandDAO.getBands(connector)).thenReturn(bands);

        final List<Band> bandDB = bandService.getBands();
        assertSame(b1, bandDB.get(0));
        assertSame(b2, bandDB.get(1));
    }

    @Test
    public void getBands_shouldThrowDBConnectionExceptionWhenDBConnectorThrowsDBConnectionException() throws DatabaseConnectionException, SQLException {
        final Band b1 = new Band(1, "b1");
        final Band b2 = new Band(2, "b2");

        final List<Band> bands = Arrays.asList(b1, b2);

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> bandService.getBands());
    }

}
