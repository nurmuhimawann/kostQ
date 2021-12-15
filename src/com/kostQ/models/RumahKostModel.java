package com.kostQ.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RumahKostModel extends BaseModel {
    public RumahKostModel() throws SQLException {
        super();
    }

    public int addRumahKost(String nama_rumah_kost, String nama_jalan, String jenis_kost, String kota) throws SQLException {
        String query = "INSERT INTO rumah_kost " +
                "(nama_rumah_kost, nama_jalan, jenis_kost, kota, pemilik_kost_id_pemilik_kost) VALUES " +
                "(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama_rumah_kost);
        preparedStatement.setString(2, nama_jalan);
        preparedStatement.setString(3, jenis_kost);
        preparedStatement.setString(4, kota);
        preparedStatement.setInt(5, getId_account());

        return preparedStatement.executeUpdate();
    }

    public ResultSet getRumahKost() throws SQLException {
        String query = "SELECT * FROM rumah_kost " +
                        "WHERE pemilik_kost_id_pemilik_kost = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, getId_account());

        return preparedStatement.executeQuery();
    }

    public int updateRumahKost(String nama_rumah_kost, String nama_jalan, String jenis_kost, String kota, int id_rumah_kost) throws SQLException {
        String query = "UPDATE rumah_kost set " +
                "nama_rumah_kost = ?, " +
                "nama_jalan = ?, " +
                "jenis_kost = ?, " +
                "kota = ? " +
                "WHERE id_rumah_kost = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama_rumah_kost);
        preparedStatement.setString(2, nama_jalan);
        preparedStatement.setString(3, jenis_kost);
        preparedStatement.setString(4, kota);
        preparedStatement.setInt(5, id_rumah_kost);

        return preparedStatement.executeUpdate();
    }

    public int deleteRumahKost(int id_rumah_kost) throws SQLException {
        String query = "DELETE FROM rumah_kost WHERE id_rumah_kost = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_rumah_kost);

        return preparedStatement.executeUpdate();
    }
}
