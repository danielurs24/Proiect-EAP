package Gestiune;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Service {

//    ArrayList<categorii> categorie = new ArrayList<>();
//
//   categorie.add(new Electrocasnice("Aparate Frigorifice", 40, "Frigider"));
//   categorii categ_masini_de_spalat = new Electrocasnice("Masini de spalat", 25, "Spalat Vase");
//   categorii categ_laptop_gaming = new IT("Laptop-uri", 30,"Gaming");
//   categorii categ_telefon = new IT("Telefoane", 25, "Smartphone");

    private static Connection con;

    public static Connection sqlConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java","root","123456");

           // con.close();
        }catch(Exception e){ System.out.println(e);}
        return con;
    }

    public static void closeSqlConnection() throws SQLException {
        con.close();
        System.out.println("Conexiune Inchisa!");
    }


    private static Service instance = null;

    public static Service Singleton()
    {
        if (instance == null)
        {
            instance = new Service();
        }
        return instance;
    }


    ArrayList<produse> produs = new ArrayList<>();
    ArrayList<categorii> categ = new ArrayList<>();
    ArrayList<distribuitori> dist = new ArrayList<>();
    ArrayList<procesor> procesor = new ArrayList<>();

    List<String[]> data = new ArrayList<String[]>();


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

    public ArrayList<Gestiune.procesor> getProcesor() {
        return procesor;
    }

    public void setProcesor(ArrayList<Gestiune.procesor> procesor) {
        this.procesor = procesor;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    public categorii getCategByName(String name) {
        for(categorii c : categ) {
            if(c.getNume().equals(name))
                return c;
        }
        return null;
    }

    public procesor getProcesorByName(String name) {
        for(procesor p : procesor){
            if(p.getModel().equals(name))
                return p;
        }
        return null;

    }




    public Reader citesteCsv(String fisier) throws URISyntaxException, IOException {
        return Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(fisier).toURI()));
    }

    public void adaugareDateCSV(){
        File file = new File("audit.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeAll(getData());
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<distribuitori> citestedistribuitori() {
        ArrayList<distribuitori> distribuitori = new ArrayList<>();

        try {

            Reader reader = citesteCsv("distribuitori.csv");
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> list = csvReader.readAll();
            for(String[] line : list) {

                if(line.length < 1) continue;
                String categorie = line[0].trim();
                if(categorie.equals("intern")) {
                    if(line.length < 6) continue;
                    distribuitori.add(new distribuitor_intern(
                            line[1],
                            line[2],
                            line[3],
                            Integer.parseInt(line[4]),
                            line[5]
                    ));
                } else if(categorie.equals("extern")) {
                    if(line.length < 7) continue;
                    distribuitori.add(new distribuitor_extern(
                            line[1],
                            line[2],
                            line[3],
                            Integer.parseInt(line[4]),
                            line[5],
                            line[6]
                    ));
                }
            }
            reader.close();
            csvReader.close();

        } catch(Exception ignored) {}

        return distribuitori;
    }

    public ArrayList<procesor> citesteProcesoare() {
        ArrayList<procesor> procesor = new ArrayList<>();

        try {

            Reader reader = citesteCsv("procesoare.csv");
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> list = csvReader.readAll();
            for(String[] line : list) {

                if(line.length < 1) continue;
                procesor.add(new procesor(
                        line[1],
                        line[2],
                        line[3]
                ));
            }
            reader.close();
            csvReader.close();

        } catch(Exception ignored) {}

        return procesor;
    }

    public ArrayList<categorii> citesteCategorii() {
        ArrayList<categorii> categorii = new ArrayList<>();

        try {

            Reader reader = citesteCsv("categorii.csv");
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> list = csvReader.readAll();
            for(String[] line : list) {

                if(line.length < 1) continue;
                String categorie = line[0].trim();
                if(categorie.equals("electrocasnice")) {
                    if(line.length < 4) continue;
                    categorii.add(new Electrocasnice(
                            line[1],
                            Integer.parseInt(line[2]),
                            line[3]
                    ));
                } else if(categorie.equals("it")) {
                    if(line.length < 4) continue;
                    categorii.add(new IT(
                            line[1],
                            Integer.parseInt(line[2]),
                            line[3]
                    ));
                }
            }
            reader.close();
            csvReader.close();

        } catch(Exception ignored) {}

        return categorii;
    }

    public ArrayList<produse> citesteProduse() throws SQLException {
        ArrayList<produse> produse = new ArrayList<>();
        Connection connection = sqlConnection();
        Statement stmt=con.createStatement();


        ResultSet rs=stmt.executeQuery("select * from telefon");
        while(rs.next()) {
//            System.out.println(rs.getString("nume")+"  "+rs.getDouble("pret")+"  "+rs.getString("categorie")
//                                            +"  "+rs.getInt("pixeli_camera")+"  "+rs.getInt("dimensiune_ecran"));

            produse.add(new Telefon(rs.getString("nume"), rs.getDouble("pret"), getCategByName(rs.getString("categorie")),
                    rs.getInt("pixeli_camera"), rs.getInt("dimensiune_ecran")));
        }

        rs = stmt.executeQuery("select * from frigider");
        while(rs.next()) {
//            System.out.println(rs.getString("nume")+"  "+rs.getDouble("pret")+"  "+rs.getString("categorie")
//                                            +"  "+rs.getInt("pixeli_camera")+"  "+rs.getInt("dimensiune_ecran"));

            produse.add(new Frigider(rs.getString("nume"),rs.getDouble("pret"),getCategByName(rs.getString("categorie")),
                                    rs.getInt("numar_usi"),rs.getInt("volum")));
        }

        rs = stmt.executeQuery("select * from laptop");
        while(rs.next()) {
//            System.out.println(rs.getString("nume")+"  "+rs.getDouble("pret")+"  "+rs.getString("categorie")
//                                            +"  "+rs.getInt("pixeli_camera")+"  "+rs.getInt("dimensiune_ecran"));

            produse.add(new Laptop(rs.getString("nume"),rs.getDouble("pret"),getCategByName(rs.getString("categorie")),
                    getProcesorByName(rs.getString("procesor")),rs.getInt("ram")));
        }

        rs = stmt.executeQuery("select * from masina_de_spalat");
        while(rs.next()) {
//            System.out.println(rs.getString("nume")+"  "+rs.getDouble("pret")+"  "+rs.getString("categorie")
//                                            +"  "+rs.getInt("pixeli_camera")+"  "+rs.getInt("dimensiune_ecran"));

            produse.add(new Masina_de_spalat(rs.getString("nume"),rs.getDouble("pret"),getCategByName(rs.getString("categorie")),
                    rs.getInt("capacitate"),rs.getInt("viteza_de_uscare")));
        }

        return produse;
    }

//    public ArrayList<produse> citesteProduse() {
//        ArrayList<produse> produse = new ArrayList<>();
//
//        try {
//
//            Reader reader = citesteCsv("produse.csv");
//            CSVReader csvReader = new CSVReader(reader);
//            List<String[]> list = csvReader.readAll();
//            for(String[] line : list) {
//
//                if(line.length < 1) continue;
//                String categorie = line[0].trim();
//                if(categorie.equals("frigider")) {
//                    if(line.length < 6) continue;
//                    produse.add(new Frigider(
//                            line[1],
//                            Double.parseDouble(line[2]),
//                            getCategByName(line[3]),
//                            Integer.parseInt(line[4]),
//                            Integer.parseInt(line[5])
//                    ));
//                } else if(categorie.equals("masina_de_spalat")) {
//                    if(line.length < 6) continue;
//                    produse.add(new Masina_de_spalat(
//                            line[1],
//                            Double.parseDouble(line[2]),
//                            getCategByName(line[3]),
//                            Integer.parseInt(line[4]),
//                            Integer.parseInt(line[5])
//                    ));
//                } else if(categorie.equals("laptop")) {
//                    if(line.length < 6) continue;
//                    produse.add(new Laptop(
//                            line[1],
//                            Double.parseDouble(line[2]),
//                            getCategByName(line[3]),
//                            getProcesorByName(line[4]),
//                            Integer.parseInt(line[5])
//                    ));
//                } else if(categorie.equals("telefon")) {
//                    if(line.length < 6) continue;
//                    produse.add(new Telefon(
//                            line[1],
//                            Double.parseDouble(line[2]),
//                            getCategByName(line[3]),
//                            Integer.parseInt(line[4]),
//                            Double.parseDouble(line[5])
//                    ));
//                }
//            }
//            reader.close();
//            csvReader.close();
//
//        } catch(Exception ignored) {}
//
//        return produse;
//    }

    public produse adaugare_produs() throws SQLException {
        System.out.println("Categorii");
        Connection connection = sqlConnection();
        Statement stmt=con.createStatement();
        PreparedStatement rs;

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

            int numar_usi_frigider = scanner.nextInt();
            int volum_frigider = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Frigider(nume_frigider,pret_frigider,cat,numar_usi_frigider,volum_frigider);
            rs = connection.prepareStatement("INSERT INTO `frigider`(`nume`, `categorie`, `numar_usi`, `volum`, `pret`) VALUES (?,?,?,?,?)");
            rs.setString(1,nume_frigider);
            rs.setString(2,getCateg( ).get(categ).toString());
            rs.setInt(3,numar_usi_frigider);
            rs.setInt(4,volum_frigider);
            rs.setDouble(5,pret_frigider);
            rs.execute();
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
            rs = connection.prepareStatement("INSERT INTO `masina_de_spalat`(`nume`, `categorie`, `capacitate`, `viteza_de_uscare`, `pret`) VALUES (?,?,?,?,?)");
            rs.setString(1,nume);
            rs.setString(2,getCateg( ).get(categ).toString());
            rs.setInt(3,capacitate);
            rs.setInt(4,viteza_uscare);
            rs.setDouble(5,pret);
            rs.execute();
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
            rs = connection.prepareStatement("INSERT INTO `telefon`(`nume`, `pret`, `categorie`, `pixeli_camera`, `dimensiune_ecran`) VALUES (?,?,?,?,?)");
            rs.setString(1,nume);
            rs.setString(3,getCateg( ).get(categ).toString());
            rs.setInt(4,pixeli);
            rs.setInt(5,dimensiune);
            rs.setDouble(2,pret);
            rs.execute();
            //return telefon;
        }
        if (categ == 2)
        {   System.out.println("Introduceti Numele Laptopului, Pret, Procesor, Ram");

            String nume = scanner.next();
            int pret = scanner.nextInt();
            //int categ = scanner.nextInt();
            String procesor_name = scanner.next();
            procesor procesor = getProcesorByName(procesor_name);
            int ram = scanner.nextInt();
            categorii cat = getCateg( ).get(categ);

            produs_adaugare = new Laptop(nume,pret,cat,procesor,ram);
            rs = connection.prepareStatement("INSERT INTO `laptop`(`nume`, `pret`, `categorie`, `procesor`, `ram`) VALUES (?,?,?,?,?)");
            rs.setString(1,nume);
            rs.setString(3,getCateg( ).get(categ).toString());
            rs.setString(4,procesor_name);
            rs.setInt(5,ram);
            rs.setDouble(2,pret);
            rs.execute();
            //return frigider;
        }

        return produs_adaugare;
    }
    public void cautare_produs() throws SQLException {
        Connection connection = sqlConnection();
        Statement stmt=con.createStatement();
        PreparedStatement rs;
        int val = 1;
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
                            if (getProdus( ).get(i).getNume( ).contains(nume)){
                                System.out.println(getProdus( ).get(i).getNume( ) + " " + getProdus( ).get(i).getPret( ));
                                System.out.println("1.Editare Produs");
                                System.out.println("2.Stergere Produs");
                                int edit;
                                edit = scanner.nextInt( );
                                if(edit ==  1)
                                {
                                    if (getProdus( ).get(i).getCategorie().toString().contains("Aparate Frigorifice"))
                                    {   System.out.println("");
                                        System.out.println("Editare Frigider: Nume, Numar Usi, Volum, Pret");
                                        String nume_frigider = scanner.next();
                                        int numar_usi = scanner.nextInt();
                                        int volum = scanner.nextInt();
                                        int pret = scanner.nextInt();



                                        rs = connection.prepareStatement("UPDATE `frigider` SET `nume`=?, `numar_usi`=?, `volum`=?, `pret`=? WHERE `nume`=? ");
                                        rs.setString(1,nume_frigider);
                                        rs.setInt(2,numar_usi);
                                        rs.setInt(3,volum);
                                        rs.setInt(4,pret);
                                        rs.setString(5,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost modificat cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Masini de spalat"))
                                    {   System.out.println("");
                                        System.out.println("Editare Masina de spalat: Nume, Capacitate, Viteza de Uscare, Pret");
                                        String nume_masina = scanner.next();
                                        int capacitate = scanner.nextInt();
                                        int viteza_de_uscare = scanner.nextInt();
                                        int pret = scanner.nextInt();

                                        rs = connection.prepareStatement("UPDATE `masina_de_spalat` SET `nume`=?, `capacitate`=?, `viteza_de_uscare`=?, `pret`=? WHERE `nume`=? ");
                                        rs.setString(1,nume_masina);
                                        rs.setInt(2,capacitate);
                                        rs.setInt(3,viteza_de_uscare);
                                        rs.setInt(4,pret);
                                        rs.setString(5,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost modificat cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Laptop-uri"))
                                    {   System.out.println("");
                                        System.out.println("Editare Laptop: Nume, Procesor, Ram, Pret");
                                        String nume_laptop = scanner.next();
                                        String procesor = scanner.next();
                                        int ram = scanner.nextInt();
                                        int pret = scanner.nextInt();

                                        rs = connection.prepareStatement("UPDATE `laptop` SET `nume`=?, `procesor`=?, `ram`=?, `pret`=? WHERE `nume`=? ");
                                        rs.setString(1,nume_laptop);
                                        rs.setString(2,procesor);
                                        rs.setInt(3,ram);
                                        rs.setInt(4,pret);
                                        rs.setString(5,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost modificat cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Telefoane"))
                                    {   System.out.println("");
                                        System.out.println("Editare Telefon: Nume, Pixeli Camera, Dimensiune Ecran, Pret");
                                        String nume_telefon = scanner.next();
                                        int pixeli = scanner.nextInt();
                                        int ecran = scanner.nextInt();
                                        int pret = scanner.nextInt();

                                        rs = connection.prepareStatement("UPDATE `telefon` SET `nume`=?, `pixeli_camera`=?, `dimensiune_ecran`=?, `pret`=? WHERE `nume`=? ");
                                        rs.setString(1,nume_telefon);
                                        rs.setInt(2,pixeli);
                                        rs.setInt(3,ecran);
                                        rs.setInt(4,pret);
                                        rs.setString(5,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost modificat cu succes!");
                                    }
                                }
                                else if(edit ==  2)
                                {
                                    if (getProdus( ).get(i).getCategorie().toString().contains("Aparate Frigorifice"))
                                    {   System.out.println("");
                                        rs = connection.prepareStatement("DELETE FROM `frigider` WHERE `nume`=? ");
                                        rs.setString(1,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost sters cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Masini de spalat"))
                                    {   System.out.println("");
                                        rs = connection.prepareStatement("DELETE FROM `masina_de_spalat` WHERE `nume`=? ");
                                        rs.setString(1,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost sters cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Laptop-uri"))
                                    {   System.out.println("");
                                        rs = connection.prepareStatement("DELETE FROM `laptop` WHERE `nume`=? ");
                                        rs.setString(1,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost sters cu succes!");
                                    }
                                    else if (getProdus( ).get(i).getCategorie().toString().contains("Telefoane"))
                                    {   System.out.println("");
                                        rs = connection.prepareStatement("DELETE FROM `telefon` WHERE `nume`=? ");
                                        rs.setString(1,getProdus().get(i).getNume());
                                        int rowsUpdated = rs.executeUpdate();
                                        if(rowsUpdated > 0)
                                            System.out.println("Produsul a fost sters cu succes!");
                                    }
                                }
                            }


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
