package com.kostQ;

import com.kostQ.views.DashboardView;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DashboardView.menuAwal();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
