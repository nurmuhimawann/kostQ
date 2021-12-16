package com.kostQ.views;

import com.kostQ.exceptions.RumahKostException;
import com.kostQ.models.RumahKostModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RumahKostView {
    static RumahKostModel rumahKostModel;

    public static void menuUtama() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        rumahKostModel = new RumahKostModel();
        ResultSet result =  rumahKostModel.getRumahKost();

        System.out.println("=============================");
        System.out.println("Id | Nama | Jenis | Jalan | Kota");

        while (result.next()) {
            System.out.print(result.getInt("id_rumah_kost"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_rumah_kost"));
            System.out.print(" | ");
            System.out.print(result.getString("jenis_kost"));
            System.out.print(" | ");
            System.out.print(result.getString("nama_jalan"));
            System.out.print(" | ");
            System.out.print(result.getString("kota"));
            System.out.println();
        }

        System.out.println("Menu: \n" +
                "1. Tambah Rumah Kost \n" +
                "2. Update Rumah Kost \n" +
                "3. Delete Rumah Kost \n" +
                "4. Lihat Daftar Kost \n" +
                "5. Kembali");

        System.out.print("Masukan menu pilihan: ");
        String menu = scanner.nextLine();

        switch (menu) {
            case "1":
                menuTambahRumahKost();
                break;
            case "2":
                menuUpdateRumahKost();
                break;
            case "3":
                menuDeleteRumahKost();
                break;
            case "4":
                if (result.isAfterLast()) {
                    try {
                        System.out.print("Pilih rumah kost: ");
                        int nomer = scanner.nextInt();

                        KamarKostView.menuDaftarKost(nomer);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("--------------------------");
                    System.out.println("Data rumah kost tidak ada");
                    DashboardView.backToMenu();
                }
                break;
            case "5":
                DashboardView.backToMenu();
                break;
            default:
                System.out.println("Menu tidak ada");
                DashboardView.backToMenu();
                break;
        }
    }

    public static void menuTambahRumahKost() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan nama rumah: ");
            String nama_rumah = scanner.nextLine();

            System.out.print("Masukan nama jalan: ");
            String nama_jalan = scanner.nextLine();

            System.out.print("Masukan jenis kost: ");
            String jenis_kost = scanner.nextLine();

            System.out.print("Masukan kota: ");
            String kota = scanner.nextLine();

            if (nama_rumah.isEmpty() || nama_jalan.isEmpty() || jenis_kost.isEmpty() || kota.isEmpty()) {
                throw new RumahKostException();
            }

            int result = rumahKostModel.addRumahKost(nama_rumah, nama_jalan, jenis_kost, kota);
            if (result > 0) {
                System.out.println(result + " Data berhasil ditambahkan");
            }

            DashboardView.backToMenu();
        } catch (SQLException | RumahKostException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuUpdateRumahKost() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan id rumah: ");
            int id_rumah = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukan nama rumah: ");
            String nama_rumah = scanner.nextLine();

            System.out.print("Masukan nama jalan: ");
            String nama_jalan = scanner.nextLine();

            System.out.print("Masukan jenis kost: ");
            String jenis_kost = scanner.nextLine();

            System.out.print("Masukan kota: ");
            String kota = scanner.nextLine();

            int result = rumahKostModel.updateRumahKost(nama_rumah, nama_jalan, jenis_kost, kota, id_rumah);
            if (result > 0) {
                System.out.println(result + " Data berhasil diperbarui");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuDeleteRumahKost() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan id rumah: ");
            int id_rumah = scanner.nextInt();

            int result = rumahKostModel.deleteRumahKost(id_rumah);
            if (result > 0) {
                System.out.println(result + " Data berhasil dihapus");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
