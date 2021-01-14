package deliveryproject.demo.Interfejsy;

import deliveryproject.demo.Podstawa.Paczka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ListaPaczek {

    public ArrayList<Paczka> listaPaczek = new ArrayList<>();
    Map<String, String> wojewodztwa = new HashMap<String, String>();

}
