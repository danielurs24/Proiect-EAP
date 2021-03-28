package Gestiune;

public class distribuitori {
    private String nume;
    private String numar_telefon;
    private String adresa;

    public distribuitori(String nume, String numar_telefon, String adresa) {
        this.nume = nume;
        this.numar_telefon = numar_telefon;
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumar_telefon() {
        return numar_telefon;
    }

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


}
