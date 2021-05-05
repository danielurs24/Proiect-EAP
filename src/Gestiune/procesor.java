package Gestiune;

public class procesor {

    private String producator;
    private String model;
    private String frecventa;

    public procesor(String producator, String model, String frecventa) {
        this.producator = producator;
        this.model = model;
        this.frecventa = frecventa;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFrecventa() {
        return frecventa;
    }

    public void setFrecventa(String frecventa) {
        this.frecventa = frecventa;
    }
}
