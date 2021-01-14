package deliveryproject.demo.Podstawa;

public class Uzytkownik {

    public Uzytkownik(String imie, String nazwisko,String haslo){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.haslo =haslo;
    }

    private String imie;
    private String haslo;
    private String selected;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    private String nazwisko;
    public Paczka paczka;


    public Paczka getPaczka() {
        return paczka;
    }

    public void setPaczka(Paczka paczka) {
        this.paczka = paczka;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void wyloguj(){
        this.selected =null;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
