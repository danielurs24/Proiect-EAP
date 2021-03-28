package Gestiune;

public class categorii {

    private String nume;
    private int nr_produse;

    public categorii(String nume, int nr_produse) {
        this.nume = nume;
        this.nr_produse = nr_produse;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNr_produse() {
        return nr_produse;
    }

    public void setNr_produse(int nr_produse) {
        this.nr_produse = nr_produse;
    }

}
