package com.kainos.ea.dao;

import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BandDAO {

    public List<Band> getBands(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT b.BandLevelID, b.BandLevel, b.BandName " +
                "FROM Bands b");

        List<Band> bands = new ArrayList<>();

        while (rs.next()) {
            Band b = new Band(
                    rs.getInt("BandLevelID"),
                    rs.getInt("BandLevel"),
                    rs.getString("BandName")
            );

            bands.add(b);
        }
        return bands;
    }

}
