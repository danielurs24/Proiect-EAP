package Gestiune;

public class distribuitor_extern extends distribuitori {

    private int timp_livrare;
    private String tara;
    private String depozit_apropiat;

    public distribuitor_extern(String nume, String numar_telefon, String adresa, int timp_livrare, String tara, String depozit_apropiat) {
        super(nume, numar_telefon, adresa);
        this.timp_livrare = timp_livrare;
        this.tara = tara;
        this.depozit_apropiat = depozit_apropiat;
    }

    public int getTimp_livrare() {
        return timp_livrare;
    }

    public void setTimp_livrare(int timp_livrare) {
        this.timp_livrare = timp_livrare;
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
}
