package com.kainos.ea.service;

import com.kainos.ea.dao.BandDAO;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BandService {
    public BandDAO bandDAO;
    public DatabaseConnector dbConnector;

    public BandService(BandDAO bandDAO, DatabaseConnector dbConnector) {
        this.bandDAO = bandDAO;
        this.dbConnector = dbConnector;
    }

    public List<Band> getBands() throws DatabaseConnectionException, SQLException {
        Connection connection = dbConnector.getConnection();
        return bandDAO.getBands(connection);
    }
}
