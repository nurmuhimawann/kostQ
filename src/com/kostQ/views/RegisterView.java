package com.kostQ.views;

import com.kostQ.exceptions.RegisterException;
import com.kostQ.models.RegisterModel;

import java.sql.SQLException;
import java.util.Scanner;

public class RegisterView {
    static RegisterModel registerModel;

    public static void register() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Register Sebagi (pemilik/penyewa): ");
            String role = scanner.nextLine();

            System.out.print("Masukan nik: ");
            String nik = scanner.nextLine();

            System.out.print("Masukan username: ");
            String username = scanner.nextLine();

            System.out.print("Masukan password: ");
            String password = scanner.nextLine();

            System.out.print("Masukan nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukan alamat: ");
            String alamat = scanner.nextLine();

            System.out.print("Masukan no hp: ");
            String no_hp = scanner.nextLine();

            System.out.print("Masukan jenis kelamin (laki-laki/perempuan): ");
            String jenis_kelamin = scanner.nextLine();

            System.out.print("Masukan kota: ");
            String kota = scanner.nextLine();

            if (role.isEmpty() || nik.isEmpty() || username.isEmpty() || password.isEmpty() || nama.isEmpty() || alamat.isEmpty() || no_hp.isEmpty() || jenis_kelamin.isEmpty() || kota.isEmpty()) {
                throw new RegisterException();
            }

            registerModel = new RegisterModel();

            int result = registerModel.addAccount(role, nik, username, password, nama, alamat, no_hp, jenis_kelamin, kota);
            if (result > 0) {
                System.out.println("-------------------------");
                System.out.println("Akun berhasil terdaftar \nsilahkan login terlebih dahulu");
                DashboardView.backToLogin();
            }
        } catch (SQLException | RegisterException e) {
            System.out.println(e.getMessage());
        }
    }
}
