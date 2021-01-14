package deliveryproject.demo.Strony;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import deliveryproject.demo.Podstawa.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * This example project shows how to use VerticalLayout and HorizontalLayout to build a typical responsive application disposition.
 */
@Route("kierownik")
@PageTitle("kierownik")
@StyleSheet("/styles/main.css")
public class KierownikView extends Div implements deliveryproject.demo.Interfejsy.dane {

    FormLayout formularzDodajKuriera = new FormLayout();
    FormLayout formularzDodajKlienta = new FormLayout();
    FormLayout formularzDodajKierownika = new FormLayout();
    FormLayout formularzDodajPracownikaBiura = new FormLayout();
    FormLayout formularzIdentyfikator = new FormLayout();

    HorizontalLayout formularzDodajKurieraBD = new HorizontalLayout();
    HorizontalLayout formularzDodajKlientaBD = new HorizontalLayout();
    HorizontalLayout formularzDodajKierownikaBD = new HorizontalLayout();
    HorizontalLayout formularzDodajPracownikaBiuraBD = new HorizontalLayout();

    HorizontalLayout daneKierownika = new HorizontalLayout();
    HorizontalLayout mapaWszystkiePaczki = new HorizontalLayout();
    HorizontalLayout layout = new HorizontalLayout();

    Set<Paczka> buffor = new HashSet<>();

    VerticalLayout layout1 = new VerticalLayout();

    Button wszystkiePaczki = new Button("Wszystkie paczki");
    Button dane = new Button("Dane");
    Button wyloguj = new Button("wyloguj");
    MenuBar dodajWszystkie = new MenuBar();
    MenuBar bazyDanych = new MenuBar();


    public KierownikView() {

        start();
        formularzDodajKierownika.setId("formularz");
        formularzDodajKlienta.setId("formularz");
        formularzDodajKuriera.setId("formularz");
        formularzDodajPracownikaBiura.setId("formularz");
        wszystkiePaczki.setId("przycisk_klient");
        daneKierownika.setId("formularz");
        dane.setId("przycisk_klient");
        dodajWszystkie.setId("przycisk_dane");
        bazyDanych.setId("przycisk_dane");
        wyloguj.setId("przycisk_wyloguj");
        formularzIdentyfikator.setId("formularz");

        wszystkiePaczki.addClickListener(buttonClickEvent -> {
            wyswietlWszystkiePaczki();
            mapaWszystkiePaczki.setVisible(true);
            formularzDodajKuriera.setVisible(false);
            formularzDodajKlienta.setVisible(false);
            daneKierownika.setVisible(false);
            formularzIdentyfikator.setVisible(false);
        });
        dane.addClickListener(buttonClickEvent -> {
            identyfikator();
        });
        wyloguj.addClickListener(buttonClickEvent -> {
            System.out.println("Wylogowanie do ekranu glownego");
            UI.getCurrent().navigate("");
        });
    }



    private void dodajKuriera() {
        formularzDodajKuriera.removeAll();
        TextField imieKuriera = new TextField();
        TextField nazwiskoKuriera = new TextField();
        TextField idKuriera = new TextField();
        TextField hasloKuriera = new TextField();

        Select<String> oddzialKuriera = new Select<>();
        oddzialKuriera.setItems("malopolskie", "zachodnio-pomorskie", "warminsko-mazurskie", "kujawsko-pomorskie", "lubuskie", "wielkopolskie", "mazowieckie", "podlaskie", "lodzkie", "dolnoslaskie", "opolskie", "slaskie", "podkarpackie", "lubelskie", "swietokrzyskie", "pomorskie");

        Select<String> typDostarczaniaPaczek = new Select<>();
        typDostarczaniaPaczek.setItems("paczki", "palety", "cokolwiek");

        Button dodaj = new Button("DODAJ");
        dodaj.setId("dodajKuriera");
        dodaj.setId("przycisk_wyslij");

        formularzDodajKuriera.addFormItem(idKuriera, "Numer kuriera ");
        formularzDodajKuriera.addFormItem(hasloKuriera, "Haslo ");
        formularzDodajKuriera.addFormItem(imieKuriera, "Imie ");
        formularzDodajKuriera.addFormItem(nazwiskoKuriera, "Nazwisko ");
        formularzDodajKuriera.addFormItem(oddzialKuriera, "Oddzial kuriera ");
        formularzDodajKuriera.addFormItem(typDostarczaniaPaczek, "Typ paczek ");


        oddzialKuriera.setItems("malopolskie", "zachodnio-pomorskie", "warminsko-mazurskie", "kujawsko-pomorskie", "lubuskie", "wielkopolskie", "mazowieckie", "podlaskie", "lodzkie", "dolnoslaskie", "opolskie", "slaskie", "podkarpackie", "lubelskie", "swietokrzyskie", "pomorskie");
        oddzialKuriera.setValue("Województwo");
        Binder<Kurier> binder = new Binder<>(Kurier.class);
        binder.forField(imieKuriera)
                .asRequired("Musisz podać imie")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                )
                .bind(Kurier::getImie, Kurier::setImie);

        binder.forField(nazwiskoKuriera)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(Kurier::getNazwisko, Kurier::setNazwisko);

        binder.forField(idKuriera)
                .asRequired("Musisz podać id")
                .withValidator(new RegexpValidator("Proszę podać poprawnę id np. k0000", "k[0-9]{4}")
                )
                .bind(Kurier::getIdKuriera, Kurier::setIdKuriera);

        binder.forField(hasloKuriera)
                .asRequired("Musisz podać hasło")
                .withValidator(new RegexpValidator("Proszę podać poprawnę hasło", "[a-zA-Z0-9]+")
                )
                .bind(Kurier::getHaslo, Kurier::setHaslo);

        binder.forField(oddzialKuriera)
                .asRequired()
                .bind(Kurier::getOddzial, Kurier::setOddzial);
        binder.forField(typDostarczaniaPaczek)
                .asRequired()
                .bind(Kurier::getTypDostarczanychPaczek, Kurier::setTypDostarczanychPaczek);

        dodaj.setEnabled(false);
        binder.addStatusChangeListener(
                event -> dodaj.setEnabled(binder.isValid()));
        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Kurier o takim ID już istnieje.");
        notification.add(Label, ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");
        dodaj.addClickListener(buttonClickEvent -> {
            int semafor = 0;
            for (int j = 0; j < listaKurierow.size(); j++) {
                if (idKuriera.getValue().equals(listaKurierow.get(j).getIdKuriera())) {
                    semafor = 1;
                    System.out.println("Blad w dodaniu kuriera");
                    notification.open();
                }
            }
            if (semafor == 0) {
                Kurier buffor = new Kurier(imieKuriera.getValue(), nazwiskoKuriera.getValue(), idKuriera.getValue(), hasloKuriera.getValue(), oddzialKuriera.getValue(), typDostarczaniaPaczek.getValue());
                System.out.println("Dodado Kuriera "+ buffor.getIdKuriera()+"  przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

                listaKurierow.add(buffor);
                imieKuriera.setValue("");
                nazwiskoKuriera.setValue("");
                idKuriera.setValue("");
                hasloKuriera.setValue("");
                oddzialKuriera.setValue("");
                typDostarczaniaPaczek.setValue("");
            }
        });

        formularzDodajKuriera.add(dodaj);
        add(formularzDodajKuriera);



        formularzDodajKuriera.setVisible(true);
        formularzDodajKlienta.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void dodajKlienta() {
        formularzDodajKlienta.removeAll();
        TextField imieKlienta = new TextField();
        TextField nazwiskoKlienta = new TextField();
        TextField idKlienta = new TextField();
        TextField hasloKlienta = new TextField();
        TextField firmaKlienta = new TextField();
        TextField adresKlienta = new TextField();


        Button dodaj = new Button("DODAJ");
        dodaj.setId("przycisk_wyslij");

        formularzDodajKlienta.addFormItem(idKlienta, "Id klient ");
        formularzDodajKlienta.addFormItem(hasloKlienta, "Haslo ");
        formularzDodajKlienta.addFormItem(imieKlienta, "Imie ");
        formularzDodajKlienta.addFormItem(nazwiskoKlienta, "Nazwisko ");
        formularzDodajKlienta.addFormItem(firmaKlienta, "Firma ");
        formularzDodajKlienta.addFormItem(adresKlienta, "Adres ");
        Binder<Klient> binder = new Binder<>(Klient.class);
        binder.forField(imieKlienta)
                .asRequired("Musisz podać imie")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                )
                .bind(Klient::getImie, Klient::setImie);

        binder.forField(nazwiskoKlienta)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(Klient::getNazwisko, Klient::setNazwisko);

        binder.forField(idKlienta)
                .asRequired("Musisz podać id")
                .withValidator(new RegexpValidator("Proszę podać poprawnę id np. c0000", "c[0-9]{4}")
                )
                .bind(Klient::getIdKlienta, Klient::setIdKlienta);

        binder.forField(hasloKlienta)
                .asRequired("Musisz podać hasło")
                .withValidator(new RegexpValidator("Proszę podać poprawnę hasło", "[a-zA-Z0-9]+")
                )
                .bind(Klient::getHaslo, Klient::setHaslo);
        binder.forField(firmaKlienta)
                .asRequired("Musisz podać firmę")
                .withValidator(new RegexpValidator("Proszę podać poprawną firmę", "[a-zA-Z0-9]+")
                )
                .bind(Klient::getFirma, Klient::setFirma);
        binder.forField(adresKlienta)
                .asRequired("Musisz podać adres")
                .bind(Klient::getAdres, Klient::setAdres);

        binder.addStatusChangeListener(
                event -> dodaj.setEnabled(binder.isValid()));
        dodaj.setEnabled(false);

        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Klient o takim ID już istnieje.");
        notification.add(Label, ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");

        dodaj.addClickListener(buttonClickEvent -> {
            int semafor = 0;

            for (int j = 0; j < listaKlientow.size(); j++) {
                if (idKlienta.getValue().equals(listaKlientow.get(j).getIdKlienta())) {
                    semafor = 1;
                    notification.open();
                    System.out.println("Blad doadnia klienta");
                }
            }
            if (semafor == 0) {

                Klient buffor = new Klient(imieKlienta.getValue(), nazwiskoKlienta.getValue(), idKlienta.getValue(), hasloKlienta.getValue(), firmaKlienta.getValue(), adresKlienta.getValue());
                System.out.println("Dodado klienta "+ buffor.getIdKlienta()+"  przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

                listaKlientow.add(buffor);
                imieKlienta.setValue("");
                idKlienta.setValue("");
                hasloKlienta.setValue("");
                firmaKlienta.setValue("");
                adresKlienta.setValue("");
                nazwiskoKlienta.setValue("");
            }
        });

        formularzDodajKlienta.add(dodaj);
        add(formularzDodajKlienta);


        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(true);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void dodajPracownikaBiura() {
        formularzDodajPracownikaBiura.removeAll();
        TextField i = new TextField();
        TextField n = new TextField();
        TextField id = new TextField();
        TextField h = new TextField();

        Button dodaj = new Button("DODAJ");
        dodaj.setId("przycisk_wyslij");

        formularzDodajPracownikaBiura.addFormItem(id, "ID pracownika biura ");
        formularzDodajPracownikaBiura.addFormItem(h, "Haslo ");
        formularzDodajPracownikaBiura.addFormItem(i, "Imie ");
        formularzDodajPracownikaBiura.addFormItem(n, "Nazwisko ");
        Binder<PracownikBiura> binder = new Binder<>(PracownikBiura.class);
        binder.forField(i)
                .asRequired("Musisz podać imie")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                )
                .bind(PracownikBiura::getImie, PracownikBiura::setImie);

        binder.forField(n)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(PracownikBiura::getNazwisko, PracownikBiura::setNazwisko);

        binder.forField(id)
                .asRequired("Musisz podać id")
                .withValidator(new RegexpValidator("Proszę podać poprawnę id np. b0000", "b[0-9]{4}")
                )
                .bind(PracownikBiura::getIdBiuro, PracownikBiura::setIdBiuro);

        binder.forField(h)
                .asRequired("Musisz podać hasło")
                .withValidator(new RegexpValidator("Proszę podać poprawnę hasło", "[a-zA-Z0-9]+")
                )
                .bind(PracownikBiura::getHaslo, PracownikBiura::setHaslo);

        binder.addStatusChangeListener(
                event -> dodaj.setEnabled(binder.isValid()));
        dodaj.setEnabled(false);

        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Podane ID jest zajęte");
        notification.add(Label, ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");

        dodaj.addClickListener(buttonClickEvent -> {

            int semafor = 0;

            for (int j = 0; j < listaPracownikowBiura.size(); j++) {
                if (id.getValue().equals(listaPracownikowBiura.get(j).getIdBiuro())) {
                    semafor = 1;
                }
            }

            for (int j = 0; j < listaKierownikow.size(); j++) {
                if (id.getValue().equals(listaKierownikow.get(j).getIdBiuro())) {
                    semafor = 1;
                }
            }

            if (semafor == 1) {
                notification.open();
                System.out.println("Blad dodania pracownika biura");
            }

            if (semafor == 0) {
                PracownikBiura buffor = new PracownikBiura(i.getValue(), n.getValue(), id.getValue(), h.getValue());
                System.out.println("Dodado pracownika biura "+ buffor.getIdBiuro()+"  przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

                i.setValue("");
                n.setValue("");
                id.setValue("");
                h.setValue("");
                listaPracownikowBiura.add(buffor);
            }
        });

        formularzDodajPracownikaBiura.add(dodaj);
        add(formularzDodajPracownikaBiura);


        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(true);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void dodajKierownika() {
        formularzDodajKierownika.removeAll();
        TextField i = new TextField();
        TextField n = new TextField();
        TextField id = new TextField();
        TextField h = new TextField();


        Select<String> poprzednieStanowisko = new Select<>();
        poprzednieStanowisko.setItems("Kurier", "Pracownik biura", "Kierownik", "Brak", "Magazynier");

        Button dodaj = new Button("DODAJ");
        dodaj.setId("przycisk_dodaj_kierownik");

        formularzDodajKierownika.addFormItem(id, "ID kierownika ");
        formularzDodajKierownika.addFormItem(h, "Haslo ");
        formularzDodajKierownika.addFormItem(i, "Imie ");
        formularzDodajKierownika.addFormItem(n, "Nazwisko ");
        formularzDodajKierownika.addFormItem(poprzednieStanowisko, "Poprzednie stanowisko ");
        Binder<Kierownik> binder = new Binder<>(Kierownik.class);
        binder.forField(i)
                .asRequired("Musisz podać imie")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                )
                .bind(Kierownik::getImie, Kierownik::setImie);

        binder.forField(n)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(Kierownik::getNazwisko, Kierownik::setNazwisko);

        binder.forField(id)
                .asRequired("Musisz podać id")
                .withValidator(new RegexpValidator("Proszę podać poprawnę id np. b0000", "b[0-9]{4}")
                )
                .bind(Kierownik::getIdBiuro, Kierownik::setIdBiuro);

        binder.forField(h)
                .asRequired("Musisz podać hasło")
                .withValidator(new RegexpValidator("Proszę podać poprawnę hasło", "[a-zA-Z0-9]+")
                )
                .bind(Kierownik::getHaslo, Kierownik::setHaslo);
        binder.forField(poprzednieStanowisko)
                .asRequired("Musisz podać porzednie stanowisko")
                .bind(Kierownik::getPoprzedniaStanowisko, Kierownik::setPoprzedniaStanowisko);

        binder.addStatusChangeListener(
                event -> dodaj.setEnabled(binder.isValid()));
        dodaj.setEnabled(false);

        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Podane ID jest zajęte");
        notification.add(Label, ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");

        dodaj.addClickListener(buttonClickEvent -> {
            int semafor = 0;
            for (int j = 0; j < listaKierownikow.size(); j++) {
                if (id.getValue().equals(listaKierownikow.get(j).getIdBiuro())) {
                    semafor = 1;
                }
            }

            for (int j = 0; j < listaPracownikowBiura.size(); j++) {
                if (id.getValue().equals(listaPracownikowBiura.get(j).getIdBiuro())) {
                    semafor = 1;
                }
            }
            if (semafor == 1) {
                System.out.println("blad dodania kierownika");
                notification.open();
            }
            if (semafor == 0) {
                Kierownik buffor = new Kierownik(i.getValue(), n.getValue(), id.getValue(), h.getValue(), poprzednieStanowisko.getValue());
                listaKierownikow.add(buffor);
                System.out.println("Dodado kierownika "+ buffor.getIdBiuro()+"  przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

                i.setValue("");
                n.setValue("");
                id.setValue("");
                h.setValue("");
                poprzednieStanowisko.setValue("");
            }
        });

        formularzDodajKierownika.add(dodaj);
        add(formularzDodajKierownika);


        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(true);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);

    }
    private void start() {
        layout1.setId("layout1");
        Icon avatar = VaadinIcon.USER.create();
        avatar.setColor("black");
        avatar.setId("Image");
        avatar.setSize("4em");
        TextField textField = new TextField("Kierownik:");
        textField.setId("name");
        textField.setValue(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getIdBiuro() + " " + listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getImie() + " " + listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getNazwisko() + " ");
        textField.setReadOnly(true);
        Image image = new Image("https://i.ibb.co/fvYQ3YN/Logo-Dobre.png","d");
        image.setId("logokierownik");
        layout1.add(image,avatar, textField);

        layout1.add(wyloguj);

        add(layout1);
        layout.setId("layout");
        layout.add(wszystkiePaczki, dodajWszystkie, bazyDanych,dane);
        add(layout);
        dodajWszystkie.setOpenOnHover(true);

        MenuItem account = dodajWszystkie.addItem("Dodaj");

        account.getSubMenu().addItem("kurier",
                e -> dodajKuriera());
        account.getSubMenu().addItem("klient",
                e -> dodajKlienta());
        account.getSubMenu().addItem("pracownik biura",
                e -> dodajPracownikaBiura());
        account.getSubMenu().addItem("kierownika",
                e -> dodajKierownika());

        MenuItem cybant = bazyDanych.addItem("Bazy danych");

        cybant.getSubMenu().addItem("kurierzy",
                e -> bazaDanychKurierzy());
        cybant.getSubMenu().addItem("klienci",
                e -> bazaDanychKlientow());
        cybant.getSubMenu().addItem("pracownicy biura",
                e -> bazaDanychBiura());
        cybant.getSubMenu().addItem("kierownicy",
                e -> bazaDanychKierownikow());


    }
    private void bazaDanychKurierzy() {

        formularzDodajKurieraBD.removeAll();

        System.out.println("Wyswietlono baze danych kierownikow przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

        List<Kurier> q = new ArrayList<>();
        for (int i = 0; i < listaKurierow.size(); i++) {
            q.add(listaKurierow.get(i));
        }

        Grid<Kurier> grid = new Grid<>();
        ListDataProvider<Kurier> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Kurier> numerColumn = grid.
                addColumn(Kurier::getIdKuriera).setHeader("ID");

        //filtr haslo
        Grid.Column<Kurier> hasloColumn = grid.
                addColumn(Kurier::getHaslo).setHeader("Haslo");

        //filtr imie
        Grid.Column<Kurier> imieColumn = grid.
                addColumn(Kurier::getImie).setHeader("Imie");

        //filtr nazwisko
        Grid.Column<Kurier> nazwiskoColumn = grid.
                addColumn(Kurier::getNazwisko).setHeader("Nazwisko");

        //filtr oddzial
        Grid.Column<Kurier> oddzialColumn = grid.
                addColumn(Kurier::getOddzial).setHeader("Oddzial");

        //filtr staz firmowy
        Grid.Column<Kurier> stazColumn = grid.
                addColumn(Kurier::getStazFirmowy).setHeader("Staż w firmie");

        //filtr pensja
        Grid.Column<Kurier> pensjaColumn = grid.
                addColumn(Kurier::getPensja).setHeader("Pensja");

        //filtr typ
        Grid.Column<Kurier> typColumn = grid.
                addColumn(Kurier::getTypDostarczanychPaczek).setHeader("Typ paczek");

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr numer
        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKuriera(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(numerColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr haslo
        TextField hField = new TextField();


        hField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getHaslo(),
                        hField.getValue())));
        hField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(hasloColumn).setComponent(hField);
        hField.setSizeFull();
        hField.setPlaceholder("...");

        //filtr haslo
        TextField qqField = new TextField();


        qqField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getTypDostarczanychPaczek(),
                        qqField.getValue())));
        qqField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(typColumn).setComponent(qqField);
        qqField.setSizeFull();
        qqField.setPlaceholder("...");

        //filtr oddzial
        TextField ddField = new TextField();


        ddField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOddzial(),
                        ddField.getValue())));
        ddField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(oddzialColumn).setComponent(ddField);
        ddField.setSizeFull();
        ddField.setPlaceholder("...");


        //filtr imie
        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(imieColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko
        TextField nField = new TextField();


        nField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNazwisko(),
                        nField.getValue())));
        nField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazwiskoColumn).setComponent(nField);
        nField.setSizeFull();
        nField.setPlaceholder("...");

        //filtr staz firmowy
        TextField sfField = new TextField();


        sfField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getStazFirmowy()),
                        sfField.getValue())));
        sfField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(stazColumn).setComponent(sfField);
        sfField.setSizeFull();
        sfField.setPlaceholder("...");

        //filtr pensja

        TextField pField = new TextField();


        pField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPensja()),
                        pField.getValue())));
        pField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(pensjaColumn).setComponent(pField);
        pField.setSizeFull();
        pField.setPlaceholder("...");

        formularzDodajKurieraBD.add(grid);
        add(formularzDodajKurieraBD);

        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(true);
        formularzDodajKlientaBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void bazaDanychKlientow() {

        formularzDodajKlientaBD.removeAll();


        formularzDodajPracownikaBiuraBD.removeAll();
        System.out.println("Wyswietlono baze danych klientow przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());


        List<Klient> q = new ArrayList<>();
        for (int i = 0; i < listaKlientow.size(); i++) {
            listaKlientow.get(i).bilansPaczek();
            q.add(listaKlientow.get(i));
        }

        Grid<Klient> grid = new Grid<>();
        ListDataProvider<Klient> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Klient> numerColumn = grid.
                addColumn(Klient::getIdKlienta).setHeader("ID");

        //filtr haslo
        Grid.Column<Klient> hasloColumn = grid.
                addColumn(Klient::getHaslo).setHeader("Haslo");

        //filtr imie
        Grid.Column<Klient> imieColumn = grid.
                addColumn(Klient::getImie).setHeader("Imie");

        //filtr nazwisko
        Grid.Column<Klient> nazwiskoColumn = grid.
                addColumn(Klient::getNazwisko).setHeader("Nazwisko");

        //filtr firma
        Grid.Column<Klient> stazColumn = grid.
                addColumn(Klient::getFirma).setHeader("Firma");

        //filtr pensja
        Grid.Column<Klient> pensjaColumn = grid.
                addColumn(Klient::getAdres).setHeader("Adres");

        //filtr paczki oczekujace
        Grid.Column<Klient> oczekujaceColumn = grid.
                addColumn(Klient::getIloscPaczekOczekujacych).setHeader("Paczki oczekujace");

        //filtr paczki nadane
        Grid.Column<Klient> nadaneColumn = grid.
                addColumn(Klient::getIloscPaczekNadanych).setHeader("Paczki nadane");

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr numer
        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKlienta(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(numerColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr numer
        TextField oczeField = new TextField();


        oczeField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getIloscPaczekOczekujacych()),
                        idField.getValue())));
        oczeField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(oczekujaceColumn).setComponent(oczeField);
        oczeField.setSizeFull();
        oczeField.setPlaceholder("...");


        //filtr numer
        TextField nadField = new TextField();


        nadField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getIloscPaczekNadanych()),
                        nadField.getValue())));
        nadField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nadaneColumn).setComponent(nadField);
        nadField.setSizeFull();
        nadField.setPlaceholder("...");


        //filtr haslo
        TextField hField = new TextField();


        hField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getHaslo(),
                        hField.getValue())));
        hField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(hasloColumn).setComponent(hField);
        hField.setSizeFull();
        hField.setPlaceholder("...");


        //filtr imie
        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(imieColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko
        TextField nField = new TextField();


        nField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNazwisko(),
                        nField.getValue())));
        nField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazwiskoColumn).setComponent(nField);
        nField.setSizeFull();
        nField.setPlaceholder("...");

        //filtr staz firmowy
        TextField sfField = new TextField();


        sfField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getFirma(),
                        sfField.getValue())));
        sfField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(stazColumn).setComponent(sfField);
        sfField.setSizeFull();
        sfField.setPlaceholder("...");

        //filtr pensja

        TextField pField = new TextField();


        pField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getAdres(),
                        pField.getValue())));
        pField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(pensjaColumn).setComponent(pField);
        pField.setSizeFull();
        pField.setPlaceholder("...");

        formularzDodajKlientaBD.add(grid);
        add(formularzDodajKlientaBD);

        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzDodajKlientaBD.setVisible(true);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void bazaDanychBiura() {
        formularzDodajPracownikaBiuraBD.removeAll();

        System.out.println("Wyswietlono baze danych pracownikow biura przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

        List<PracownikBiura> q = new ArrayList<>();
        for (int i = 0; i < listaPracownikowBiura.size(); i++) {
            q.add(listaPracownikowBiura.get(i));
        }

        Grid<PracownikBiura> grid = new Grid<>();
        ListDataProvider<PracownikBiura> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<PracownikBiura> numerColumn = grid.
                addColumn(PracownikBiura::getIdBiuro).setHeader("ID");

        //filtr haslo
        Grid.Column<PracownikBiura> hasloColumn = grid.
                addColumn(PracownikBiura::getHaslo).setHeader("Haslo");

        //filtr imie
        Grid.Column<PracownikBiura> imieColumn = grid.
                addColumn(PracownikBiura::getImie).setHeader("Imie");

        //filtr nazwisko
        Grid.Column<PracownikBiura> nazwiskoColumn = grid.
                addColumn(PracownikBiura::getNazwisko).setHeader("Nazwisko");

        //filtr staz firmowy
        Grid.Column<PracownikBiura> stazColumn = grid.
                addColumn(PracownikBiura::getStazFirmowy).setHeader("Staż w firmie");

        //filtr pensja
        Grid.Column<PracownikBiura> pensjaColumn = grid.
                addColumn(PracownikBiura::getPensja).setHeader("Pensja");

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr numer
        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdBiuro(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(numerColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr haslo
        TextField hField = new TextField();


        hField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getHaslo(),
                        hField.getValue())));
        hField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(hasloColumn).setComponent(hField);
        hField.setSizeFull();
        hField.setPlaceholder("...");


        //filtr imie
        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(imieColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko
        TextField nField = new TextField();


        nField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNazwisko(),
                        nField.getValue())));
        nField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazwiskoColumn).setComponent(nField);
        nField.setSizeFull();
        nField.setPlaceholder("...");

        //filtr staz firmowy
        TextField sfField = new TextField();


        sfField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getStazFirmowy()),
                        sfField.getValue())));
        sfField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(stazColumn).setComponent(sfField);
        sfField.setSizeFull();
        sfField.setPlaceholder("...");

        //filtr pensja

        TextField pField = new TextField();


        pField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPensja()),
                        pField.getValue())));
        pField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(pensjaColumn).setComponent(pField);
        pField.setSizeFull();
        pField.setPlaceholder("...");

        formularzDodajPracownikaBiuraBD.add(grid);
        add(formularzDodajPracownikaBiuraBD);

        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(true);
    }
    private void bazaDanychKierownikow() {
        update();
        formularzDodajKierownikaBD.removeAll();
        System.out.println("Wyswietlono baze danych kierownikow przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

        List<Kierownik> q = new ArrayList<>();
        for (int i = 0; i < listaKierownikow.size(); i++) {
            q.add(listaKierownikow.get(i));
        }

        Grid<Kierownik> grid = new Grid<>();
        ListDataProvider<Kierownik> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Kierownik> numerColumn = grid.
                addColumn(Kierownik::getIdBiuro).setHeader("ID");

        //filtr haslo
        Grid.Column<Kierownik> hasloColumn = grid.
                addColumn(Kierownik::getHaslo).setHeader("haslo");

        //filtr imie
        Grid.Column<Kierownik> imieColumn = grid.
                addColumn(Kierownik::getImie).setHeader("IMIE");

        //filtr nazwisko
        Grid.Column<Kierownik> nazwiskoColumn = grid.
                addColumn(Kierownik::getNazwisko).setHeader("NAZWISKO");

        //filtr staz firmowy
        Grid.Column<Kierownik> stazColumn = grid.
                addColumn(Kierownik::getStazFirmowy).setHeader("Staż w firmie");

        //filtr stanowisko
        Grid.Column<Kierownik> stanowikoColumn = grid.
                addColumn(Kierownik::getPoprzedniaStanowisko).setHeader("Poprzednie stanowisko");

        //filtr pensja
        Grid.Column<Kierownik> pensjaColumn = grid.
                addColumn(Kierownik::getPensja).setHeader("Pensja");

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr numer
        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdBiuro(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(numerColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr haslo
        TextField hField = new TextField();


        hField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getHaslo(),
                        hField.getValue())));
        hField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(hasloColumn).setComponent(hField);
        hField.setSizeFull();
        hField.setPlaceholder("...");


        //filtr imie
        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(imieColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko
        TextField nField = new TextField();


        nField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNazwisko(),
                        nField.getValue())));
        nField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazwiskoColumn).setComponent(nField);
        nField.setSizeFull();
        nField.setPlaceholder("...");

        //filtr staz firmowy
        TextField sfField = new TextField();


        sfField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getStazFirmowy()),
                        sfField.getValue())));
        sfField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(stazColumn).setComponent(sfField);
        sfField.setSizeFull();
        sfField.setPlaceholder("...");

        //filtr pensja

        TextField pField = new TextField();


        pField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPensja()),
                        pField.getValue())));
        pField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(pensjaColumn).setComponent(pField);
        pField.setSizeFull();
        pField.setPlaceholder("...");

        //filtr stanowiko

        TextField stField = new TextField();


        stField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getPoprzedniaStanowisko(),
                        stField.getValue())));
        stField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(stanowikoColumn).setComponent(stField);
        stField.setSizeFull();
        stField.setPlaceholder("...");

        formularzDodajKierownikaBD.add(grid);
        add(formularzDodajKierownikaBD);

        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(true);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void wyswietlWszystkiePaczki() {
        //update();
        mapaWszystkiePaczki.removeAll();
        System.out.println("Wyswietlono wyswietlono wszystkie paczki przez " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

        List<Paczka> q = new ArrayList<>();
        for (int i = 0; i < listaPaczek.size(); i++) {
            q.add(listaPaczek.get(i));
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Paczka> numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("numer");

        //filtr aktualnyStan
        Grid.Column<Paczka> aktualnyStanColumn = grid.
                addColumn(Paczka::getAktualnyStan).setHeader("stan");

        //filtr typPaczki
        Grid.Column<Paczka> tpColumn = grid.
                addColumn(Paczka::getTypPaczki).setHeader("typ paczki");

        //filtr oddzial nadawcy
        Grid.Column<Paczka> odColumn = grid.
                addColumn(Paczka::getNadawczyOddzial).setHeader("oddział nadawcy");

        //filtr id odbierajacy
        Grid.Column<Paczka> idkoColumn = grid.
                addColumn(Paczka::getIdKurieraOdbierajacego).setHeader("kurier odb.");

        //filtr id nadawcy
        Grid.Column<Paczka> idnColumn = grid.
                addColumn(Paczka::getIdNadawcy).setHeader("nadawca");


        //filtr oddzial odbierajcy
        Grid.Column<Paczka> ooColumn = grid.
                addColumn(Paczka::getOdbiorczyOddzial).setHeader("oddział odbiorcy");


        //filtr id dostarczajacy
        Grid.Column<Paczka> idkdColumn = grid.
                addColumn(Paczka::getIdKurieraDostarczajacego).setHeader("kurier dost.");

        //filtr id odbiorcy
        Grid.Column<Paczka> idoColumn = grid.
                addColumn(Paczka::getIdOdbiorcy).setHeader("odbiorca");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        //System.out.println();

        HeaderRow filterRow = grid.appendHeaderRow();
        //filtr id odbiorcy

        TextField idoField = new TextField();

        idoField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdOdbiorcy(),
                        idoField.getValue())));
        idoField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idoColumn).setComponent(idoField);
        idoField.setSizeFull();
        idoField.setPlaceholder("...");
        //filtr id nadawcy
        TextField idnField = new TextField();

        idnField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdNadawcy(),
                        idnField.getValue())));
        idnField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idnColumn).setComponent(idnField);
        idnField.setSizeFull();
        idnField.setPlaceholder("...");

        //filtr numer
        TextField nField = new TextField();


        nField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getNumer()),
                        nField.getValue())));
        nField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(numerColumn).setComponent(nField);
        nField.setSizeFull();
        nField.setPlaceholder("...");


        //filtr typPaczki
        TextField tpField = new TextField();


        tpField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getTypPaczki(),
                        tpField.getValue())));
        tpField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(tpColumn).setComponent(tpField);
        tpField.setSizeFull();
        tpField.setPlaceholder("...");


        //filtr aktualnyStan
        TextField asField = new TextField();

        asField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getAktualnyStan(),
                        asField.getValue())));
        asField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(aktualnyStanColumn).setComponent(asField);
        asField.setSizeFull();
        asField.setPlaceholder("...");


        //filtr id odbierajacy
        TextField idooField = new TextField();

        idooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKurieraOdbierajacego(),
                        idooField.getValue())));
        idooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idkoColumn).setComponent(idooField);
        idooField.setSizeFull();
        idooField.setPlaceholder("...");

        //filtr id dostarczajacy
        TextField iddField = new TextField();

        iddField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKurieraDostarczajacego(),
                        iddField.getValue())));
        iddField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idkdColumn).setComponent(iddField);
        iddField.setSizeFull();
        iddField.setPlaceholder("...");
        //filtr oddzial odbierajacy
        TextField ooField = new TextField();

        ooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorczyOddzial(),
                        ooField.getValue())));
        ooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(ooColumn).setComponent(ooField);
        ooField.setSizeFull();
        ooField.setPlaceholder("...");
        //filtr oddzial dosrczajacy
        TextField odField = new TextField();

        odField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawczyOddzial(),
                        odField.getValue())));
        odField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(odColumn).setComponent(odField);
        odField.setSizeFull();
        odField.setPlaceholder("...");

        mapaWszystkiePaczki.add(grid);
        add(mapaWszystkiePaczki);


        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(true);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
    private void identyfikator() {
        formularzIdentyfikator.removeAll();
        System.out.println("Wyswietlono dane kierownika " +listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size()-1)).getIdBiuro());

        TextField idBiuro = new TextField();
        TextField haslo = new TextField();
        TextField imie = new TextField();
        TextField nazwisko = new TextField();
        TextField pensja = new TextField();
        TextField stazFirmowy = new TextField();

        listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).obliczPensje();

        idBiuro.setValue(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getIdBiuro());
        haslo.setValue(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getHaslo());
        imie.setValue(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getImie());
        nazwisko.setValue(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getNazwisko());
        pensja.setValue(String.valueOf(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getPensja()));
        stazFirmowy.setValue(String.valueOf(listaKierownikow.get(aktualnyIteratorKierownik.get(aktualnyIteratorKierownik.size() - 1)).getStazFirmowy()));

        idBiuro.setReadOnly(true);
        haslo.setReadOnly(true);
        imie.setReadOnly(true);
        nazwisko.setReadOnly(true);
        pensja.setReadOnly(true);
        stazFirmowy.setReadOnly(true);

        formularzIdentyfikator.addFormItem(imie, "Imie ");
        formularzIdentyfikator.addFormItem(nazwisko, "Nazwisko ");
        formularzIdentyfikator.addFormItem(idBiuro, "ID biuro ");
        formularzIdentyfikator.addFormItem(haslo, "Haslo ");
        formularzIdentyfikator.addFormItem(pensja, "Pensja ");
        formularzIdentyfikator.addFormItem(stazFirmowy, "Staz Firmowy ");

        add(formularzIdentyfikator);

        formularzDodajKuriera.setVisible(false);
        formularzDodajKlienta.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzDodajKierownika.setVisible(false);
        formularzDodajPracownikaBiura.setVisible(false);
        daneKierownika.setVisible(false);
        formularzDodajKurieraBD.setVisible(false);
        formularzIdentyfikator.setVisible(true);
        formularzDodajKlientaBD.setVisible(false);
        formularzDodajKierownikaBD.setVisible(false);
        formularzDodajPracownikaBiuraBD.setVisible(false);
    }
}