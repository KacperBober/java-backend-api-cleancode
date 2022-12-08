package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.model.JobSpec;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDAO {

    public List<JobRole> getJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT jr.JobRole , c.Capability, jr.JobRoleID, b.BandName "
                        + "FROM JobRoles jr JOIN JobFamily jf ON (jr.JobFamilyID = jf.FamilyID) " +
                        "JOIN Bands b ON (jr.JobBandLevelID = b.BandLevelID) " +
                        "JOIN Capabilities c ON (jf.capabilityID = c.capabilityID);");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole jr = new JobRole(
                    rs.getString("JobRole"),
                    rs.getString("Capability"),
                    rs.getString("BandName"),
                    rs.getInt("JobRoleID"));
            jobRoles.add(jr);
        }
        return jobRoles;
    }

    public JobSpec getJobSpec(Connection c, int job_spec_id) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT JobRole, JobRoleSpec, JobRoleSpecLink "
                        + "FROM JobRoles " +
                        "WHERE JobRoleID = " + job_spec_id + ";");

        while (rs.next()) {
            JobSpec jobSpec = new JobSpec(
                    rs.getString("JobRole"),
                    rs.getString("JobRoleSpec"),
                    rs.getString("JobRoleSpecLink")
            );
            return jobSpec;
        }
        return null;
    }

    public int insertJobRole(Connection c, JobRoleRequest jobRoleRequest) throws SQLException {

        String insertJobRoleQuery = "insert into JobRoles (JobRole, JobRoleSpec, JobRoleSpecLink, JobBandLevelID," +
                " JobFamilyID)"
                + " values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(insertJobRoleQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, jobRoleRequest.getName());
        preparedStmt.setString(2, jobRoleRequest.getJob_spec());
        preparedStmt.setString(3, jobRoleRequest.getJob_spec_url());
        preparedStmt.setInt(4, jobRoleRequest.getBand_level_id());
        preparedStmt.setInt(5, jobRoleRequest.getJob_family_id());


        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating JobRole failed, no rows affected.");
        }

        int job_role_id = 0;

        try (ResultSet rs = preparedStmt.getGeneratedKeys()) {
            if (rs.next()) {
                job_role_id = rs.getInt(1);
            }
        }

        return job_role_id;
    }

    public void deleteJobRole(Connection c, int jobRoleID) throws SQLException {
        Statement st = c.createStatement();
        String query = "DELETE from JobRoles where JobRoleID = " + jobRoleID + ";";
        st.execute(query);
    }
}
