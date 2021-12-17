package com.kostQ.models;

import com.kostQ.views.DashboardView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginModel extends BaseModel {
    private String tableName;

    public LoginModel() throws SQLException {
        super();
    }

    public void checkAcount(String role, String username, String password) throws SQLException {
        if (Objects.equals(role, "pemilik")) {
            tableName = "pemilik_kost";
        } else if (Objects.equals(role, "penyewa")) {
            tableName = "penyewa";
        }

         String query = "SELECT * FROM " + tableName +
                        " WHERE username = ? " +
                        "AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try {
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String username2 = result.getString("username");
                String password2 = result.getString("password");
                if (Objects.equals(username2, username) && Objects.equals(password2, password)) {
                    setId_account(result.getInt("id_" + tableName));
                    System.out.println("Login Berhasil");
                    if (Objects.equals(role, "pemilik")) {
                        DashboardView.menuPemilik();
                    } else if (Objects.equals(role, "penyewa")) {
                        DashboardView.menuPenyewa();
                    }
                }
            } else {
                System.out.println("Username atau password salah !");
                DashboardView.backToLogin();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
