package anuan;

public class LaguRock extends Model {
    private String tipeRock;

    public LaguRock(String judul, String artis, String genre, String tipeRock) {
        super(judul, artis, genre);
        this.tipeRock = tipeRock;
    }

    public String getTipeRock() { return tipeRock; }
    public void setTipeRock(String tipeRock) { this.tipeRock = tipeRock; }

    @Override
    public String deskripsiMusik() {
        return super.deskripsiMusik() + " ~ Rock Type: " + tipeRock;
    }
}
