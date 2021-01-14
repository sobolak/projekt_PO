package deliveryproject.demo.Podstawa;

public class Wymiary {
    private double dlugosc;
    private double wysokosc;
    private double szerokosc;

    public Wymiary(double dlugosc,double wysokosc,double szerokosc) {
        this.dlugosc = dlugosc;
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public void setWysokosc(double wysokosc) {
        this.wysokosc = wysokosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }
}
