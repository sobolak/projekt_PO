package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class Paczka implements dane {
    //obiekty
    private int numer;
    private double waga;
    public String typPaczki;
    private double cena;
    private String aktualnyStan;
    public String odbiorcaImie;
    private String nadawcaImie;
    public String odbiorcaNazwisko;
    private String nadawcaNazwisko;
    private String addresOdbiorcy;
    private String ubezpieczenie;
    private String dokumenty;
    private double pobranie;
    private boolean cybant;
    private String idNadawcy;
    private String idOdbiorcy;
    private String idKurieraDostarczajacego;
    private String idKurieraOdbierajacego;
    private String nadawczyOddzial;
    private String odbiorczyOddzial;

    public Paczka() {

    }


    static int licznik = 6231100;
    public Wymiary wymiary = new Wymiary(0,0,0);
    public Oddzialy oddzialy = new Oddzialy("buffor","buffor");



    //konstruktor
    public Paczka( double waga, double wys ,double sze , double dl ,String addresOdbiorcy,String odbiorcaNazwisko, String nadawcaNazwisko,String odbiorcaImie, String nadawcaImie, String ubezpieczenie, String dokumenty, double pobranie, String nadawczy , String odbiorczy) {
        this.waga = waga;
        wymiary.setDlugosc(dl);
        wymiary.setSzerokosc(sze);
        wymiary.setWysokosc(wys);

        this.addresOdbiorcy = addresOdbiorcy;
        this.odbiorcaImie = odbiorcaImie;
        this.nadawcaImie = nadawcaImie;
        this.nadawcaNazwisko = nadawcaNazwisko;
        this.odbiorcaNazwisko = odbiorcaNazwisko;

        licznik++;
        this.numer = licznik;
        this.ubezpieczenie = ubezpieczenie;
        this.dokumenty = dokumenty;
        this.pobranie = pobranie;

        this.oddzialy.setNadawczy(nadawczy);
        this.oddzialy.setOdbiorczy(odbiorczy);

        this.aktualnyStan = "NADANA";
        jakiTypPaczki();
        czyJestemCybantem();
        obliczCene();
        przyporzadkujNadawce();
        przyporzadkujOdbiorce();

        this.nadawczyOddzial = oddzialy.getNadawczy();
        this.odbiorczyOddzial = oddzialy.getOdbiorczy();
    }

    // gettery i settery
    public int getNumer() {
        return numer;
    }
    public double getWaga() {
        return waga;
    }
    public double getCena() {
        return cena;
    }
    public String getAktualnyStan() {
        return aktualnyStan;
    }
    public String getOdbiorczyOddzial() {
        return odbiorczyOddzial;
    }
    public void setOdbiorczyOddzial(String odbiorczyOddzial) {
        this.odbiorczyOddzial = odbiorczyOddzial;
    }
    public String getNadawczyOddzial() {
        return nadawczyOddzial;
    }
    public void setNadawczyOddzial(String nadawczyOddzial) {
        this.nadawczyOddzial = nadawczyOddzial;
    }
    public String getDokumenty() {
        return dokumenty;
    }
    public String getIdNadawcy() {
        return idNadawcy;
    }
    public void setIdNadawcy(String idNadawcy) {
        this.idNadawcy = idNadawcy;
    }
    public String getIdOdbiorcy() {
        return idOdbiorcy;
    }
    public void setIdOdbiorcy(String idOdbiorcy) {
        this.idOdbiorcy = idOdbiorcy;
    }
    public String getIdKurieraDostarczajacego() {
        return idKurieraDostarczajacego;
    }
    public void setIdKurieraDostarczajacego(String idKurieraDostarczajacego) {
        this.idKurieraDostarczajacego = idKurieraDostarczajacego;
    }
    public double getPobranie() {
        return pobranie;
    }
    public void setNumer(int numer) {
        this.numer = numer;
    }
    public void setAktualnyStan(String aktualnyStan) {
        this.aktualnyStan = aktualnyStan;
    }
    public String getTypPaczki() {
        return typPaczki;
    }
    public void setTypPaczki(String typPaczki) {
        this.typPaczki = typPaczki;
    }
    public String getOdbiorcaImie() {
        return odbiorcaImie;
    }
    public void setOdbiorcaImie(String odbiorcaImie) {
        this.odbiorcaImie = odbiorcaImie;
    }
    public String getNadawcaImie() {
        return nadawcaImie;
    }
    public void setNadawcaImie(String nadawcaImie) {
        this.nadawcaImie = nadawcaImie;
    }
    public String getOdbiorcaNazwisko() {
        return odbiorcaNazwisko;
    }
    public void setOdbiorcaNazwisko(String odbiorcaNazwisko) {
        this.odbiorcaNazwisko = odbiorcaNazwisko;
    }
    public String getNadawcaNazwisko() {
        return nadawcaNazwisko;
    }
    public void setNadawcaNazwisko(String nadawcaNazwisko) {
        this.nadawcaNazwisko = nadawcaNazwisko;
    }
    public String getIdKurieraOdbierajacego() {
        return idKurieraOdbierajacego;
    }
    public void setCybant(boolean cybant) {
        this.cybant = cybant;
    }
    public void setIdKurieraOdbierajacego(String idKurieraOdbierajacego) {
        this.idKurieraOdbierajacego = idKurieraOdbierajacego;
    }
    public String getUbezpieczenie() {
        return ubezpieczenie;
    }
    public void setUbezpieczenie(String ubezpieczenie) {
        this.ubezpieczenie = ubezpieczenie;
    }
    public void setPobranie(double pobranie) {
        this.pobranie = pobranie;
    }
    public void setOddzialy(Oddzialy oddzialy) {
        this.oddzialy = oddzialy;
    }
    public String getAddresOdbiorcy() {
        return addresOdbiorcy;
    }
    public void setAddresOdbiorcy(String addresOdbiorcy) {
        this.addresOdbiorcy = addresOdbiorcy;
    }
    public void setWaga(double waga) {
        this.waga = waga;
    }
    public double getSzerokosc(){
        return this.wymiary.getSzerokosc();
    }
    public double getWysokosc(){
        return this.wymiary.getWysokosc();
    }
    public double getDlugosc(){
        return this.wymiary.getWysokosc();
    }
    public void setSzerokosc(double x){
        this.wymiary.setSzerokosc(x);
    }
    public void setWysokosc(double x){
        this.wymiary.setWysokosc(x);
    }
    public void setDlugosc(double x){
        this.wymiary.setDlugosc(x);
    }


    //metody
    public String isCybant() {
        if (this.cybant){
            return "cybant";
        }else {
            return "normalna";
        }
    }
    public void jakiTypPaczki(){
        if (waga <= 70){
            typPaczki = "paczka";
        }else{
            typPaczki = "paleta";
        }
    }
    public void obliczCene(){
        if(typPaczki == "paleta"){
            if(70 < waga && waga <= 100){
                cena =50;
            }else if(100 < waga && waga <= 150){
                cena =150;
            }else if(150 < waga && waga <= 250){
                cena = 175;
            }else if(150 < waga && waga <= 500){
                cena = 250;
            }else if(500 < waga && waga <= 1500){
                cena = 355;
            }else{
                cena = 700;
            }
        }else {
            if(0 < waga && waga <= 2){
                cena = 6;
            }else if (2 < waga && waga <= 10){
                cena = 8;
            }else if (10 < waga && waga <= 25){
                cena = 12;
            }else if (25 < waga && waga <= 35){
                cena = 15;
            }else if (35 < waga && waga <= 50){
                cena = 19;
            }else{
                cena = 27;
            }
        }
        if (pobranie != 0){
            cena = cena + 20;
        }
        if (ubezpieczenie != ""){
            cena = 1.2 * cena;
        }
        if (cybant){
            cena = 2*cena;
        }
        this.cena *= 100;
        this.cena = Math.round(this.cena);
        this.cena /= 100;
    }
    public void czyJestemCybantem(){
        if (typPaczki =="paczka") {
            if (wymiary.getDlugosc() > 180 || wymiary.getSzerokosc() > 180 || wymiary.getWysokosc() > 180 || wymiary.getWysokosc() + wymiary.getSzerokosc() > 150 || wymiary.getWysokosc() + wymiary.getDlugosc() > 150 || wymiary.getDlugosc() + wymiary.getSzerokosc() > 150) {
                this.cybant = true;
            } else {
                this.cybant = false;
            }
        }else
            if(wymiary.getDlugosc() > 100 || wymiary.getSzerokosc() > 100 || wymiary.getWysokosc() > 200) {
                cybant =true;
            }else{
                cybant = false;
            }
    }
    public void przyporzadkujNadawce() {
        for (int i = 0; i < listaKlientow.size(); i++) {
            if (listaKlientow.get(i).getImie().equals(this.nadawcaImie) && listaKlientow.get(i).getNazwisko().equals(this.nadawcaNazwisko)) {
                this.idNadawcy = listaKlientow.get(i).getIdKlienta();
            }
        }

    }
    public void przyporzadkujOdbiorce(){
        int buffor = 0;
        for (int i = 0 ; i < listaKlientow.size() ;i++){


            if(listaKlientow.get(i).getImie().equals(this.odbiorcaImie) && listaKlientow.get(i).getNazwisko().equals(this.odbiorcaNazwisko)) {
                this.idOdbiorcy = listaKlientow.get(i).getIdKlienta();
                //System.out.println("--------------");
                //System.out.println(this.odbiorcaNazwisko);
                //System.out.println(listaKlientow.get(i).getNazwisko());
                //System.out.println("--------------");
                buffor = buffor + 1;
            }
        }

        if (buffor > 0) {
            for (int i = 0; i < listaKlientow.size(); i++) {
                if (listaKlientow.get(i).getAdres().equals(this.addresOdbiorcy)) {
                    this.idOdbiorcy = listaKlientow.get(i).getIdKlienta();
                }
            }
        }
    }

}
