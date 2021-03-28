package Gestiune;

public class Masina_de_spalat extends produse{

    private int capacitate;
    private int viteza_uscare;

    public Masina_de_spalat(String nume, double pret, categorii categorie, int capacitate, int viteza_uscare) {
        super(nume, pret, categorie);
        this.capacitate = capacitate;
        this.viteza_uscare = viteza_uscare;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public int getViteza_uscare() {
        return viteza_uscare;
    }

    public void setViteza_uscare(int viteza_uscare) {
        this.viteza_uscare = viteza_uscare;
    }
}