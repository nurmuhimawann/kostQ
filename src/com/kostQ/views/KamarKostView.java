package com.kostQ.views;

import com.kostQ.exceptions.KamarKostException;
import com.kostQ.models.KamarKostModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class KamarKostView {
    static KamarKostModel kamarKostModel;

    public static void menuDaftarKost(int nomer) throws SQLException {
        kamarKostModel = new KamarKostModel();
        ResultSet result =  kamarKostModel.getKost(nomer);

        System.out.println("=============================");
        System.out.println("Kode kamar | Nama Kamar | Fasilitas | Penyewa");

        while (result.next()) {
            System.out.print(result.getInt("kode_kamar"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_kamar"));
            System.out.print(" | ");
            System.out.print(result.getString("fasilitas"));
            System.out.print(" | ");
            System.out.print(result.getInt("harga_sewa"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_lengkap"));
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu: \n" +
                "1. Tambah Kamar \n" +
                "2. Update Kamar \n" +
                "3. Delete Kamar \n" +
                "4. Kembali");

        System.out.print("Masukan menu pilihan: ");
        String menu = scanner.nextLine();

        switch (menu) {
            case "1":
                menuTambahKost(nomer);
                break;
            case "2":
                menuUpdateKost();
                break;
            case "3":
                menuDeleteKost();
                break;
            case "4":
                RumahKostView.menuUtama();
                break;
            default:
                System.out.println("Menu tidak ada");
                DashboardView.backToMenu();
                break;
        }
    }

    public static void menuTambahKost(int nomer) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan kode kamar: ");
            int kode_kamar = scanner.nextInt();

            scanner.nextLine();

            System.out.print("Masukan nama kamar: ");
            String nama_kamar = scanner.nextLine();

            System.out.print("Masukan fasilitas: ");
            String fasilitas = scanner.nextLine();

            System.out.print("Masukan harga sewa: ");
            int harga_sewa = scanner.nextInt();


            if (nama_kamar.isEmpty()) {
                throw new KamarKostException();
            }

            int result = kamarKostModel.addKost(kode_kamar, nama_kamar, fasilitas, harga_sewa, nomer);
            if (result > 0) {
                System.out.println(result + " Data berhasil ditambahkan");
            }

            DashboardView.backToMenu();
        } catch (SQLException | KamarKostException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuUpdateKost() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan kode kamar: ");
            int kode_kamar = scanner.nextInt();

            System.out.print("Fasilitas: ");
            scanner.nextLine();
            String fasilitas = scanner.nextLine();

            System.out.print("Harga Sewa: ");
            int harga_sewa = scanner.nextInt();

            System.out.print("Penyewa: ");
            int penyewa = scanner.nextInt();

            int result = kamarKostModel.updateKost(kode_kamar, fasilitas, harga_sewa, penyewa);
            if (result > 0) {
                System.out.println(result + " Data berhasil diperbarui");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuDeleteKost() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan kode kamar: ");
            int kode_kamar = scanner.nextInt();

            int result = kamarKostModel.deleteKost(kode_kamar);
            if (result > 0) {
                System.out.println(result + " Data berhasil dihapus");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
