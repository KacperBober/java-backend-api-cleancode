package com.kainos.ea.dao;

import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyDAO {

    public List<JobFamily> getJobFamilies(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT jf.FamilyID, jf.FamilyName FROM " +
                        "JobFamily jf;");

        List<JobFamily> jobFamilies = new ArrayList<>();

        while (rs.next()) {
            JobFamily jf = new JobFamily(
                    rs.getInt("FamilyID"),
                    rs.getString("FamilyName")
            );

            jobFamilies.add(jf);
        }
        return jobFamilies;
    }
}
