package com.kostQ.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PenyewaModel extends BaseModel {
    public PenyewaModel() throws SQLException {
        super();
    }

    public ResultSet getPenyewa() throws SQLException {
        String query = "SELECT * FROM penyewa";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        return preparedStatement.executeQuery();
    }
}
