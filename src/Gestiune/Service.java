package Gestiune;

import java.util.ArrayList;

public class Service {

//    categorii categ_frigidere = new Electrocasnice("Aparate Frigorifice", 40, "Frigider");
//    categorii categ_masini_de_spalat = new Electrocasnice("Masini de spalat", 25, "Spalat Vase");
//    categorii categ_laptop_gaming = new IT("Laptop-uri", 30,"Gaming");
//    categorii categ_telefon = new IT("Telefoane", 25, "Smartphone");


    ArrayList<produse> produs = new ArrayList<>();

    public void setProdus(ArrayList<produse> produs) {
        this.produs = produs;
    }

    public ArrayList<produse> getProdus() {
        return produs;
    }

}
