package anuan;

import java.util.ArrayList;

public class Service {
    private final ArrayList<Model> daftarMusik = new ArrayList<>();

    public void tambahMusik(Model musik) {
        daftarMusik.add(musik);
    }

    public ArrayList<Model> getDaftarMusik() {
        return daftarMusik;
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
}
