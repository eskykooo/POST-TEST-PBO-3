package anuan;

import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Service service = new Service();

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("Selamat Datang di Daftar Musik Favorit!");
        System.out.println("=======================================");

        int pilih;
        do {
            System.out.println("\n=== SISTEM MANAJEMEN DAFTAR MUSIK ===");
            System.out.println("1. Tambah Musik");
            System.out.println("2. Lihat Daftar Musik");
            System.out.println("3. Update Musik");
            System.out.println("4. Hapus Musik");
            System.out.println("5. Cari Lagu");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilih = validasiInt();
            switch (pilih) {
                case 1 -> tambahMusik();
                case 2 -> lihatMusik();
                case 3 -> ubahMusik();
                case 4 -> hapusMusik();
                case 5 -> cariMusik();
                case 0 -> System.out.println("Semoga harimu menyenangkan");
                default -> System.out.println("Pilih sesuai nomor yang ada kocak!");
            }
        } while (pilih != 0);
    }

    private static void tambahMusik() {
        String judul = inputTidakKosong("Judul: ");
        String artis = inputTidakKosong("Artis: ");
        String genre = inputTidakKosong("Genre: ");

        System.out.println("Pilih tipe lagu: 1. Pop  2. Rock  3. Hipdut  4. Biasa");
        int tipe = validasiInt();
        Model musik;

        if (tipe == 1) {
            String mood = inputTidakKosong("Mood lagu: ");
            musik = new LaguPop(judul, artis, genre, mood);
        } else if (tipe == 2) {
            String tipeRock = pilihTipeRock();
            musik = new LaguRock(judul, artis, genre, tipeRock);
            System.out.println("Memilih tipe Rock: " + tipeRock);
        } else if (tipe == 3) {
            String tingkatAsik = pilihTingkatAsik();
            musik = new LaguHipdut(judul, artis, genre, tingkatAsik);
        } else {
            musik = new Model(judul, artis, genre);
        }
        service.tambahMusik(musik);
        System.out.println("Musik berhasil ditambahin");
    }

    private static void lihatMusik() {
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) {
            System.out.println("Hahh, kosong");
        } else {
            System.out.println("=== Playlist ===");
            int i = 1;
            for (Model m : daftar) {
                System.out.println(i + ". " + m.deskripsiMusik());
                i++;
            }
        }
    }

    private static void ubahMusik() {
        lihatMusik();
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) return;

        System.out.print("Pilih nomor musik yang mau diganti: ");
        int index = validasiInt() - 1;
        if (index < 0 || index >= daftar.size()) {
            System.out.println("Nomor takda");
            return;
        }

        String judul = inputTidakKosong("Judul baru: ");
        String artis = inputTidakKosong("Artis baru: ");
        String genre = inputTidakKosong("Genre baru: ");

        System.out.println("Pilih tipe lagu: 1. Pop  2. Rock  3. Hipdut  4. Biasa");
        int tipe = validasiInt();
        Model musikBaru;

        if (tipe == 1) {
            String mood = inputTidakKosong("Mood lagu: ");
            musikBaru = new LaguPop(judul, artis, genre, mood);
        } else if (tipe == 2) {
            String tipeRock = pilihTipeRock();
            musikBaru = new LaguRock(judul, artis, genre, tipeRock);
            System.out.println("Memilih tipe Rock: " + tipeRock);
        } else if (tipe == 3) {
            String tingkatAsik = pilihTingkatAsik();
            musikBaru = new LaguHipdut(judul, artis, genre, tingkatAsik);
        } else {
            musikBaru = new Model(judul, artis, genre);
        }
        service.ubahMusik(index, musikBaru);
        System.out.println("Musik diubah");
    }

    private static void hapusMusik() {
        lihatMusik();
        ArrayList<Model> daftar = service.getDaftarMusik();
        if (daftar.isEmpty()) return;

        System.out.print("Pilih nomor musik yang mau dihapus: ");
        int index = validasiInt() - 1;
        if (index < 0 || index >= daftar.size()) {
            System.out.println("Nomor gaada");
            return;
        }
        service.hapusMusik(index);
        System.out.println("Musik dihapus");
    }

    private static void cariMusik() {
        System.out.print("Masukkan judul/artist/genre yang mau dicari: ");
        String keyword = sc.nextLine().trim().toLowerCase();
        ArrayList<Model> hasil = service.getDaftarMusik();
        boolean ditemukan = false;
        for (Model m : hasil) {
            if (m.getJudul().toLowerCase().contains(keyword) ||
                m.getArtis().toLowerCase().contains(keyword) ||
                m.getGenre().toLowerCase().contains(keyword)) {
                if (!ditemukan) {
                    System.out.println("=== Hasil Pencarian ===");
                    ditemukan = true;
                }
                System.out.println(m.deskripsiMusik());
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada yang cocok");
        }
    }

    private static String pilihTipeRock() {
        System.out.println("Pilih tipe Rock:");
        System.out.println("1. Soft Rock");
        System.out.println("2. Hard Rock");
        int pilihan = validasiInt();
        return (pilihan == 2) ? "Hard Rock" : "Soft Rock";
    }

    private static String pilihTingkatAsik() {
        System.out.println("Pilih tingkat asik:");
        System.out.println("1. B aja");
        System.out.println("2. Asik sih");
        System.out.println("3. Asik banget woilah");
        int pilihan = validasiInt();
        return switch (pilihan) {
            case 2 -> "Asik sih";
            case 3 -> "Asik banget woilah";
            default -> "B aja";
        };
    }

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
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Jangan dikosongin, masukin aja bebas apakek");
            }
        } while (input.isEmpty());
        return input;
    }
}
