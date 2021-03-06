package deliveryproject.demo.Strony;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import deliveryproject.demo.Interfejsy.dane;

@Route(value ="kierowniklogin")
@PageTitle("Login")
@StyleSheet("/styles/login.css")
public class LoginKierownikView extends Div implements dane {
    int semafor = 0;

    public LoginKierownikView() {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        var login = new NativeButton("Login");
        login.setId("login_przycisk");
        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Błędne hasło lub nazwa użytkownika.");
        notification.add(Label,ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");
        login.addClickListener(clickEvent -> {
            for (int i = 0 ; i < listaKierownikow.size() ; i++) {
                if (password.getValue().equals(listaKierownikow.get(i).getHaslo()) && username.getValue().equals(listaKierownikow.get(i).getIdBiuro())) {
                    aktualnyIteratorKierownik.add(i);
                    semafor = 1;
                    UI.getCurrent().navigate("kierownik");
                    //System.out.println(pobierzAktualnyIterator());
                    break;
                }
            }
            if(semafor == 0){
                notification.open();
            }
        });

        com.vaadin.flow.component.html.Label title = new Label("Cybanteria Wita!");
        title.setId("title");
        add(
                title,
                username,
                password,
                login
        );

    }
}
