package com.kostQ.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseModel {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    static private int id_account;

    public Connection connection;

    public BaseModel() throws SQLException {
        dbUrl = "jdbc:postgresql://localhost/kostQ";
        dbUser = "postgres";
        dbPassword = "Smart+nt90";

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_acount) {
        this.id_account = id_acount;
    }
}
