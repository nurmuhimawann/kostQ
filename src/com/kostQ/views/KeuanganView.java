package com.kostQ.views;

import com.kostQ.exceptions.KeuanganException;
import com.kostQ.models.KeuanganModel;
import com.kostQ.models.RumahKostModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class KeuanganView {
    static RumahKostModel rumahKostModel;
    static KeuanganModel keuanganModel;

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

        if (result.isAfterLast()) {
            try {
                System.out.println("=============================");
                System.out.print("Menu: \n1. Pemasukan \n2. Pengeluaran \nPilih Menu> ");
                int menu = scanner.nextInt();

                System.out.print("Pilih rumah kost: ");
                int nomer = scanner.nextInt();

                switch (menu) {
                    case 1:
                        menuKeuangan(nomer, menu);
                        break;
                    case 2:
                        menuKeuangan(nomer, menu);
                        break;
                    default:
                        System.out.println("Menu tidak ada!");
                        DashboardView.backToMenu();
                        break;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Data rumah kost tidak ada");
            DashboardView.backToMenu();
        }
    }

    public static void menuKeuangan(int nomer, int selectedMenu) throws SQLException {
        keuanganModel = new KeuanganModel();
        ResultSet result =  keuanganModel.getKeuangan(nomer, selectedMenu);

        System.out.println("=============================");
        System.out.println("Id | Tanggal | Nominal | Keterangan");

        while (result.next()) {
            if (selectedMenu == 1) {
                System.out.print(result.getInt("id_pemasukan"));
            } else if (selectedMenu == 2) {
                System.out.print(result.getInt("id_pengeluaran"));
            }
            System.out.print(" | ");
            System.out.print(result.getDate("tanggal"));
            System.out.print(" | ");
            System.out.print(result.getString("nominal"));
            System.out.print(" | ");
            System.out.print(result.getString("keterangan"));
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");

        if (selectedMenu == 1) {
            System.out.println("Menu: \n" +
                    "1. Tambah Pendapatan \n" +
                    "2. Update Pendapatan \n" +
                    "3. Delete Pendapatan");
        } else if (selectedMenu == 2) {
            System.out.println("Menu: \n" +
                    "1. Tambah Pengeluaran \n" +
                    "2. Update Pengeluaran \n" +
                    "3. Delete Pengeluaran");
        }

        System.out.print("Masukan menu pilihan: ");
        String menu = scanner.nextLine();

        switch (menu) {
            case "1" -> menuTambahKeuangan(nomer, selectedMenu);
            case "2" -> menuUpdateKeuangan(selectedMenu);
            case "3" -> menuDeleteKeuangan(selectedMenu);
            default -> {
                System.out.println("Menu tidak ada");
                DashboardView.backToMenu();
            }
        }
    }

    public static void menuTambahKeuangan(int nomer, int selectedMenu) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan tanggal: ");
            String tanggal = scanner.nextLine();

            System.out.print("Masukan nominal: ");
            int nominal = scanner.nextInt();

            scanner.nextLine();
            System.out.print("Masukan keterangan: ");
            String keterangan = scanner.nextLine();

            if (tanggal.isEmpty()) {
                throw new KeuanganException();
            }

            int result = keuanganModel.addKeuangan(tanggal, nominal, keterangan, nomer, selectedMenu);
            if (result > 0) {
                System.out.println(result + " Data berhasil ditambahkan");
            }

            DashboardView.backToMenu();
        } catch (SQLException | KeuanganException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuUpdateKeuangan(int selectedMenu) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan id: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Tanggal: ");
            String tanggal = scanner.nextLine();

            System.out.print("Nominal: ");
            int nominal = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Keterangan: ");
            String keterangan = scanner.nextLine();

            int result = keuanganModel.updateKeuangan(id, tanggal, nominal, keterangan, selectedMenu);
            if (result > 0) {
                System.out.println(result + " Data berhasil diperbarui");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuDeleteKeuangan(int selectedMenu) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukan id: ");
            int id = scanner.nextInt();

            int result = keuanganModel.deleteKeuangan(id, selectedMenu);
            if (result > 0) {
                System.out.println(result + " Data berhasil dihapus");
            }

            DashboardView.backToMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
