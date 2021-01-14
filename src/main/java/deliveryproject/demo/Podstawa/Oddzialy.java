package deliveryproject.demo.Podstawa;

import deliveryproject.demo.Interfejsy.dane;

public class Oddzialy implements dane {
    private String nadawczy;
    private String odbiorczy;


    public Oddzialy(String nadawczyWoj, String odbiorczyWoj){
        this.nadawczy = wybor(nadawczyWoj);
        this.odbiorczy = wybor(odbiorczyWoj);
    }

    public String getNadawczy() {
        return nadawczy;
    }

    public void setNadawczy(String nadawczy) {
        this.nadawczy =wybor(nadawczy);
    }

    public String getOdbiorczy() {
        return odbiorczy;
    }

    public void setOdbiorczy(String odbiorczy) {
        this.odbiorczy = wybor(odbiorczy);
    }

    public  void setNadawczyZwrot(String x){
        this.nadawczy = x;
    }

    public  void  setOdbiorczyZwrot(String x){
        this.odbiorczy = x;
    }

    public String wybor(String wybraneWojewodztwo){
        return wojewodztwa.get(wybraneWojewodztwo);
    }
}
