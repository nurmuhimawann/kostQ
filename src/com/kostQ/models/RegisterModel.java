package com.kostQ.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterModel extends BaseModel {
    private String tableName;

    public RegisterModel() throws SQLException {
        super();
    }

    public int addAccount(String role, String nik, String username, String password, String nama, String alamat, String no_hp, String jenis_kelamin, String kota) throws SQLException {
        if (Objects.equals(role, "pemilik")) {
            tableName = "pemilik_kost";
        } else if (Objects.equals(role, "penyewa")) {
            tableName = "penyewa";
        }

        String query = "INSERT INTO " + tableName +
                        " (nik, username, password, nama_lengkap, nama_jalan, nomor_hp, jenis_kelamin, kota) VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nik);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, nama);
        preparedStatement.setString(5, alamat);
        preparedStatement.setString(6, no_hp);
        preparedStatement.setString(7, jenis_kelamin);
        preparedStatement.setString(8, kota);

        return preparedStatement.executeUpdate();
    }
}
