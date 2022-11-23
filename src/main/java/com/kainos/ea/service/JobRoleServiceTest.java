package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.util.DatabaseConnector;
import java.sql.Connection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection conn;
    JobRoleDAO jobRoleDAO = Mockito.mock(JobRoleDAO.class);
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

        @Test
        void getJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {

            Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
            Mockito.when(jobRoleService.getJobRoles()).thenThrow(SQLException.class);

            assertThrows(SQLException.class,
                    () -> jobRoleService.getJobRoles());
        }
}