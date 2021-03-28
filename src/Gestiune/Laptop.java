package Gestiune;

public class Laptop extends produse {

    private String procesor;
    private int ram;


    public Laptop(String nume, double pret, categorii categorie, String procesor, int ram) {
        super(nume, pret, categorie);
        this.procesor = procesor;
        this.ram = ram;

    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

}
