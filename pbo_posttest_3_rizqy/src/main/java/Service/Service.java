package Service;

import java.util.ArrayList;
import java.util.List;
import Model.Model;

public class Service {
    private final List<Model> daftarMusik = new ArrayList<>();

    public void tambahMusik(Model musik) {
        daftarMusik.add(musik);
        System.out.println("Musik berhasil ditambahkan!");
    }

    public ArrayList<Model> getDaftarMusik() {
        return new ArrayList<>(daftarMusik);
    }

    public boolean ubahMusik(int index, Model musikBaru) {
        if (index >= 0 && index < daftarMusik.size()) {
            daftarMusik.set(index, musikBaru);
            return true;
        }
        return false;
    }

    public boolean hapusMusik(int index) {
        if (index >= 0 && index < daftarMusik.size()) {
            daftarMusik.remove(index);
            return true;
        }
        return false;
    }

    public void cariMusik(String keyword) {
        boolean ditemukan = false;
        for (Model m : daftarMusik) {
            if (m.getJudul().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(m.deskripsiMusik());
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada musik dengan judul \"" + keyword + "\".");
        }
    }
}
