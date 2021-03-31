package Gestiune;

public class distribuitori implements Comparable {
    private String nume;
    private String numar_telefon;
    private String adresa;
    private int timp_livrare;

    public distribuitori(String nume, String numar_telefon, String adresa, int timp_livrare) {
        this.nume = nume;
        this.numar_telefon = numar_telefon;
        this.adresa = adresa;
        this.timp_livrare = timp_livrare;
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

    public int getTimp_livrare() {
        return timp_livrare;
    }

    public void setTimp_livrare(int timp_livrare) {
        this.timp_livrare = timp_livrare;
    }


    @Override
    public int compareTo(Object o) {
        int compareage=((distribuitori)o).getTimp_livrare();
        return this.timp_livrare-compareage;

    }
}
