package Gestiune;

public class Electrocasnice extends categorii {

    private String tip;

    public Electrocasnice(String nume, int nr_produse, String tip) {
        super(nume, nr_produse);
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
