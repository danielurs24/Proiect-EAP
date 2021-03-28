package Gestiune;

public class Frigider extends produse {

    private int numar_usi;
    private int volum;

    public Frigider(String nume, double pret, categorii categorie, int numar_usi, int volum) {
        super(nume, pret, categorie);
        this.numar_usi = numar_usi;
        this.volum = volum;
    }

    public int getNumar_usi() {
        return numar_usi;
    }

    public void setNumar_usi(int numar_usi) {
        this.numar_usi = numar_usi;
    }

    public int getVolum() {
        return volum;
    }

    public void setVolum(int volum) {
        this.volum = volum;
    }
}
