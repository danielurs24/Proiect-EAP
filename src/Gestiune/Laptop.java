package Gestiune;

public class Laptop extends produse {

    private procesor procesor;
    private int ram;


    public Laptop(String nume, double pret, categorii categorie, Gestiune.procesor procesor, int ram) {
        super(nume, pret, categorie);
        this.procesor = procesor;
        this.ram = ram;
    }

    public Gestiune.procesor getProcesor() {
        return procesor;
    }

    public void setProcesor(Gestiune.procesor procesor) {
        this.procesor = procesor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

}
