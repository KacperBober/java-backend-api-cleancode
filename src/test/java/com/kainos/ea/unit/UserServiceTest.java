package com.kainos.ea.unit;
import com.kainos.ea.dao.UserDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.User;
import com.kainos.ea.service.UserService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection conn;
    UserDAO userDao = Mockito.mock(UserDAO.class);
    UserService userService = new UserService(databaseConnector, userDao);

    @Test
    void registerUser_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {

        User user = new User("james@gmail.com", "Password12!", 1, "salt");

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(userDao.registerUser(user, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> userService.registerUser(user));
    }

    @Test
    void registerUser_shouldThrowDatabaseConnectionException_whenDAOThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        User user = new User("james@gmail.com", "Password12!", 1, "salt");

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> userService.registerUser(user));
    }

    @Test
    void registerUser_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        User user = new User("james@gmail.com", "Password12!", 1, "salt");

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> userService.registerUser(user));
    }
}
