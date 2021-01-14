package deliveryproject.demo.Strony;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.lang.annotation.Native;

@Route(value ="login")
@PageTitle("Login")
@StyleSheet("/styles/login.css")
public class LoginView extends Div {

    public LoginView() {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        var login = new NativeButton("Login");
        login.setId("login_przycisk");
        username.setId("username");
        password.setId("password");

        login.addClickListener(clickEvent -> {
            login.getUI().ifPresent(ui -> ui.navigate("main"));
        });

        add(
                new H1("Welcome"),
                username,
                password,
                login
        );

    }
}
