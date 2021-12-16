package com.kostQ.views;

import com.kostQ.models.LoginModel;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginView {
    static LoginModel loginModel;

    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login Sebagi (pemilik/penyewa): ");
        String role = scanner.nextLine();

        System.out.print("Masukan username: ");
        String username = scanner.nextLine();

        System.out.print("Masukan password: ");
        String password = scanner.nextLine();

        try {
            loginModel = new LoginModel();

            loginModel.checkAcount(role, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
