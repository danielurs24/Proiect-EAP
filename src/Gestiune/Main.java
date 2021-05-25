package Gestiune;

import com.opencsv.CSVReader;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

// TO DO: Scoatere din oferta
public class Main {

    public static void main(String[] args) throws SQLException {



        Service Service1 = Service.Singleton();
        Service1.sqlConnection();

        /////////////////////////////////////////////////////////////////////////////////// - Introducere valori default
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<distribuitori> dist = new ArrayList<>( );
        ArrayList<categorii> categ = new ArrayList<>( );
        ArrayList<produse> produse = new ArrayList<>( );

        List<String[]> data = new ArrayList<String[]>();

        dist.addAll(Service1.citestedistribuitori());
        Collections.sort(dist);
        Service1.setDist(dist);


        categ.addAll(Service1.citesteCategorii());

        Service1.setCateg(categ);

        produse.addAll(Service1.citesteProduse());

        Service1.setProdus(produse);

        System.out.println(formatter.format(new Date()));


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
                data.add(new String[]{"Adaugare Produs",  formatter.format(new Date())});

            }
            if(opt == 2)
            {
                dist.add(Service1.adaugare_distribuitori());
                Collections.sort(dist);
                Service1.setDist(dist);
                data.add(new String[]{"Adaugare Distribuitori",  formatter.format(new Date())});

            }
            if(opt == 3)
            {
                Service1.afisare_produse();
                data.add(new String[]{"Afisare Produse",  formatter.format(new Date())});
            }
            if(opt == 4)
            {
                Service1.afisare_toti_distribuitorii();
                data.add(new String[]{"Afisare Distribuitori",  formatter.format(new Date())});
            }
            if(opt == 5)
            {
                Service1.afisare_categorii();
                data.add(new String[]{"Afisare Categorii",  formatter.format(new Date())});
            }
            if(opt == 6)
            {
                Service1.adaugare_stoc();
                data.add(new String[]{"Adaugare Stoc",  formatter.format(new Date())});
            }
            if(opt == 7)
            {
                Service1.afisare_distribuitori();
                data.add(new String[]{"Afisare Distribuitori",  formatter.format(new Date())});
            }
            if(opt == 8)
            {
                Service1.cautare_produs();
                data.add(new String[]{"Cautare Produs",  formatter.format(new Date())});
            }


        }
        Service1.setData(data);
        Service1.adaugareDateCSV();
        Service.closeSqlConnection();


    }

}
