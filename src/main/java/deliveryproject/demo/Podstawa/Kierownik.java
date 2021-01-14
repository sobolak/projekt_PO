package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class Kierownik extends PracownikBiura implements dane {
    //obiekty
    private int pensja;
    private String poprzedniaStanowisko;

    public Kierownik(String imie, String nazwisko, String idBiuro,String haslo,String poprzednieStanowisko) {
        super(imie, nazwisko, idBiuro,haslo);
        this.poprzedniaStanowisko = poprzednieStanowisko;
        obliczPensje();
    }

    @Override
    public void obliczPensje() {
        this.pensja = 10000;
    }
    @Override
    public float getPensja() {
        return pensja;
    }
    @Override
    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
    public String getPoprzedniaStanowisko() {
        return poprzedniaStanowisko;
    }
    public void setPoprzedniaStanowisko(String poprzedniaStanowisko) {
        this.poprzedniaStanowisko = poprzedniaStanowisko;
    }
}