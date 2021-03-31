package Gestiune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Service {

//    ArrayList<categorii> categorie = new ArrayList<>();
//
//   categorie.add(new Electrocasnice("Aparate Frigorifice", 40, "Frigider"));
//   categorii categ_masini_de_spalat = new Electrocasnice("Masini de spalat", 25, "Spalat Vase");
//   categorii categ_laptop_gaming = new IT("Laptop-uri", 30,"Gaming");
//   categorii categ_telefon = new IT("Telefoane", 25, "Smartphone");


    ArrayList<produse> produs = new ArrayList<>();
    ArrayList<categorii> categ = new ArrayList<>();
    ArrayList<distribuitori> dist = new ArrayList<>( );

    public ArrayList<distribuitori> getDist() {
        return dist;
    }

    public void setDist(ArrayList<distribuitori> dist) {
        this.dist = dist;
    }

    public ArrayList<categorii> getCateg() {
        return categ;
    }

    public void setCateg(ArrayList<categorii> categ) {
        this.categ = categ;
    }

    public void setProdus(ArrayList<produse> produs) {
        this.produs = produs;
    }

    public ArrayList<produse> getProdus() {
        return produs;
    }



    public produse adaugare_produs()
    {
        System.out.println("Categorii");
        for (int i=0; i<getCateg().size(); i ++)
        {   int j = i + 1;
            System.out.println( j + "." + getCateg( ).get(i).getNume());
        }
        System.out.println("0.Meniul Anterior");
        produse produs_adaugare = null;
        System.out.print("Alege una din categorii: ");
        Scanner scanner = new Scanner(System.in);
        int categ = scanner.nextInt();
        categ--;
        if (categ == 0)
        {   System.out.println("Introduceti Numele Frigiderului, Pretul frigiderului, Numarul Usilor, Volumul Frigiderului");

            String nume_frigider = scanner.next();
            int pret_frigider = scanner.nextInt();
            //int categ = scanner.nextInt();
            int numar_usi_frigider = scanner.nextInt();
            int volum_frigider = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Frigider(nume_frigider,pret_frigider,cat,numar_usi_frigider,volum_frigider);
            //return frigider;
        }
        if (categ == 1)
        {   System.out.println("Introduceti Numele Masinii de spalat, Pret, Capacitatea, Viteza de uscare");

            String nume = scanner.next();
            int pret = scanner.nextInt();
            //int categ = scanner.nextInt();
            int capacitate = scanner.nextInt();
            int viteza_uscare = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Masina_de_spalat(nume,pret,cat,capacitate,viteza_uscare);
            //return masina_de_spalat;
        }
        if (categ == 3)
        {   System.out.println("Introduceti Numele Telefonului, Pret, Numarul de pixeli ai camerei , Dimensiunea Ecranului");

            String nume = scanner.next();
            int pret = scanner.nextInt();
            //int categ = scanner.nextInt();
            int pixeli = scanner.nextInt();
            int dimensiune = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Telefon(nume,pret,cat,pixeli,dimensiune);
            //return telefon;
        }
        if (categ == 2)
        {   System.out.println("Introduceti Numele Laptopului, Pret, Procesor, Ram");

            String nume = scanner.next();
            int pret = scanner.nextInt();
            //int categ = scanner.nextInt();
            String procesor = scanner.next();
            int ram = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Laptop(nume,pret,cat,procesor,ram);
            //return frigider;
        }

        return produs_adaugare;
    }
    public void cautare_produs()
    {   int val = 1;
        Scanner scanner = new Scanner(System.in);
        int categ = 1;
        while (val != 0) {

            System.out.println("CAUTARE PRODUSE");
            System.out.println("1.Cautare dupa Categorie");
            System.out.println("2.Cautare dupa Nume");
            System.out.println("0.Meniul Anterior");
            val = scanner.nextInt( );

            if (val == 1) {
                while (categ != -1) {
                    System.out.println("Categorii");

                    for (int i = 0; i < getCateg( ).size( ); i++) {

                        int j = i + 1;

                        System.out.println(j + "." + getCateg( ).get(i).getNume( ));
                    }

                    System.out.println("0.Meniul Anterior");
                    System.out.print("Alege una din categorii: ");
                    categ = scanner.nextInt( );
                    System.out.println( );
                    categ--;

                    if (categ != -1) {

                        for (int i = 0; i < getProdus( ).size( ); i++) {
                            if(getProdus().get(i) != null)
                                if (getProdus( ).get(i).getCategorie( ) == getCateg( ).get(categ)) {

                                    System.out.println(getProdus( ).get(i).getNume( ) + " " + getProdus( ).get(i).getPret( ));
                                }

                        }
                    }

                    System.out.println( );

                }
            } else if (val == 2) {
                String nume = "start";
                while (nume.length( ) > 1) {
                    System.out.println( );
                    System.out.println("0.Meniul Anterior");
                    System.out.print("Introduceti numele produsului: ");

                    nume = scanner.next( );
                    System.out.println( );
                    for (int i = 0; i < getProdus( ).size( ); i++) {
                        if(getProdus().get(i) != null)
                            if (getProdus( ).get(i).getNume( ).contains(nume))
                                System.out.println(getProdus( ).get(i).getNume( ) + " " + getProdus( ).get(i).getPret( ));
                    }
                }
            }
        }
    }

    public void adaugare_stoc()
    {   int categ = 1;
        Scanner scanner = new Scanner(System.in);
        while (categ != 0) {

            System.out.println("Comanda Stoc nou");
            System.out.println("1.Comanda Urgenta (cel mai mic timp de livrare)");
            System.out.println("2.Comanda Normala - Alege unul dintre magazine");
            System.out.println("0.Meniul Anterior");
            categ = scanner.nextInt( );
            int categ1 = 1;
            int distribuitor = 1;
            if (categ == 1) {
                for (int i = 0; i < 1; i++) {

                    System.out.println("Cel mai rapid distribuitor este " + getDist( ).get(i).getNume( ));
                }
                while (categ1 != -1) {

                    System.out.println("Categorii");

                    for (int i = 0; i < getCateg( ).size( ); i++) {

                        int j = i + 1;

                        System.out.println(j + "." + getCateg( ).get(i).getNume( ));
                    }

                    System.out.println("0.Meniul Anterior");
                    System.out.print("Alege una din categorii: ");
                    categ1 = scanner.nextInt( );
                    System.out.println( );
                    categ1--;
                    if (categ1 != -1) {

                        System.out.println("Ai ales categoria " + getCateg().get(categ1).getNume());
                        System.out.println("Cate " + getCateg().get(categ1).getNume() + " iti doresti sa comanzi de la furnizorul " + getDist( ).get(0).getNume( ) + "?" );
                        int numar_produse = scanner.nextInt( );
                        getCateg().get(categ1).setNr_produse(getCateg().get(categ1).getNr_produse() + numar_produse);
                        System.out.println( "In categoria " + getCateg().get(categ1).getNume() + " vor fi " + getCateg().get(categ1).getNr_produse() + " produse");

                    }
                }
            }
            if (categ == 2) {
                while (distribuitor != -1) {
                    System.out.println("Distribuitorii disponibili sunt: ");
                    for (int i = 0; i < getDist( ).size( ); i++) {
                        int j = i + 1;
                        System.out.println(j + "." + getDist( ).get(i).getNume( ));
                    }
                    System.out.println("0.Meniul Anterior");
                    System.out.print("Alege numarul distribuitorului de unde vrei sa comanzi: ");
                    distribuitor = scanner.nextInt( );
                    System.out.println( );
                    distribuitor--;
                    if (distribuitor != -1) {
                        int categ2 = 1;
                        System.out.println("Ai ales distribuitorul " + getDist().get(distribuitor).getNume());
                        while (categ2 != -1) {

                            System.out.println("Categorii");

                            for (int i = 0; i < getCateg( ).size( ); i++) {

                                int j = i + 1;

                                System.out.println(j + "." + getCateg( ).get(i).getNume( ));
                            }

                            System.out.println("0.Meniul Anterior");
                            System.out.print("Alege una din categorii: ");
                            categ2 = scanner.nextInt( );
                            System.out.println( );
                            categ2--;
                            if (categ2 != -1) {

                                System.out.println("Ai ales categoria " + getCateg().get(categ2).getNume());
                                System.out.println("Cate " + getCateg().get(categ2).getNume() + " iti doresti sa comanzi de la furnizorul " + getDist( ).get(distribuitor).getNume( ) + "?" );
                                int numar_produse = scanner.nextInt( );
                                getCateg().get(categ2).setNr_produse(getCateg().get(categ2).getNr_produse() + numar_produse);
                                System.out.println( "In categoria " + getCateg().get(categ2).getNume() + " vor fi " + getCateg().get(categ2).getNr_produse() + " produse");

                            }
                        }
                    }
                }
            }
        }
    }
    public void afisare_distribuitori() {
        Scanner scanner = new Scanner(System.in);
        int val = 1;
        while (val != 0) {
            System.out.println("Alege tipul distribuitorilor:");
            System.out.println("1.Interni");
            System.out.println("2.Externi");
            System.out.println("0.Meniul Anterior");
            val = scanner.nextInt( );
            if (val == 1) {
                for (int i = 0; i < getDist( ).size( ); i++) {
                    Class<? extends distribuitori> s = getDist( ).get(i).getClass( );

                    if (s.toString( ).contains("class Gestiune.distribuitor_intern")) {
                        int j = i + 1;
                        System.out.println(j + "." + getDist( ).get(i).toString( ));
                    }
                }
            }
            if (val == 2) {
                for (int i = 0; i < getDist( ).size( ); i++) {
                    Class<? extends distribuitori> s = getDist( ).get(i).getClass( );

                    if (s.toString( ).contains("class Gestiune.distribuitor_extern")) {
                        int j = i + 1;
                        System.out.println(j + "." + getDist( ).get(i).toString( ));
                    }
                }
            }
            System.out.println();
        }
    }
    public distribuitori adaugare_distribuitori()
    {   distribuitori distribuitori_adaugare = null;
        Scanner scanner = new Scanner(System.in);
        int val = 1;
            System.out.println("Alege tipul distribuitorilor:");
            System.out.println("1.Interni");
            System.out.println("2.Externi");
            System.out.println("0.Meniul Anterior");
            val = scanner.nextInt( );
            if (val ==1){
                System.out.println("Introduceti Numele Distribuitorului, Telefonul, Adresa , Timpul de livrare in ore, Livrarea este sau nu facuta la sediu?");

                String nume = scanner.next();
                String tel = scanner.next();
                //int categ = scanner.nextInt();
                String adresa = scanner.next();
                int timp_livrare = scanner.nextInt();
                String livrare = scanner.next();

                distribuitori_adaugare = new distribuitor_intern(nume,tel,adresa,timp_livrare, livrare);
            }
            if (val ==2){
                System.out.println("Introduceti Numele Distribuitorului, Telefonul, Adresa , Timpul de livrare in ore, tara, cel mai apropait depozit");

                String nume = scanner.next();
                String tel = scanner.next();
                //int categ = scanner.nextInt();
                String adresa = scanner.next();
                int timp_livrare = scanner.nextInt();
                String tara = scanner.next();
                String depozit_apropiat = scanner.next();

                distribuitori_adaugare = new distribuitor_extern(nume,tel,adresa,timp_livrare, tara, depozit_apropiat);
            }
            return distribuitori_adaugare;
    }
    public void afisare_produse()
    {
        System.out.println("Afisare toate produsele din oferta");
        for(int i = 0; i < getProdus().size(); i++)
        {
            if(getProdus().get(i) != null)
            System.out.println(getProdus().get(i).getNume());
        }
    }
    public void afisare_categorii()
    {
        System.out.println("Afisare toate categoriile din oferta");
        for(int i = 0; i < getCateg().size(); i++)
        {
            if(getCateg().get(i) != null)
            System.out.println(getCateg().get(i).getNume());
        }
    }
    public void afisare_toti_distribuitorii()
    {
        System.out.println("Afisare toti distribuitorii");
        for(int i = 0; i < getDist().size(); i++)
        {
            if(getDist().get(i) != null)
            System.out.println(getDist().get(i).getNume());
        }
    }

}
