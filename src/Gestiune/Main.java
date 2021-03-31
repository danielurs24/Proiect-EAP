package Gestiune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// TO DO: Scoatere din oferta
public class Main {
    public static void main(String[] args) {

        Service Service1 = new Service();

        /////////////////////////////////////////////////////////////////////////////////// - Introducere valori default

        ArrayList<distribuitori> dist = new ArrayList<>( );
        ArrayList<categorii> categ = new ArrayList<>( );
        ArrayList<produse> produse = new ArrayList<>( );

        dist.add(new distribuitor_extern("Amazon","+44 20 7234 3456" ,"60 Holborn Viaduct, Holborn",48,"Anglia","Ungaria"));
        dist.add(new distribuitor_intern("Altex","0751338446","Bd.Bucuresti, nr 15, Bucuresti",12,"Da, prin curier"));
        Collections.sort(dist);
        Service1.setDist(dist);

        categ.add(new Electrocasnice("Aparate Frigorifice", 40, "Frigider"));
        categ.add(new Electrocasnice("Masini de spalat", 25, "Spalat Vase"));
        categ.add(new IT("Laptop-uri", 30,"Gaming"));
        categ.add(new IT("Telefoane", 25, "Smartphone"));

        Service1.setCateg(categ);

        produse.add(new Masina_de_spalat("Masina de spalat rufe Beko", 1600, Service1.getCateg().get(1), 6, 1200));
        produse.add(new Masina_de_spalat("Masina de spalat rufe Arctic", 1250, Service1.getCateg().get(1),4,800));

        produse.add(new Frigider("Frigider LG", 3700, Service1.getCateg().get(0),2,300));
        produse.add(new Frigider("Frigider Samsung", 2300, Service1.getCateg().get(0),1,225));

        produse.add(new Laptop("Laptop Gaming Lenovo", 4500, Service1.getCateg().get(2),"Intel I5",16));
        produse.add(new Laptop("Laptop Gaming Asus", 3200, Service1.getCateg().get(2),"Amd Ryzen 5",8));

        produse.add(new Telefon("Telefon Samsung S20", 3700, Service1.getCateg().get(3),108,6.5));
        produse.add(new Telefon("Telefon Huawei P40", 3700, Service1.getCateg().get(3),64,6));
//
        Service1.setProdus(produse);

///////////////////////////////////////////////////////////////////////////////////
        Scanner scanner = new Scanner(System.in);
        int opt = 1;

        while(opt != 0)
        {
            System.out.println( );
            System.out.println("Aplicatie pentru Gestionarea Stocului unui magazin");
            System.out.println("1.Adaugare produse");
            System.out.println("2.Adaugare distribuitori");
            System.out.println("3.Afisare toate produsele");
            System.out.println("4.Afisare toti distribuitorii");
            System.out.println("5.Afisare toate categoriile");
            System.out.println("6.Adaugare produse in stoc");
            System.out.println("7.Afisare distribuitori sortati");
            System.out.println("8.Cautare produs");
            System.out.println("0.Inchidere program");
            System.out.print("Alege una dintre optiuni: " );
            opt = scanner.nextInt();
            if(opt == 1)
            {
                produse.add(Service1.adaugare_produs());
                Service1.setProdus(produse);
            }
            if(opt == 2)
            {
                dist.add(Service1.adaugare_distribuitori());
                Collections.sort(dist);
                Service1.setDist(dist);

            }
            if(opt == 3)
            {
                Service1.afisare_produse();
            }
            if(opt == 4)
            {
                Service1.afisare_toti_distribuitorii();
            }
            if(opt == 5)
            {
                Service1.afisare_categorii();
            }
            if(opt == 6)
            {
                Service1.adaugare_stoc();
            }
            if(opt == 7)
            {
                Service1.afisare_distribuitori();
            }
            if(opt == 8)
            {
                Service1.cautare_produs();
            }


        }

    }
}
