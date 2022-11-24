package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpec;

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
                "SELECT jr.JobRole , c.Capability, jr.JobRoleID "
                        + "FROM JobRoles jr JOIN Capabilities c ON (jr.CapabilityID = c.CapabilityID);");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole jr = new JobRole(
                    rs.getString("JobRole"),
                    rs.getString("Capability"),
                    rs.getInt("JobRoleID")
            );

            jobRoles.add(jr);
        }
        return jobRoles;
    }

    public JobSpec getJobSpec(Connection c, int job_spec_id) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT JobRoleSpec, JobRoleSpecLink "
                        + "FROM JobRoles " +
                        "WHERE JobRoleID = " + job_spec_id + ";");

        while (rs.next()) {
            JobSpec jobSpec = new JobSpec(
                    rs.getString("JobRoleSpec"),
                    rs.getString("JobRoleSpecLink")
            );
            return jobSpec;
        }
        return null;
    }


}
