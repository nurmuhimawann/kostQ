package com.kostQ.views;

import com.kostQ.models.PenyewaModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PenyewaView {
    public static void daftarPenyewa() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("-------- Daftar Penyewa --------");
        System.out.println("=============================");

        PenyewaModel penyewaModel = new PenyewaModel();
        ResultSet result =  penyewaModel.getPenyewa();

        System.out.println("Id | Nama | Jenis Kelamin | Nomor Hp");

        while (result.next()) {
            System.out.print(result.getString("id_penyewa"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_lengkap"));
            System.out.print(" | ");
            System.out.print(result.getString("jenis_kelamin"));
            System.out.print(" | ");
            System.out.print(result.getString("nomor_hp"));
            System.out.println();
        }

        System.out.println("============================");
        System.out.print("Menu: \n0. Kembali \nPilih Menu> ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "0":
                DashboardView.backToMenu();
                break;
            default:
                System.out.println("Menu tidak ada!");
                DashboardView.backToMenu();
                break;
        }
    }
}
