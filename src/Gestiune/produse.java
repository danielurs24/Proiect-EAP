package Gestiune;

public class produse {

   private String nume;
   private double pret;
   private categorii categorie;

    public produse(String nume, double pret, categorii categorie) {
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public categorii getCategorie() {
        return categorie;
    }

    public void setCategorie(categorii categorie) {
        this.categorie = categorie;
    }


}
