package com.kostQ.views;

import com.kostQ.models.KamarKostModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DashboardView {
    public static void menuAwal() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("-------- Menu Awal --------");
        System.out.println("===========================");
        System.out.println("Menu: \n1. Login \n2. Registrasi \n0. Keluar");
        System.out.print("Masukan menu pilihan: ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "0":
                System.out.println("------------------");
                System.out.println("Anda telah Keluar!");
                System.out.println("------------------");
                System.exit(0);
                break;
            case "1":
                LoginView.login();
                break;
            case "2":
                RegisterView.register();
                break;
            default:
                System.out.println("Pilihan Anda Salah!");
                backToLogin();
                break;
        }
    }

    public static void menuPemilik() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================");
        System.out.println("-------- Menu Utama --------");
        System.out.println("============================");
        System.out.print("Menu: \n1. Kost \n2. Laporan Keuangan \n3. Daftar Penyewa \n0. Keluar \nPilih Menu> ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "0":
                backToLogin();
                break;
            case "1":
                RumahKostView.menuUtama();
                break;
            case "2":
                KeuanganView.menuUtama();
                break;
            case "3":
                PenyewaView.daftarPenyewa();
                break;
            default:
                System.out.println("Menu tidak ada!");
                backToMenu();
                break;
        }
    }

    public static void menuPenyewa() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("-------- Daftar Kost --------");
        System.out.println("=============================");

        KamarKostModel kostModel = new KamarKostModel();
        ResultSet result =  kostModel.getDaftarKostKosong();

        System.out.println("No | Nama | Jenis | Jalan | Kota | Fasilitas | Harga Sewa");
        int i = 1;

        while (result.next()) {
            System.out.print(i++);
            System.out.print(" | ");
            System.out.print(result.getString("nama_rumah_kost"));
            System.out.print(" | ");
            System.out.print(result.getString("jenis_kost"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_jalan"));
            System.out.print(" | ");
            System.out.print(result.getString("kota"));
            System.out.print(" | ");
            System.out.print(result.getString("fasilitas"));
            System.out.print(" | ");
            System.out.print(result.getInt("harga_sewa"));
            System.out.println();
        }

        System.out.println("============================");
        System.out.print("Menu: \n0. Keluar \nPilih Menu> ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "0":
                backToLogin();
                break;
            default:
                System.out.println("Menu tidak ada!");
                menuPenyewa();
                break;
        }
    }

    public static void backToLogin() throws SQLException {
        System.out.println("--------------------------");
        System.out.println("Tekan Enter Untuk Kembali!");
        try {
            System.in.read();
            menuAwal();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void backToMenu() throws SQLException {
        System.out.println("--------------------------");
        System.out.println("Tekan Enter Untuk Kembali!");
        try {
            System.in.read();
            menuPemilik();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
