package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class PracownikBiura extends Uzytkownik implements dane {

    //obiekty
    private String idBiuro;
    private int pensja;
    private int stazFirmowy;

    //konstruktor
    public PracownikBiura(String imie , String nazwisko , String idBiuro,String haslo){
        super(imie,nazwisko,haslo);
        this.stazFirmowy = 0;
        this.idBiuro = idBiuro;
        obliczPensje();
    }

    //metody
    public void obliczPensje(){
        this.pensja=2700 + stazFirmowy*120;
    }
    public void wyslijPaczkeMiedzyOddzialami(Paczka paczka){
        if (paczka.getAktualnyStan().equals("ODEBRANA")) {
            paczka.setAktualnyStan("WYSLANA");
        }
    }
    public void zrobZwrot(Paczka x){
        x.setAktualnyStan("ZWROT");
    }


    //gettery i settery
    public String getIdBiuro() {
        return idBiuro;
    }
    public void setIdBiuro(String idBiuro) {
        this.idBiuro = idBiuro;
    }
    public float getPensja() {
        return pensja;
    }
    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
    public int getStazFirmowy() {
        return stazFirmowy;
    }
    public void setStazFirmowy(int stazFirmowy) {
        this.stazFirmowy = stazFirmowy;
    }
}
