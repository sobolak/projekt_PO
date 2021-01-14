package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class Kurier extends Uzytkownik implements dane {
    //OBIEKTY
    private String idKuriera;
    private float pensja;
    private int stazFirmowy;
    private String oddzial;
    private String typDostarczanychPaczek;

    //KONSTRUKTOR
    public Kurier(String imie , String nazwisko ,String nrKuriera,String haslo,String oddzial,String typDostarczanychPaczek) {
        super(imie,nazwisko,haslo);
        this.idKuriera = nrKuriera;
        this.stazFirmowy = 0;
        this.oddzial = wojewodztwa.get(oddzial);
        this.typDostarczanychPaczek = typDostarczanychPaczek;
        obliczPensje();
    }


    //METODY
    public void obliczPensje(){
        this.pensja=3540 + 120*(float)stazFirmowy;
    }
    public void potwierdzDoreczenie(Paczka paczka){
        paczka.setAktualnyStan("DORENCZONA");
    }
    public void potwierdzOdbior(Paczka paczka){
        if (paczka.getAktualnyStan().equals("NADANA") && paczka.oddzialy.getOdbiorczy().equals(paczka.oddzialy.getNadawczy())){
            paczka.setIdKurieraOdbierajacego(this.idKuriera);
            paczka.setAktualnyStan("WYSLANA");
        }else if (paczka.getAktualnyStan().equals("NADANA")){
            paczka.setIdKurieraOdbierajacego(this.idKuriera);
            paczka.setAktualnyStan("ODEBRANA");
        }
    }
    public void awizo(Paczka paczka){
        if (paczka.getAktualnyStan().equals("WYDANA")) {
            paczka.setAktualnyStan("AWIZO");
        }
    }
    public void odmowa(Paczka paczka){
        if (paczka.getAktualnyStan().equals("WYDANA")) {
            paczka.setAktualnyStan("ODMOWA");
        }
    }
    public void doDoreczenia(Paczka paczka){
        if (paczka.getAktualnyStan().equals("WYSLANA")) {
            paczka.setIdKurieraDostarczajacego(this.idKuriera);
            paczka.setAktualnyStan("WYDANA");
        }
    }



    //gettery i settery
    public String getIdKuriera() {
        return idKuriera;
    }
    public void setIdKuriera(String idKuriera) {
        this.idKuriera = idKuriera;
    }
    public float getPensja() {
        return pensja;
    }
    public void setPensja(float pensja) {
        this.pensja = pensja;
    }
    public int getStazFirmowy() {
        return stazFirmowy;
    }
    public void setStazFirmowy(int stazFirmowy) {
        this.stazFirmowy = stazFirmowy;
    }
    public String getOddzial() {
        return oddzial;
    }
    public void setOddzial(String oddzial) {
        this.oddzial = oddzial;
    }
    public String getTypDostarczanychPaczek() {
        return typDostarczanychPaczek;
    }
    public void setTypDostarczanychPaczek(String typDostarczanychPaczek) {
        this.typDostarczanychPaczek = typDostarczanychPaczek;
    }
}
