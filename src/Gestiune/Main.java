package Gestiune;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Service Service1 = new Service();

        categorii categ_frigidere = new Electrocasnice("Aparate Frigorifice", 40, "Frigider");
        categorii categ_masini_de_spalat = new Electrocasnice("Masini de spalat", 25, "Spalat Vase");
        categorii categ_laptop_gaming = new IT("Laptop-uri", 30,"Gaming");
        categorii categ_telefon = new IT("Telefoane", 25, "Smartphone");


        ArrayList<produse> produse = new ArrayList<>( );


        produse.add(new Masina_de_spalat("Masina de spalat rufe Beko", 1600, categ_masini_de_spalat, 6, 1200));
        produse.add(new Masina_de_spalat("Masina de spalat rufe Arctic", 1250, categ_masini_de_spalat,4,800));

        produse.add(new Frigider("Frigider LG", 3700, categ_frigidere,2,300));
        produse.add(new Frigider("Frigider Samsung", 2300, categ_frigidere,1,225));

        produse.add(new Laptop("Laptop Gaming Lenovo", 4500, categ_laptop_gaming,"Intel I5",16));
        produse.add(new Laptop("Laptop Gaming Asus", 3200, categ_frigidere,"Amd Ryzen 5",8));

        produse.add(new Telefon("Telefon Samsung S20", 3700, categ_telefon,108,6.5));
        produse.add(new Telefon("Telefon Huawei P40", 3700, categ_telefon,64,6));



        Service1.setProdus(produse);


        for(int i = 0; i < produse.size(); i++)
        {
            System.out.println(Service1.getProdus().get(i).getNume() + " " + Service1.getProdus().get(i).getCategorie().getNume());
        }





    }
}
