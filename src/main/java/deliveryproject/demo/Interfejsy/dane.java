package deliveryproject.demo.Interfejsy;

import deliveryproject.demo.Podstawa.*;

import java.util.*;

public interface dane {


    Collection<String> dymy = new Collection<String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(String s) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    };
    List<Integer> aktualnyIteratorKurier = new ArrayList<>();
    List<Integer> aktualnyIteratorKlient = new ArrayList<>();
    List<Integer> aktualnyIteratorBiuro = new ArrayList<>();
    List<Integer> aktualnyIteratorKierownik = new ArrayList<>();
    ArrayList<Paczka> listaPaczek = new ArrayList<>();
    ArrayList<Kurier> listaKurierow = new ArrayList<>();
    ArrayList<Klient> listaKlientow = new ArrayList<>();
    ArrayList<Kierownik> listaKierownikow = new ArrayList<>();
    ArrayList<PracownikBiura> listaPracownikowBiura = new ArrayList<>();
    Map<String, String> wojewodztwa = new HashMap<String, String>();


    default int pobierzAktualnyIteratorKurier(){
        return aktualnyIteratorKurier.get(0);
    }
    default int pobierzAktualnyIteratorBiuro(){
        return aktualnyIteratorKierownik.get(0);
    }
    default int pobierzAktualnyIteratorKlient(){
        return aktualnyIteratorKlient.get(0);
    }
    default int pobierzAktualnyIteratorKierownik(){
        return aktualnyIteratorBiuro.get(0);
    }


    default void update(){

    }

    default void daneStartowe() {
        dymy.add("aaaa");
        dymy.add("bbbb");
        dymy.add("ccc");

        if (aktualnyIteratorKurier.isEmpty()){
            aktualnyIteratorKurier.add(100);
        }
        if (aktualnyIteratorKlient.isEmpty()){
            aktualnyIteratorKlient.add(100);
        }
        if (aktualnyIteratorBiuro.isEmpty()){
            aktualnyIteratorBiuro.add(100);
        }
        if (aktualnyIteratorKierownik.isEmpty()){
            aktualnyIteratorKierownik.add(100);
        }

        if (wojewodztwa.isEmpty()) {
            wojewodztwa.put("malopolskie", "KRK");
            wojewodztwa.put("zachodnio-pomorskie", "SZC");
            wojewodztwa.put("warminsko-mazurskie", "OLS");
            wojewodztwa.put("kujawsko-pomorskie", "BYD");
            wojewodztwa.put("lubuskie", "ZGA");
            wojewodztwa.put("wielkopolskie", "POZ");
            wojewodztwa.put("mazowieckie", "WAW");
            wojewodztwa.put("podlaskie", "BIA");
            wojewodztwa.put("lodzkie", "LDZ");
            wojewodztwa.put("dolnoslaskie", "WRO");
            wojewodztwa.put("opolskie", "OPL");
            wojewodztwa.put("slaskie", "KAT");
            wojewodztwa.put("podkarpackie", "RZE");
            wojewodztwa.put("lubelskie", "LUB");
            wojewodztwa.put("swietokrzyskie", "KIE");
            wojewodztwa.put("pomorskie", "GDA");
        }
        if (listaKurierow.isEmpty()){
            Kurier k0200 = new Kurier("Roman","Nowak","k0200","q","malopolskie","paczki");
            k0200.setStazFirmowy(40);
            Kurier k2710 = new Kurier("Maciej","Stabrwa","k2710","q","malopolskie","cokolwiek");
            k2710.setStazFirmowy(6);
            Kurier k1111 = new Kurier("Hanys","Zachaldy","k1111","q","slaskie","cokolwiek");
            k1111.setStazFirmowy(2);
            Kurier k0002 = new Kurier("Marek","Szybki","k0002","q","zachodnio-pomorskie","cokolwiek");
            k0002.setStazFirmowy(5);
            Kurier k0003 = new Kurier("Pawel","Kozak","k0003","q","warminsko-mazurskie","cokolwiek");
            k0003.setStazFirmowy(7);
            Kurier k0004 = new Kurier("Monika","Fifarafa","k0004","q","kujawsko-pomorskie","cokolwiek");
            k0004.setStazFirmowy(20);
            Kurier k0005 = new Kurier("Adam","Burlifa","k0005","q","lubuskie","paczki");
            k0005.setStazFirmowy(1);
            Kurier k0006 = new Kurier("Andrzej","Mily","k0006","q","wielkopolskie","cokolwiek");
            k0006.setStazFirmowy(0);
            Kurier k0007 = new Kurier("Bryam","Kobe","k0007","q","mazowieckie","paczki");
            k0007.setStazFirmowy(16);
            Kurier k0008 = new Kurier("Mateusz","Gruby","k0008","q","podlaskie","paczki");
            k0008.setStazFirmowy(2);
            Kurier k0009 = new Kurier("Michal","Micha;","k0008","q","lodzkie","cokolwiek");
            k0009.setStazFirmowy(2);
            Kurier k0010 = new Kurier("Kamil","Smierdz","k0010","q","dolnoslaskie","paczki");
            k0010.setStazFirmowy(7);
            Kurier k0011 = new Kurier("Lukasz","Awizo","k0011","q","opolskie","cokolwiek");
            k0011.setStazFirmowy(9);
            Kurier k0012 = new Kurier("Maciej","Predki","k0012","q","podkarpackie","paczki");
            k0012.setStazFirmowy(25);
            Kurier k0013 = new Kurier("Dawid","Długi","k0013","q","lubelskie","cokolwiek");
            k0013.setStazFirmowy(10);
            Kurier k0014 = new Kurier("Kacper","Usa","k0014","q","swietokrzyskie","cokolwiek");
            k0014.setStazFirmowy(11);
            Kurier k0015 = new Kurier("Patryk","Nowak","k0015","q","pomorskie","cokolwiek");
            k0015.setStazFirmowy(4);
            Kurier k0016 = new Kurier("Jakub","Kowalski","k0016","q","malopolskie","palety");
            k0016.setStazFirmowy(12);

            Kurier a = new Kurier("Jan","Dobry","k0018","q","malopolskie","palety");
            a.setStazFirmowy(10);
            Kurier b = new Kurier("Tomek","Wasacz","k0017","q","mazowieckie","paczki");
            b.setStazFirmowy(7);
            Kurier c = new Kurier("Marcin","Wozniak","k0700","q","mazowieckie","paczki");
            c.setStazFirmowy(9);

            listaKurierow.add(k0200);
            listaKurierow.add(k2710);
            listaKurierow.add(a);
            listaKurierow.add(b);
            listaKurierow.add(c);
            listaKurierow.add(k0007);
            listaKurierow.add(k1111);
            listaKurierow.add(k0002);
            listaKurierow.add(k0003);
            listaKurierow.add(k0004);
            listaKurierow.add(k0005);
            listaKurierow.add(k0006);
            listaKurierow.add(k0008);
            listaKurierow.add(k0009);
            listaKurierow.add(k0010);
            listaKurierow.add(k0011);
            listaKurierow.add(k0012);
            listaKurierow.add(k0013);
            listaKurierow.add(k0014);
            listaKurierow.add(k0015);



        }
        if (listaKlientow.isEmpty()){
           Klient c1001 = new Klient("Kacper" ,"Zemla","c1001","1235","X-kom","Wadowice 32");
           Klient c1002 = new Klient("Kamil","Sobolak","c1002","1234","w_krk","ul. Lipska Krakow");
           Klient c1003 = new Klient("Pawel","Cwlo","c1003","q","Szybko_biegacz","NS");
           Klient a= new Klient("Maria","Pyk","c1004","4123","samsung","Suwalki");
           Klient b= new Klient("Ola","Masaus","321","c1005","klikamy","Krakow 12");
           Klient c= new Klient("Hubert","Kiel","513","c1006","brukbet","Szczecin 219");
           Klient d= new Klient("Olaf","Smialy","132","c1007","masarnia","Mragowo 412");
           Klient e= new Klient("Hugo","Koaltaj","531","c1008","ksiegarnia","Brzezawa 12");
           Klient f= new Klient("Patrycja","Wesola","421","c1009","empik","Lubin 321");
           Klient g= new Klient("Sebastian","Grozny","135","c1010","lewiatan","WWA ul. Kliny 33");



           listaKlientow.add(c1001);
           listaKlientow.add(c1002);
           listaKlientow.add(c1003);
           listaKlientow.add(a);
           listaKlientow.add(b);
           listaKlientow.add(c);
           listaKlientow.add(d);
           listaKlientow.add(e);
           listaKlientow.add(f);
           listaKlientow.add(g);
        }
        if (listaKierownikow.isEmpty()){
            Kierownik b0001 = new Kierownik("Damain","Sobolak","b0001","banan","Pracownik biura");
            b0001.setStazFirmowy(6);
            b0001.setPensja(72302);
            Kierownik b0002 = new Kierownik("Grzegorz","Stancki","b0002","excel","Kierownik");
            b0002.setStazFirmowy(2);
            b0002.setPensja(5000);
            Kierownik a = new Kierownik("Tomasz","Loska","b0003","saplsa","Brak");
            a.setStazFirmowy(5);
            a.setPensja(9122);
            Kierownik  b= new Kierownik("Daniel","Pawelski","b0004","asas","Brak");
            b.setStazFirmowy(2);
            b.setPensja(2193);
            Kierownik  c= new Kierownik("Edyta","Matusik","b0005","dasvas","magazynier");
            c.setStazFirmowy(12);
            c.setPensja(2100);
            Kierownik  d= new Kierownik("Gerard","Jajecznica","b0006","eq","Kierownik");
            d.setStazFirmowy(7);
            d.setPensja(2280);
            Kierownik  e= new Kierownik("Rafal","Herzog","b0007","214","Pracownik biura");
            e.setStazFirmowy(21);
            e.setPensja(9291);
            Kierownik  f= new Kierownik("Marek","Niedorajda","b0008","32sd","Brak");
            e.setStazFirmowy(1);
            e.setPensja(4500);

            //System.out.println(b0002.getPensja());
            listaKierownikow.add(b0001);
            listaKierownikow.add(b0002);
            listaKierownikow.add(a);
            listaKierownikow.add(b);
            listaKierownikow.add(c);
            listaKierownikow.add(d);
            listaKierownikow.add(e);
            listaKierownikow.add(f);

        }
        if (listaPracownikowBiura.isEmpty()) {
            PracownikBiura a = new PracownikBiura("Ewa","Czoprowska","b1000","misiek");
            a.setStazFirmowy(5);
            a.obliczPensje();
            PracownikBiura  b= new PracownikBiura("Mateusz","Proskura","b2000","piwo");
            b.setStazFirmowy(0);
            b.obliczPensje();
            PracownikBiura  c= new PracownikBiura("Kasia","Piwosz","b3000","dssa");
            c.setStazFirmowy(1);
            c.obliczPensje();
            PracownikBiura  d= new PracownikBiura("Natalia","Gwozdz","b4000","3r2");
            d.setStazFirmowy(2);
            d.obliczPensje();
            PracownikBiura  e= new PracownikBiura("Bozena","Napieracz","b5000","sdfds");
            e.setStazFirmowy(10);
            e.obliczPensje();
            PracownikBiura  f= new PracownikBiura("Milosz","Zbawicciej","b6000","rgrwe");
            f.setStazFirmowy(7);
            f.obliczPensje();
            PracownikBiura  g= new PracownikBiura("Halyna","Deyna","b7000","132f");
            g.setStazFirmowy(9);
            g.obliczPensje();
            //System.out.println(a.getPensja());
            listaPracownikowBiura.add(a);
            listaPracownikowBiura.add(b);
            listaPracownikowBiura.add(c);
            listaPracownikowBiura.add(d);
            listaPracownikowBiura.add(e);
            listaPracownikowBiura.add(f);
            listaPracownikowBiura.add(g);


        }
        if (listaPaczek.isEmpty()){
            Paczka a = new Paczka(50,200,200,200,"lipska","Sobolak","Zemla","Kamil","Kacper","standard","brak",192,"malopolskie","mazowieckie");
            listaKurierow.get(0).potwierdzOdbior(a);
            listaPracownikowBiura.get(0).wyslijPaczkeMiedzyOddzialami(a);
            listaKurierow.get(1).doDoreczenia(a);
            listaKurierow.get(1).awizo(a);
            Paczka b = new Paczka(700,114,14,14,"mysliwksa","Sobolak","Zemla","Kamil","Kacper","standard","ZPD",20,"malopolskie","wielkopolskie");
            Paczka c = new Paczka(12,1,11,22,"wodna","Sobolak","Zemla","Kamil","Kacper","standard","brak",100000,"opolskie","malopolskie");
            listaKurierow.get(10).potwierdzOdbior(c);
            listaPracownikowBiura.get(1).wyslijPaczkeMiedzyOddzialami(c);
            Paczka d = new Paczka(2,22,22,2,"rzebika","Sobolak","Cwlo","Kamil","Pawel","standard","dokumenty",0,"kujawsko-pomorskie","malopolskie");
            listaKurierow.get(9).potwierdzOdbior(d);
            Paczka e = new Paczka(7,42,53,63,"zencow","Sobolak","Smialy","Kamil","Olaf","standard","brak",1200,"slaskie","malopolskie");
            listaKurierow.get(7).potwierdzOdbior(d);
            listaPracownikowBiura.get(0).wyslijPaczkeMiedzyOddzialami(d);
            Paczka f = new Paczka(35,53,42,41,"ilowa","Sobolak","Wesola","Kamil","Patrycja","standard","ZPD",1000,"malopolskie","mazowieckie");
            Paczka g = new Paczka(42,64,12,64,"lanowa","Sobolak","Zemla","Kamil","Kacper","standard","brak",0,"lubuskie","malopolskie");
            listaKurierow.get(13).potwierdzOdbior(g);
            listaPracownikowBiura.get(0).wyslijPaczkeMiedzyOddzialami(g);
            Paczka h = new Paczka(42,200,200,200,"plac Mariacki","Cwlo","Zemla","Pawel","Kacper","dodatkowe","ZPD",0,"malopolskie","swietokrzyskie");
            Paczka j = new Paczka(42,342,210,200,"plac na Groblach","Sobolak","Cwel","Kamil","Pawel","dodatkowe","ZPD",0,"malopolskie","slaskie");
            Paczka k = new Paczka(42,200,520,10,"wesola","Cwlo","Zemla","Pawel","Kacper","dodatkowe","ZPD",0,"malopolskie","pomorskie");

            listaPaczek.add(a);
            listaPaczek.add(b);
            listaPaczek.add(c);
            listaPaczek.add(d);
            listaPaczek.add(e);
            listaPaczek.add(f);
            listaPaczek.add(g);
            listaPaczek.add(h);
            listaPaczek.add(j);
            listaPaczek.add(k);
        }
        }
}