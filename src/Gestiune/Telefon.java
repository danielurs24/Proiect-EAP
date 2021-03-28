package Gestiune;

public class Telefon extends produse {

    private int pixeli_camera;
    private double dimensiune_ecran;

    public Telefon(String nume, double pret, categorii categorie, int pixeli_camera, double dimensiune_ecran) {
        super(nume, pret, categorie);
        this.pixeli_camera = pixeli_camera;
        this.dimensiune_ecran = dimensiune_ecran;
    }

    public int getPixeli_camera() {
        return pixeli_camera;
    }

    public void setPixeli_camera(int pixeli_camera) {
        this.pixeli_camera = pixeli_camera;
    }

    public double getDimensiune_ecran() {
        return dimensiune_ecran;
    }

    public void setDimensiune_ecran(double dimensiune_ecran) {
        this.dimensiune_ecran = dimensiune_ecran;
    }
}
