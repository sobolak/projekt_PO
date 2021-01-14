package deliveryproject.demo.Strony;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


/**
 * This example project shows how to use VerticalLayout and HorizontalLayout to build a typical responsive application disposition.
 */
@Route("main")
@StyleSheet("/styles/main.css")
public class MainView extends Div {

    public MainView() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setId("layout1");
        Icon avatar = VaadinIcon.USER.create();
        avatar.setColor("black");
        avatar.setId("Image");
        avatar.setSize("4em");
        TextField textField = new TextField("Kurier:");
        textField.setId("name");
        layout1.add(avatar,textField);
        add(layout1);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("layout");
        Button button1 = new Button("Paczki");
        Button button2 = new Button("Zadania");
        Button button3 = new Button("Dane");
        layout.add(button1,button2,button3);
        add(layout);
    }
}