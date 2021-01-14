package deliveryproject.demo.Strony;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import deliveryproject.demo.Interfejsy.dane;

@Route("")
@StyleSheet("/styles/start.css")
public class StartView extends VerticalLayout implements dane {

    public StartView(){
        setId("startView");
        Label tytuł = new Label("Zaloguj się jako: ");
        tytuł.setId("tytuł");
        NativeButton buttonKurier = new NativeButton("Kurier");
        NativeButton buttonKlient = new NativeButton("Klient");
        NativeButton buttonPracownikBiura = new NativeButton("Biuro");
        NativeButton buttonKierownik = new NativeButton("Kierownik");

        buttonKlient.setId("przycisk");
        buttonKurier.setId("przycisk");
        buttonPracownikBiura.setId("przycisk");
        buttonKierownik.setId("przycisk");
        Image image = new Image("https://i.ibb.co/fvYQ3YN/Logo-Dobre.png","d");
        image.setId("logo");
        buttonKlient.addClickListener(clickEvent -> {
            UI.getCurrent().navigate("klientlogin");
        });
        buttonKurier.addClickListener(clickEvent -> {
            UI.getCurrent().navigate("kurierlogin");
        });
        buttonPracownikBiura.addClickListener(clickEvent -> {
            UI.getCurrent().navigate("biurologin");
        });
        buttonKierownik.addClickListener(clickEvent -> {
            UI.getCurrent().navigate("kierowniklogin");
        });
        add(image,tytuł,buttonKlient,buttonKierownik,buttonKurier,buttonPracownikBiura);

        daneStartowe(); //żeby jednokrotnie zdefiniowac nasze wartosci startowe
    }

}
