package anuan;

public class Model {
    private String judul;
    private String artis;
    private String genre;

    public Model(String judul, String artis, String genre) {
        this.judul = judul;
        this.artis = artis;
        this.genre = genre;
    }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getArtis() { return artis; }
    public void setArtis(String artis) { this.artis = artis; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String deskripsiMusik() {
        return judul + " - " + artis + " [" + genre + "]";
    }

    @Override
    public String toString() {
        return deskripsiMusik();
    }
}
