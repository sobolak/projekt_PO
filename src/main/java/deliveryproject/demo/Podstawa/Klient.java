package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class Klient extends Uzytkownik implements dane {
    //obiekty
    private String firma;
    private String idKlienta;
    private int iloscPaczekNadanych;
    private int iloscPaczekOczekujacych;
    private String adres;

    //konstruktor
    public Klient(String imie, String nazwisko,String idKlienta ,String haslo, String firma , String adres){
        super(imie,nazwisko,haslo);
        this.idKlienta = idKlienta;
        this.firma = firma;
        this.adres = adres;
    }

    //metody
    public void bilansPaczek(){
        for (int i = 0 ; i < listaPaczek.size() ; i ++){
            if (listaPaczek.get(i).getIdNadawcy() != null && listaPaczek.get(i).getIdNadawcy().equals(this.idKlienta)){
                iloscPaczekNadanych++;
            }
            if (listaPaczek.get(i).getIdOdbiorcy() != null && listaPaczek.get(i).getIdOdbiorcy().equals(this.idKlienta)){
                iloscPaczekOczekujacych++;
            }
        }
    }
    //GETTERY I SETTERY
    public String getFirma() {
        return firma;
    }
    public void setFirma(String firma) {
        this.firma = firma;
    }
    public String getIdKlienta() {
        return idKlienta;
    }
    public void setIdKlienta(String idKlienta) {
        this.idKlienta = idKlienta;
    }
    public int getIloscPaczekNadanych() {
        return iloscPaczekNadanych;
    }
    public void setIloscPaczekNadanych(int iloscPaczekNadanych) {
        this.iloscPaczekNadanych = iloscPaczekNadanych;
    }
    public int getIloscPaczekOczekujacych() {
        return iloscPaczekOczekujacych;
    }
    public void setIloscPaczekOczekujacych(int iloscPaczekOczekujacych) {
        this.iloscPaczekOczekujacych = iloscPaczekOczekujacych;
    }
    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
}
