package com.kainos.ea.dao;

import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.model.User;

import java.sql.*;

public class UserDAO {
    public int registerUser(User user, Connection c) throws SQLException {

        String registerUserQuery = "insert into Users (email, password, role, salt)" +
                " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(registerUserQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, user.getEmail());
        preparedStmt.setString(2, user.getPassword());
        preparedStmt.setInt(3, user.getRole());
        preparedStmt.setString(4, user.getSalt());


        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Registering user failed, no rows affected.");
        }

        int user_id = 0;

        try (ResultSet rs = preparedStmt.getGeneratedKeys()) {
            if (rs.next()) {
                user_id = rs.getInt(1);
            }
        }

        return user_id;
    }

}
