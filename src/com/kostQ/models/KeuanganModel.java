package com.kostQ.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KeuanganModel extends BaseModel {
    private String tableName;

    public KeuanganModel() throws SQLException {
        super();
    }

    public int addKeuangan(String tanggal, int nominal, String keterangan, int id_rumah, int menu) throws SQLException {
        if (menu == 1) {
            tableName = "pemasukan";
        } else if (menu == 2) {
            tableName = "pengeluaran";
        }

        String query = "INSERT INTO " + tableName +
                " (tanggal, nominal, keterangan, rumah_kost_id_rumah_kost) VALUES " +
                "(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(tanggal));
        preparedStatement.setInt(2, nominal);
        preparedStatement.setString(3, keterangan);
        preparedStatement.setInt(4, id_rumah);

        return preparedStatement.executeUpdate();
    }


    public ResultSet getKeuangan(int nomer, int menu) throws SQLException {
        if (menu == 1) {
            tableName = "pemasukan";
        } else if (menu == 2) {
            tableName = "pengeluaran";
        }

        String query = "SELECT * FROM " + tableName +
                        " WHERE rumah_kost_id_rumah_kost = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, nomer);

        return preparedStatement.executeQuery();
    }

    public int updateKeuangan(int id, String tanggal, int nominal, String keterangan, int menu) throws SQLException {
        if (menu == 1) {
            tableName = "pemasukan";
        } else if (menu == 2) {
            tableName = "pengeluaran";
        }

        String query = "UPDATE " + tableName + " set " +
                "tanggal = ?, " +
                "nominal = ?, " +
                "keterangan = ? " +
                "WHERE id_" + tableName + " = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(tanggal));
        preparedStatement.setInt(2, nominal);
        preparedStatement.setString(3, keterangan);
        preparedStatement.setInt(4, id);

        return preparedStatement.executeUpdate();
    }

    public int deleteKeuangan(int id, int menu) throws SQLException {
        if (menu == 1) {
            tableName = "pemasukan";
        } else if (menu == 2) {
            tableName = "pengeluaran";
        }

        String query = "DELETE FROM " + tableName + " WHERE id_" + tableName + " = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeUpdate();
    }
}
