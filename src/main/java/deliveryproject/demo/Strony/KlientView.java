package deliveryproject.demo.Strony;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import deliveryproject.demo.Podstawa.Paczka;
import deliveryproject.demo.Interfejsy.dane;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static java.lang.Thread.sleep;


/**
 * This example project shows how to use VerticalLayout and HorizontalLayout to build a typical responsive application disposition.
 */
@Route("klient")
@PageTitle("klient")
@StyleSheet("/styles/main.css")
public class KlientView extends Div implements dane {
    VerticalLayout layout1 = new VerticalLayout();
    HorizontalLayout layout = new HorizontalLayout();

    Set<Paczka> buffor = new HashSet<>();

    Button button1 = new Button("Paczki wyslane");
    Button button2 = new Button("Wyślij");
    Button button3 = new Button("Dane");
    Button button5 = new Button("Paczki do mnie");
    Button wyloguj = new Button("wyloguj");


    FormLayout formularz = new FormLayout(); //data validating vaadin
    FormLayout formularzDane = new FormLayout() ;
    HorizontalLayout fomularzSkarga = new HorizontalLayout();
    HorizontalLayout mapaWysłanePaczki = new HorizontalLayout();
    HorizontalLayout mapaPaczkiDoMnie = new HorizontalLayout();
    String IdKlienta ;

    public KlientView() {

       start();

        button1.setId("przycisk_klient");
        button2.setId("przycisk_klient");
        button3.setId("przycisk_klient");
        button5.setId("przycisk_klient");
        wyloguj.setId("przycisk_wyloguj");

        button2.addClickListener(buttonClickEvent -> {
            formularz.removeAll();
            wyslijPaczke();
            formularz.setVisible(true);
            formularzDane.setVisible(false);
            mapaWysłanePaczki.setVisible(false);
            fomularzSkarga.setVisible(false);
            mapaPaczkiDoMnie.setVisible(false);
        });

        button1.addClickListener(buttonClickEvent -> {
            formularz.setVisible(false);
            formularzDane.setVisible(false);
            fomularzSkarga.setVisible(false);
            mapaPaczkiDoMnie.setVisible(false);
            wysłanePaczki();
        });
        button3.addClickListener(buttonClickEvent -> {
            formularz.setVisible(false);
            mapaWysłanePaczki.setVisible(false);
            fomularzSkarga.setVisible(false);
            mapaPaczkiDoMnie.setVisible(false);
            dane();
        });

        button5.addClickListener(buttonClickEvent -> {
            formularzDane.setVisible(false);
            formularz.setVisible(false);
            mapaWysłanePaczki.setVisible(false);
            mapaPaczkiDoMnie.setVisible(true);
            fomularzSkarga.setVisible(false);
            paczkiDoMnie();
        });
        wyloguj.addClickListener(buttonClickEvent -> {
            System.out.println("Wylogowanie do ekranu glownego");
            UI.getCurrent().navigate("");
        });


    }
    private void start(){
        layout1.setId("layout1");
        Icon avatar = VaadinIcon.USER.create();
        avatar.setColor("black");
        avatar.setId("Image");
        avatar.setSize("4em");
        TextField textField = new TextField("Klient:");
        textField.setId("name");
        textField.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta()+" "+listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getImie()+" "+listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getNazwisko()+" ");
        textField.setReadOnly(true);
        Image image = new Image("https://i.ibb.co/fvYQ3YN/Logo-Dobre.png","d");
        image.setId("logokierownik");
        layout1.add(image,avatar,textField);
        layout1.add(wyloguj);
        add(layout1);

        IdKlienta = listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta();

        layout.setId("layout");
        layout.add(button2 , button1 , button5 , button3);
        add(layout);

    }
    private void wyslijPaczke(){
        formularz.removeAll();
        TextField imieNadawcy = new TextField();
        TextField nazwiskoNadawcy = new TextField();
        NumberField waga = new NumberField();
        TextField imieOdbiorcy = new TextField();
        TextField nazwiskoOdbiorcy = new TextField();
        TextField adresOdbiorcy = new TextField();
        NumberField Pobranie = new NumberField();
        NumberField dlugosc = new NumberField();
        NumberField szerokosc = new NumberField();
        NumberField wysokosc = new NumberField();
        waga.setHasControls(true);
        waga.setMin(0);
        Pobranie.setHasControls(true);
        Pobranie.setMin(0);
        dlugosc.setHasControls(true);
        dlugosc.setMin(0);
        szerokosc.setHasControls(true);
        szerokosc.setMin(0);
        wysokosc.setHasControls(true);
        wysokosc.setMin(0);
        imieNadawcy.setMinLength(2);
        Binder<Paczka> binder = new Binder<>(Paczka.class);
        binder.forField(imieNadawcy)
                .asRequired("Musisz podać imię")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                        )
                .bind(Paczka::getNadawcaImie,Paczka::setNadawcaImie);
        binder.forField(nazwiskoNadawcy)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(Paczka::getNadawcaNazwisko,Paczka::setNadawcaNazwisko);
        binder.forField(imieOdbiorcy)
                .asRequired("Musisz podać imię")
                .withValidator(new RegexpValidator("Proszę podać poprawnę imie", "[a-zA-Z]+")
                )
                .bind(Paczka::getOdbiorcaImie,Paczka::setOdbiorcaImie);
        binder.forField(nazwiskoOdbiorcy)
                .asRequired("Musisz podać nazwisko")
                .withValidator(new RegexpValidator("Proszę podać poprawnę nazwisko", "[a-zA-Z]+")
                )
                .bind(Paczka::getOdbiorcaNazwisko,Paczka::setOdbiorcaNazwisko);
        binder.forField(adresOdbiorcy)
                .asRequired("Musisz podać nazwisko")
                .bind(Paczka::getAddresOdbiorcy,Paczka::setAddresOdbiorcy);
        binder.forField(waga)
            .asRequired("Podaj wage")
            .bind(Paczka::getWaga,Paczka::setWaga);
        binder.forField(szerokosc)
                .asRequired("Podaj szerokosc")
                .bind(Paczka::getSzerokosc,Paczka::setSzerokosc);
        binder.forField(wysokosc)
                .asRequired("Podaj wysokosc")
                .bind(Paczka::getWysokosc,Paczka::setWysokosc);
        binder.forField(dlugosc)
                .asRequired("Podaj szerokosc")
                .bind(Paczka::getDlugosc,Paczka::setDlugosc);
        binder.forField(Pobranie)
                .asRequired()
                .bind(Paczka::getPobranie,Paczka::setPobranie);

        Select<String> valueComboBox = new Select<>();
        valueComboBox.setItems("malopolskie", "zachodnio-pomorskie", "warminsko-mazurskie","kujawsko-pomorskie", "lubuskie", "wielkopolskie", "mazowieckie", "podlaskie", "lodzkie", "dolnoslaskie", "opolskie", "slaskie", "podkarpackie", "lubelskie", "swietokrzyskie", "pomorskie");


        Select<String> valueComboBox2 = new Select<>();
        valueComboBox2.setItems("malopolskie", "zachodnio-pomorskie", "warminsko-mazurskie","kujawsko-pomorskie", "lubuskie", "wielkopolskie", "mazowieckie", "podlaskie", "lodzkie", "dolnoslaskie", "opolskie", "slaskie", "podkarpackie", "lubelskie", "swietokrzyskie", "pomorskie");



        Select<String> dokumenty = new Select<>();
        dokumenty.setItems("ZPD","umowa","brak");

        Select<String> ubezbieczenie = new Select<>();
        ubezbieczenie.setItems("dodatkowe ubezpieczenie","standard");

        binder.forField(valueComboBox2)
                .asRequired()
                .bind(Paczka::getOdbiorczyOddzial,Paczka::setOdbiorczyOddzial);

        binder.forField(ubezbieczenie)
                .asRequired()
                .bind(Paczka::getUbezpieczenie,Paczka::setUbezpieczenie);

        binder.forField(dokumenty)
                .asRequired()
                .bind(Paczka::getDokumenty,Paczka::setUbezpieczenie);

        binder.forField(valueComboBox)
                .asRequired()
                .bind(Paczka::getOdbiorczyOddzial,Paczka::setOdbiorczyOddzial);

        Button wyslij = new Button("Wyślij");
        wyslij.setId("przycisk_wyslij");
        wyslij.setEnabled(false);
        binder.addStatusChangeListener(
                event -> wyslij.setEnabled(binder.isValid()));


        formularz.addFormItem(imieNadawcy,"Imie nadawcy");
        formularz.addFormItem(nazwiskoNadawcy,"Nazwisko nadawcy");
        formularz.addFormItem(imieOdbiorcy,"Imie odbiorcy");
        formularz.addFormItem(nazwiskoOdbiorcy,"Nazwisko odbiorcy");
        formularz.addFormItem(adresOdbiorcy,"Adres Odbiorcy ");
        formularz.addFormItem(waga,"Waga ");
        formularz.addFormItem(Pobranie,"Pobranie ");
        formularz.addFormItem(dlugosc,"Długość ");
        formularz.addFormItem(szerokosc,"szerokosc ");
        formularz.addFormItem(wysokosc,"Wysokosc ");
        formularz.addFormItem(dokumenty,"Dokumenty ");
        formularz.addFormItem(ubezbieczenie,"Ubezpieczenie ");
        formularz.addFormItem(valueComboBox,"Województwo Nadawcy ");
        formularz.addFormItem(valueComboBox2,"Województwo Odbiorcy ");

        wyslij.addClickListener(buttonClickEvent -> {


            Paczka bufor= new Paczka(waga.getValue(),(wysokosc.getValue()),
                    (szerokosc.getValue()),(dlugosc.getValue()) ,adresOdbiorcy.getValue(),nazwiskoOdbiorcy.getValue(),nazwiskoNadawcy.getValue(),
                    imieOdbiorcy.getValue(),imieNadawcy.getValue(),ubezbieczenie.getValue(),
                    dokumenty.getValue(),(Pobranie.getValue()),valueComboBox.getValue(),valueComboBox2.getValue());
            listaPaczek.add(bufor);
            System.out.println("Wysłano paczke  " + bufor.getNumer());
            //imieNadawcy.setValue("");
            //nazwiskoNadawcy.setValue("");
            //waga.setValue(null);
            wyslijPaczke();
        });
        formularz.add(wyslij);
        add(formularz);
        formularz.setId("formularz");
        formularz.setVisible(true);

    }
    public void dane(){

        formularzDane.removeAll();
        listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).bilansPaczek();
        System.out.println("Wyswietlono dane " + listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta());
        TextField imieKlienta = new TextField();
        TextField nazwiskoKlienta = new TextField();
        TextField idKlienta = new TextField();
        EmailField email = new EmailField();
        TextField firma = new TextField();
        TextField iloscPaczekNadanych = new TextField();
        TextField iloscPaczekOczekujacych = new TextField();
        TextField adres = new TextField();


        imieKlienta.setId("imieKlineta");

        imieKlienta.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getImie());
        nazwiskoKlienta.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getNazwisko());
        idKlienta.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta());
        email.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getImie()+listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getNazwisko()+"@cybanteria.pl");
        firma.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getFirma());
        adres.setValue(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getAdres());
        iloscPaczekNadanych.setValue(String.valueOf(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIloscPaczekNadanych()));
        iloscPaczekOczekujacych.setValue(String.valueOf(listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIloscPaczekOczekujacych()));


        email.setReadOnly(true);
        idKlienta.setReadOnly(true);
        nazwiskoKlienta.setReadOnly(true);
        imieKlienta.setReadOnly(true);
        firma.setReadOnly(true);
        adres.setReadOnly(true);
        iloscPaczekNadanych.setReadOnly(true);
        iloscPaczekOczekujacych.setReadOnly(true);

        formularzDane.addFormItem(imieKlienta,"Imie ");
        formularzDane.addFormItem(nazwiskoKlienta,"Nazwisko ");
        formularzDane.addFormItem(idKlienta,"Numer klienta ");
        formularzDane.addFormItem(email,"Email ");
        formularzDane.addFormItem(adres,"Adres ");
        formularzDane.addFormItem(firma,"Firma ");
        formularzDane.addFormItem(iloscPaczekNadanych,"Ilosc paczek nadanych  ");
        formularzDane.addFormItem(iloscPaczekOczekujacych,"Ilosc paczek oczekujacych ");
        formularzDane.setId("formularz");
        add(formularzDane);
        formularzDane.setVisible(true);
    }
    private void wysłanePaczki(){
        mapaWysłanePaczki.removeAll();
        System.out.println("Wyswietlono wyslane paczki klienta " + listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta());
        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            //System.out.println(listaPaczek.get(i).getIdNadawcy());
            if(listaPaczek.get(i).getIdNadawcy() == null){
            } else if(listaPaczek.get(i).getIdNadawcy().equals(IdKlienta)){
                q.add(listaPaczek.get(i));
            }
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Paczka>  numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("Numer");

        //filtr aktualnyStan
        Grid.Column<Paczka>  aktualnyStanColumn = grid.
                addColumn(Paczka::getAktualnyStan).setHeader("Stan");

        //filtr Imie
        Grid.Column<Paczka>  ImieColumn = grid.
                addColumn(Paczka::getOdbiorcaImie).setHeader("Imie odbiorcy");
        //filtr Nazwisko
        Grid.Column<Paczka>  NazwiskoColumn = grid.
                addColumn(Paczka::getOdbiorcaNazwisko).setHeader("Nazwisko odbiorcy");

        //filtr Cena
        Grid.Column<Paczka>  CenaColumn = grid.
                addColumn(Paczka::getCena).setHeader("Cena");

        //filtr Pobranie
        Grid.Column<Paczka> PobranieColumn = grid.
                addColumn(Paczka::getPobranie).setHeader("Pobranie");

        //filtr id Waga
        Grid.Column<Paczka>  WagaColumn = grid.
                addColumn(Paczka::getWaga).setHeader("Waga");


        //filtr dokumenty
        Grid.Column<Paczka>  DokumentyColumn = grid.
                addColumn(Paczka::getDokumenty).setHeader("Dokumenty");





        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        //System.out.println();

        HeaderRow filterRow = grid.appendHeaderRow();


        //filtr  Waga
        TextField idnField = new TextField();

        idnField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getWaga()),
                        idnField.getValue())));
        idnField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(WagaColumn).setComponent(idnField);
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


        //filtr Imie
        TextField tpField = new TextField();


        tpField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaImie(),
                        tpField.getValue())));
        tpField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(ImieColumn).setComponent(tpField);
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


        //filtr id Pobranie
        TextField idooField = new TextField();

        idooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPobranie()),
                        idooField.getValue())));
        idooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(PobranieColumn).setComponent(idooField);
        idooField.setSizeFull();
        idooField.setPlaceholder("...");

        //filtr dokumenty
        TextField ooField = new TextField();

        ooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getDokumenty(),
                        ooField.getValue())));
        ooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(DokumentyColumn).setComponent(ooField);
        ooField.setSizeFull();
        ooField.setPlaceholder("...");
        //filtr Cena
        TextField cField = new TextField();

        cField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getCena()),
                        cField.getValue())));
        cField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(CenaColumn).setComponent(cField);
        cField.setSizeFull();
        cField.setPlaceholder("...");
        //filtr Nazwisko
        TextField nazwiskoField = new TextField();

        nazwiskoField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaNazwisko(),
                        nazwiskoField.getValue())));
        nazwiskoField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(NazwiskoColumn).setComponent(nazwiskoField);
        nazwiskoField.setSizeFull();
        nazwiskoField.setPlaceholder("...");
        mapaWysłanePaczki.add(grid);
        add(mapaWysłanePaczki);
        mapaWysłanePaczki.setVisible(true);

    }
    private void paczkiDoMnie(){
        mapaPaczkiDoMnie.removeAll();
        System.out.println("Wyswietlono do dostarczenia paczki klienta " + listaKlientow.get(aktualnyIteratorKlient.get(aktualnyIteratorKlient.size()-1)).getIdKlienta());

        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            if(listaPaczek.get(i).getIdOdbiorcy() == null){
            } else if(listaPaczek.get(i).getIdOdbiorcy().equals(IdKlienta)){
                q.add(listaPaczek.get(i));
            }
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);

        //filtr numer
        Grid.Column<Paczka>  numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("Numer");

        //filtr aktualnyStan
        Grid.Column<Paczka>  aktualnyStanColumn = grid.
                addColumn(Paczka::getAktualnyStan).setHeader("Stan");

        //filtr Imie
        Grid.Column<Paczka>  ImieColumn = grid.
                addColumn(Paczka::getNadawcaImie).setHeader("Imie nadawcy");
        //filtr Nazwisko
        Grid.Column<Paczka>  NazwiskoColumn = grid.
                addColumn(Paczka::getNadawcaNazwisko).setHeader("Nazwisko nadawcy");

        //filtr Cena
        Grid.Column<Paczka>  CenaColumn = grid.
                addColumn(Paczka::getCena).setHeader("Cena");

        //filtr Pobranie
        Grid.Column<Paczka> PobranieColumn = grid.
                addColumn(Paczka::getPobranie).setHeader("Pobranie");

        //filtr id Waga
        Grid.Column<Paczka>  WagaColumn = grid.
                addColumn(Paczka::getWaga).setHeader("Waga");


        //filtr dokumenty
        Grid.Column<Paczka>  DokumentyColumn = grid.
                addColumn(Paczka::getDokumenty).setHeader("Dokumenty");





        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        //System.out.println();

        HeaderRow filterRow = grid.appendHeaderRow();


        //filtr  Waga
        TextField idnField = new TextField();

        idnField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getWaga()),
                        idnField.getValue())));
        idnField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(WagaColumn).setComponent(idnField);
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


        //filtr Imie
        TextField tpField = new TextField();


        tpField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawcaImie(),
                        tpField.getValue())));
        tpField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(ImieColumn).setComponent(tpField);
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


        //filtr id Pobranie
        TextField idooField = new TextField();

        idooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPobranie()),
                        idooField.getValue())));
        idooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(PobranieColumn).setComponent(idooField);
        idooField.setSizeFull();
        idooField.setPlaceholder("...");

        //filtr dokumenty
        TextField ooField = new TextField();

        ooField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getDokumenty(),
                        ooField.getValue())));
        ooField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(DokumentyColumn).setComponent(ooField);
        ooField.setSizeFull();
        ooField.setPlaceholder("...");
        //filtr Cena
        TextField cField = new TextField();

        cField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getCena()),
                        cField.getValue())));
        cField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(CenaColumn).setComponent(cField);
        cField.setSizeFull();
        cField.setPlaceholder("...");
        //filtr Nazwisko
        TextField nazwiskoField = new TextField();

        nazwiskoField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawcaNazwisko(),
                        nazwiskoField.getValue())));
        nazwiskoField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(NazwiskoColumn).setComponent(nazwiskoField);
        nazwiskoField.setSizeFull();
        nazwiskoField.setPlaceholder("...");
        mapaPaczkiDoMnie.add(grid);
        add(mapaPaczkiDoMnie);
        mapaPaczkiDoMnie.setVisible(true);
    }

}