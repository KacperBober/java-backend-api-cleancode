package com.kainos.ea.service;
import com.kainos.ea.dao.UserDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.User;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    public DatabaseConnector dbConnector;

    public UserDAO userDAO;

    public UserService() {
    }

//    USER SERVICE CONSTRUCTOR
    public UserService(DatabaseConnector dbConnector, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.dbConnector = dbConnector;
    }

//    REGISTER USER METHOD
    public int registerUser(User user) throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return userDAO.registerUser(user, connection);
    }
}
