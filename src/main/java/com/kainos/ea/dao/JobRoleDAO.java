package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDAO {

    public List<JobRole> getJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT JobRoles.JobRole , Capabilities.Capability "
                        + "FROM JobRoles JOIN Capabilities ON (JobRoles.CapabilityID = Capabilities.CapabilityID);");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole jr = new JobRole(
                    rs.getString("JobRole"),
                    rs.getString("Capability")
            );

            jobRoles.add(jr);
        }
        return jobRoles;
    }
}
