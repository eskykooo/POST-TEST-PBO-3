package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Service.Service;
import Model.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Service service = new Service();

    private static int validasiInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Masukkan angka yang benar: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private static String inputTidakKosong(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        return input.isEmpty() ? "Tidak diketahui" : input;
    }

    public static void main(String[] args) {
        int pilihan = 0;
        System.out.println("========================================");
        System.out.println(" Selamat Datang di Daftar Musik Favorit!");
        System.out.println("========================================");

        while (pilihan != 6) {
            System.out.println("\n========= MENU MANAJEMEN MUSIK =========");
            System.out.println("1. Tambah Musik");
            System.out.println("2. Lihat Daftar Musik");
            System.out.println("3. Update Musik");
            System.out.println("4. Hapus Musik");
            System.out.println("5. Cari Musik");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");
            pilihan = validasiInt();

            switch (pilihan) {
                case 1 -> tambahMusik();
                case 2 -> lihatMusik();
                case 3 -> ubahMusik();
                case 4 -> hapusMusik();
                case 5 -> cariMusik();
                case 6 -> System.out.println("Bye");
                default -> System.out.println("Coba Lagi");
            }
        }
    }

    private static void tambahMusik() {
        String judul = inputTidakKosong("Masukkan judul lagu: ");
        String artis = inputTidakKosong("Masukkan nama artis: ");
        String genre = inputTidakKosong("Masukkan genre: ");

        System.out.println("Pilih tipe lagu: 1.Pop  2.Rock  3.Hipdut  4.Biasa");
        int tipe = validasiInt();
        switch (tipe) {
            case 1 -> {
                String mood = inputTidakKosong("Mood Pop: ");
                service.tambahMusik(new LaguPop(judul, artis, genre, mood));
            }
            case 2 -> {
                System.out.println("Pilih tipe Rock: 1.Soft Rock  2.Hard Rock");
                int pilihan = validasiInt();
                String tipeRock = (pilihan == 2) ? "Hard Rock" : "Soft Rock";
                service.tambahMusik(new LaguRock(judul, artis, genre, tipeRock));
            }
            case 3 -> {
                System.out.println("Tingkat asik: 1.B aja  2.Asik sih  3.Asik banget woilah");
                int pilihan = validasiInt();
                String tingkat = switch (pilihan) {
                    case 2 -> "Asik sih";
                    case 3 -> "Asik banget woilah";
                    default -> "B aja";
                };
                service.tambahMusik(new LaguHipdut(judul, artis, genre, tingkat));
            }
            default -> service.tambahMusik(new Model(judul, artis, genre));
        }
    }

    private static void lihatMusik() {
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) {
            System.out.println("Kosong");
        } else {
            System.out.println("\n========== PLAYLIST ==========");
            for (int i = 0; i < daftar.size(); i++) {
                System.out.println((i + 1) + ". " + daftar.get(i).deskripsiMusik());
            }
        }
    }

    private static void ubahMusik() {
        lihatMusik();
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) return;

        System.out.print("Pilih nomor musik yang mau diupdate: ");
        int index = validasiInt() - 1;
        if (index < 0 || index >= daftar.size()) {
            System.out.println("Nomor salah");
            return;
        }

        String judul = inputTidakKosong("Judul baru: ");
        String artis = inputTidakKosong("Artis baru: ");
        String genre = inputTidakKosong("Genre baru: ");

        System.out.println("Pilih tipe lagu baru: 1.Pop  2.Rock  3.Hipdut  4.Biasa");
        int tipe = validasiInt();
        Model musikBaru;
        switch (tipe) {
            case 1 -> {
                String mood = inputTidakKosong("Mood Pop: ");
                musikBaru = new LaguPop(judul, artis, genre, mood);
            }
            case 2 -> {
                System.out.println("Pilih tipe Rock: 1.Soft Rock  2.Hard Rock");
                int pilihan = validasiInt();
                String tipeRock = (pilihan == 2) ? "Hard Rock" : "Soft Rock";
                musikBaru = new LaguRock(judul, artis, genre, tipeRock);
            }
            case 3 -> {
                System.out.println("Tingkat asik: 1.B aja  2.Asik sih  3.Asik banget woilah");
                int pilihan = validasiInt();
                String tingkat = switch (pilihan) {
                    case 2 -> "Asik sih";
                    case 3 -> "Asik banget woilah";
                    default -> "B aja";
                };
                musikBaru = new LaguHipdut(judul, artis, genre, tingkat);
            }
            default -> musikBaru = new Model(judul, artis, genre);
        }
        service.ubahMusik(index, musikBaru);
        System.out.println("Musik berhasil diperbarui!");
    }

    private static void hapusMusik() {
        lihatMusik();
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) return;

        System.out.print("Pilih nomor musik yang mau dihapus: ");
        int index = validasiInt() - 1;
        if (service.hapusMusik(index)) {
            System.out.println("Musik dihapus");
        } else {
            System.out.println("Nomor salah");
        }
    }

    private static void cariMusik() {
        System.out.print("Masukkan judul/artis/genre): ");
        String keyword = sc.nextLine().trim().toLowerCase();
        ArrayList<Model> hasil = service.getDaftarMusik();
        boolean ditemukan = false;
        for (Model m : hasil) {
            if (m.getJudul().toLowerCase().contains(keyword) ||
                m.getArtis().toLowerCase().contains(keyword) ||
                m.getGenre().toLowerCase().contains(keyword)) {
                if (!ditemukan) {
                    System.out.println("\n===== HASIL PENCARIAN =====");
                    ditemukan = true;
                }
                System.out.println(m.deskripsiMusik());
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ditemukan musik yang cocok");
        }
    }
}
