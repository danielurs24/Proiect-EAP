package Gestiune;

public class distribuitor_extern extends distribuitori {


    private String tara;
    private String depozit_apropiat;

    public distribuitor_extern(String nume, String numar_telefon, String adresa, int timp_livrare, String tara, String depozit_apropiat) {

        super(nume, numar_telefon, adresa, timp_livrare);
        this.tara = tara;
        this.depozit_apropiat = depozit_apropiat;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getDepozit_apropiat() {
        return depozit_apropiat;
    }

    public void setDepozit_apropiat(String depozit_apropiat) {
        this.depozit_apropiat = depozit_apropiat;
    }

    @Override
    public String toString() {
        return getNume() + " " + getNumar_telefon() + " " +
                getAdresa() + " " + getTimp_livrare() + " " + tara + " " + depozit_apropiat;
    }
}
