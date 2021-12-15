package com.kostQ.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KamarKostModel extends BaseModel {
    public KamarKostModel() throws SQLException {
        super();
    }

    public int addKost(int kode_kamar, String nama_kamar, String fasilitas, int harga_sewa, int id_rumah) throws SQLException {
        String query = "INSERT INTO kamar_kost " +
                "(kode_kamar, nama_kamar, fasilitas, harga_sewa, rumah_kost_id_rumah_kost) VALUES " +
                "(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, kode_kamar);
        preparedStatement.setString(2, nama_kamar);
        preparedStatement.setString(3, fasilitas);
        preparedStatement.setInt(4, harga_sewa);
        preparedStatement.setInt(5, id_rumah);

        return preparedStatement.executeUpdate();
    }

    public ResultSet getKost(int nomer) throws SQLException {
        String query = "SELECT * FROM kamar_kost " +
                        "FULL JOIN penyewa on kamar_kost.penyewa_id_penyewa = penyewa.id_penyewa " +
                        "WHERE rumah_kost_id_rumah_kost = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, nomer);

        return preparedStatement.executeQuery();
    }

    public int updateKost(int kode_kamar, String fasilitas, int harga_sewa, int penyewa) throws SQLException {
        if (penyewa == 0) {
             String query = "UPDATE kamar_kost set " +
                    "fasilitas = ?, " +
                    "harga_sewa = ? " +
                    "WHERE kode_kamar = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fasilitas);
            preparedStatement.setInt(2, harga_sewa);
            preparedStatement.setInt(3, kode_kamar);
            return preparedStatement.executeUpdate();
        } else {
             String query = "UPDATE kamar_kost set " +
                    "fasilitas = ?, " +
                    "harga_sewa = ?, " +
                    "penyewa_id_penyewa = ? " +
                    "WHERE kode_kamar = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fasilitas);
            preparedStatement.setInt(2, harga_sewa);
            preparedStatement.setInt(3, penyewa);
            preparedStatement.setInt(4, kode_kamar);
            return preparedStatement.executeUpdate();
        }
    }

    public int deleteKost(int kode_kamar) throws SQLException {
        String query = "DELETE FROM kamar_kost WHERE kode_kamar = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, kode_kamar);

        return preparedStatement.executeUpdate();
    }

    public ResultSet getDaftarKostKosong() throws SQLException {
        String query = "SELECT * FROM kamar_kost "+
                "INNER JOIN rumah_kost ON kamar_kost.rumah_kost_id_rumah_kost = rumah_kost.id_rumah_kost " +
                "WHERE penyewa_id_penyewa is null";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        return preparedStatement.executeQuery();
    }
}
