package Gestiune;

public class distribuitor_intern extends distribuitori {


    private String livrare_la_sediu;

    public distribuitor_intern(String nume, String numar_telefon, String adresa, int timp_livrare, String livrare_la_sediu) {
        super(nume, numar_telefon, adresa, timp_livrare);
        this.livrare_la_sediu = livrare_la_sediu;
    }

    public String getDepozit_apropiat() {
        return livrare_la_sediu;
    }

    public void setDepozit_apropiat(String depozit_apropiat) {
        this.livrare_la_sediu = depozit_apropiat;
    }

    @Override
    public String toString() {
        return getNume() + " " + getNumar_telefon() + " " +
                getAdresa() + " " + getTimp_livrare() + " " + livrare_la_sediu;
    }
}
