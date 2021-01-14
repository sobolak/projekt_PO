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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import deliveryproject.demo.Podstawa.Paczka;
import deliveryproject.demo.Interfejsy.dane;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * This example project shows how to use VerticalLayout and HorizontalLayout to build a typical responsive application disposition.
 */
@Route("kurier")
@PageTitle("kurier")
@StyleSheet("/styles/main.css")
public class KurierView extends Div implements dane {

    VerticalLayout identyfikator = new VerticalLayout();
    HorizontalLayout layout = new HorizontalLayout();
    FormLayout formularzDane = new FormLayout() ;

    HorizontalLayout formularzWydanePaczki = new HorizontalLayout();
    HorizontalLayout formularzOdebranePaczki = new HorizontalLayout();
    HorizontalLayout formularzDostarczPaczki = new HorizontalLayout();


    Button daneKuriera = new Button("Dane");
    Button buttonWydajPaczki= new Button("Wydaj paczki");
    Button buttonOddbierzPaczki = new Button("Odbierz paczki");
    Button buttonDostarczPaczki = new Button("Dostarcz paczki");
    Button wyloguj = new Button("wyloguj");
    VerticalLayout layout1 = new VerticalLayout();
    HorizontalLayout statusyWydana = new HorizontalLayout();
    HorizontalLayout statusyOdebrana = new HorizontalLayout();
    HorizontalLayout statusyAkcja = new HorizontalLayout();

    Set<Paczka> buffor = new HashSet<>();
    String oddzial;


    public KurierView() {

        start();
        wyloguj.setId("przycisk_wyloguj");
        daneKuriera.setId("przycisk_klient");
        buttonDostarczPaczki.setId("przycisk_klient");
        buttonOddbierzPaczki.setId("przycisk_klient");
        buttonWydajPaczki.setId("przycisk_klient");
        formularzDane.setId("formularz");

        daneKuriera.addClickListener(buttonClickEvent -> {
            identyfikator();
            formularzDane.setVisible(true);
            formularzWydanePaczki.setVisible(false);
            statusyWydana.setVisible(false);
            statusyOdebrana.setVisible(false);
            formularzOdebranePaczki.setVisible(false);
            formularzDostarczPaczki.setVisible(false);
            statusyAkcja.setVisible(false);
        });
        buttonWydajPaczki.addClickListener(buttonClickEvent -> {
            formularzOdebranePaczki.removeAll();
            formularzWydanePaczki.removeAll();
            formularzDostarczPaczki.removeAll();

            statusyAkcja.removeAll();
            statusyOdebrana.removeAll();
            statusyWydana.removeAll();

            wydajPaczki();
            formularzDane.setVisible(false);
        });
        buttonOddbierzPaczki.addClickListener(buttonClickEvent -> {
            formularzOdebranePaczki.removeAll();
            formularzWydanePaczki.removeAll();
            formularzDostarczPaczki.removeAll();

            statusyAkcja.removeAll();
            statusyOdebrana.removeAll();
            statusyWydana.removeAll();

            odbierzPaczki();
            formularzDane.setVisible(false);
        });
        buttonDostarczPaczki.addClickListener(buttonClickEvent -> {
            formularzOdebranePaczki.removeAll();
            formularzWydanePaczki.removeAll();
            formularzDostarczPaczki.removeAll();

            statusyAkcja.removeAll();
            statusyOdebrana.removeAll();
            statusyWydana.removeAll();

            akcjePaczki();
            formularzDane.setVisible(false);
        });
        wyloguj.addClickListener(buttonClickEvent -> {
            System.out.println("Wylogowanie do ekranu glownego");
            UI.getCurrent().navigate("");
        });
    }
    private void start(){
        Icon avatar = VaadinIcon.USER.create();
        avatar.setColor("black");
        avatar.setId("Image");
        avatar.setSize("4em");
        TextField textField = new TextField("Kurier:");
        textField.setId("name");
        Image image = new Image("https://i.ibb.co/fvYQ3YN/Logo-Dobre.png","d");
        image.setId("logokierownik");

        layout1.setId("layout1");

        textField.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera()+" "+listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getImie()+" "+ listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getNazwisko()+" ");
        oddzial = listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getOddzial();
        textField.setReadOnly(true);

        layout1.add(image,avatar,textField);
        layout1.add(wyloguj);
        add(layout1);

        statusyWydana.setId("przyciskNaSrodek");
        statusyOdebrana.setId("przyciskNaSrodek");
        statusyAkcja.setId("przyciskNaSrodek");


        layout.setId("layout");
        layout.add(buttonWydajPaczki,buttonOddbierzPaczki,buttonDostarczPaczki,daneKuriera);
        add(layout);

        //Paczka paczka1 = new Paczka(85,100,12,14,"sss","ss","ss","ss",100,true,100,"opolskie","mazowieckie");
    }
    private void identyfikator(){
        formularzDane.removeAll();
        System.out.println("Wyswietlono dane "+listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());
        TextField imieKuriera = new TextField();
        TextField nazwiskoKuriera = new TextField();
        TextField idKuriera = new TextField();
        TextField email = new TextField();
        TextField pensja= new TextField();
        TextField stazFirmow= new TextField();
        TextField oddzial = new TextField();
        TextField typDostarczaniaPaczek= new TextField();


        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).obliczPensje();

        imieKuriera.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getImie());
        nazwiskoKuriera.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getNazwisko());
        idKuriera.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());
        email.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getImie()+listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getNazwisko()+"@cybanteria.pl");
        pensja.setValue(String.valueOf(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getPensja()));
        stazFirmow.setValue(String.valueOf(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getStazFirmowy()));
        oddzial.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getOddzial());
        typDostarczaniaPaczek.setValue(listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getTypDostarczanychPaczek());

        email.setReadOnly(true);
        imieKuriera.setReadOnly(true);
        nazwiskoKuriera.setReadOnly(true);
        idKuriera.setReadOnly(true);
        pensja.setReadOnly(true);
        stazFirmow.setReadOnly(true);
        oddzial.setReadOnly(true);
        typDostarczaniaPaczek.setReadOnly(true);


        formularzDane.addFormItem(imieKuriera,"Imie ");
        formularzDane.addFormItem(nazwiskoKuriera,"Nazwisko ");
        formularzDane.addFormItem(idKuriera,"Numer kuriera ");
        formularzDane.addFormItem(email,"Email ");
        formularzDane.addFormItem(pensja, "Pensja ");
        formularzDane.addFormItem(stazFirmow, "Staz firmowy ");
        formularzDane.addFormItem(oddzial, "Oddzial ");
        formularzDane.addFormItem(typDostarczaniaPaczek, "Typ paczek ");


        add(formularzDane);

        formularzDane.setVisible(false);
    }
    private void wydajPaczki(){
        //formularzWydanePaczki.removeAll();
        //statusy.removeAll();
        //System.out.println(oddzial);

        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            if (listaPaczek.get(i).oddzialy.getOdbiorczy().equals(oddzial) && listaPaczek.get(i).getAktualnyStan().equals("WYSLANA")){
                q.add(listaPaczek.get(i));
                //System.out.println(listaPaczek.get(i).getNumer());
            }
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);


        //filtr numer
        Grid.Column<Paczka>  numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("numer");

        //filtr aktualnyStan
        Grid.Column<Paczka>  aktualnyStanColumn = grid.
                addColumn(Paczka::getAktualnyStan).setHeader("stan");

        //filtr typPaczki
        Grid.Column<Paczka>  tpColumn = grid.
                addColumn(Paczka::getTypPaczki).setHeader("typ paczki");

        //filtr Imie odbiorcy
        Grid.Column<Paczka>  iColumn = grid.
                addColumn(Paczka::getOdbiorcaImie).setHeader("imie odbiorcy");
        //filtr nazwisko odbiorcy
        Grid.Column<Paczka>  nazColumn = grid.
                addColumn(Paczka::getOdbiorcaNazwisko).setHeader("nazwisko odbiorcy");
        //filtr adres
        Grid.Column<Paczka>  aColumn = grid.
                addColumn(Paczka::getAddresOdbiorcy).setHeader("adres odbiorcy");
        //fitr waga
        Grid.Column<Paczka>  wColumn = grid.
                addColumn(Paczka::getWaga).setHeader("waga");
        //filtr oddzial odbiorcy
        Grid.Column<Paczka>  odColumn = grid.
                addColumn(Paczka::getOdbiorczyOddzial).setHeader("oddzial");
        //cybant
        Grid.Column<Paczka>  cColumn = grid.
                addColumn(Paczka::isCybant).setHeader("cybant?");


        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        Button dodaj = new Button("WYDAJ NA  MNIE");
        dodaj.setId("przycisk_wyslij_kurier");

        dodaj.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).doDoreczenia(listaPaczek.get(i));
                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" wydana na kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());
                        //System.out.println("zaz");
                    }
                }
            }
            formularzWydanePaczki.removeAll();
            statusyWydana.removeAll();
            wydajPaczki();
        });
        statusyWydana.add(dodaj);

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr cybant

        TextField cField = new TextField();


        cField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.isCybant()),
                        cField.getValue())));
        cField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(cColumn).setComponent(cField);
        cField.setSizeFull();
        cField.setPlaceholder("...");

        //filtr Imie odbiorcy

        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(iColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko odbiorcy
        TextField nazField = new TextField();


        nazField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaNazwisko(),
                        nazField.getValue())));
        nazField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazColumn).setComponent(nazField);
        nazField.setSizeFull();
        nazField.setPlaceholder("...");

        //filtr adres
        TextField aField = new TextField();


        aField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getAddresOdbiorcy(),
                        aField.getValue())));
        aField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(aColumn).setComponent(aField);
        aField.setSizeFull();
        aField.setPlaceholder("...");

        //fitr waga

        TextField wField = new TextField();


        wField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getWaga()),
                        wField.getValue())));
        wField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(wColumn).setComponent(wField);
        wField.setSizeFull();
        wField.setPlaceholder("...");

        //filtr oddizal odbiorcy

        TextField odField = new TextField();


        odField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorczyOddzial(),
                        odField.getValue())));
        odField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(odColumn).setComponent(odField);
        odField.setSizeFull();
        odField.setPlaceholder("...");

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

        formularzWydanePaczki.add(grid);
        add(formularzWydanePaczki);
        add(statusyWydana);
        formularzWydanePaczki.setVisible(true);
        statusyWydana.setVisible(true);
    }
    private void odbierzPaczki(){
        //formularzWydanePaczki.removeAll();
        //statusy.removeAll();

        //System.out.println(oddzial);


        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            if (listaPaczek.get(i).oddzialy.getNadawczy().equals(oddzial) && listaPaczek.get(i).getAktualnyStan().equals("NADANA")){
                q.add(listaPaczek.get(i));
                //System.out.println(listaPaczek.get(i).getNumer());
            }
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);


        //filtr numer
        Grid.Column<Paczka>  numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("numer");

        //filtr aktualnyStan
        Grid.Column<Paczka>  aktualnyStanColumn = grid.
                addColumn(Paczka::getAktualnyStan).setHeader("stan");

        //filtr typPaczki
        Grid.Column<Paczka>  tpColumn = grid.
                addColumn(Paczka::getTypPaczki).setHeader("typ paczki");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        //filtr Imie nadawcy
        Grid.Column<Paczka>  iColumn = grid.
                addColumn(Paczka::getNadawcaImie).setHeader("imie");
        //filtr nazwisko nadawca
        Grid.Column<Paczka>  nazColumn = grid.
                addColumn(Paczka::getNadawcaNazwisko).setHeader("nazwisko");
        //fitr waga
        Grid.Column<Paczka>  wColumn = grid.
                addColumn(Paczka::getWaga).setHeader("waga");
        //filtr oddzial nadawcy
        Grid.Column<Paczka>  odColumn = grid.
                addColumn(Paczka::getNadawczyOddzial).setHeader("ODDZIAL");

        //cybant
        Grid.Column<Paczka>  cColumn = grid.
                addColumn(Paczka::isCybant).setHeader("cybant?");

        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        Button dodaj = new Button("ODDBIERZ PACZKI");
        dodaj.setId("przycisk_wyslij_kurier");

        dodaj.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).potwierdzOdbior(listaPaczek.get(i));
                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" odebrana przez kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());

                        //System.out.println("zaz");
                    }
                }
            }
           ;
            formularzOdebranePaczki.removeAll();
            statusyOdebrana.removeAll();
            odbierzPaczki();
        });
        statusyOdebrana.add(dodaj);

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr Imie nadawca

        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawcaImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(iColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko nadawca
        TextField nazField = new TextField();


        nazField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawcaNazwisko(),
                        nazField.getValue())));
        nazField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazColumn).setComponent(nazField);
        nazField.setSizeFull();
        nazField.setPlaceholder("...");

//filtr cybant

        TextField cField = new TextField();


        cField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.isCybant()),
                        cField.getValue())));
        cField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(cColumn).setComponent(cField);
        cField.setSizeFull();
        cField.setPlaceholder("...");

        //fitr waga

        TextField wField = new TextField();


        wField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getWaga()),
                        wField.getValue())));
        wField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(wColumn).setComponent(wField);
        wField.setSizeFull();
        wField.setPlaceholder("...");

        //filtr oddizal nadawczy

        TextField odField = new TextField();


        odField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawczyOddzial(),
                        odField.getValue())));
        odField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(odColumn).setComponent(odField);
        odField.setSizeFull();
        odField.setPlaceholder("...");

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

        formularzOdebranePaczki.add(grid);
        add(formularzOdebranePaczki);
        add(statusyOdebrana);
        formularzOdebranePaczki.setVisible(true);
        statusyOdebrana.setVisible(true);
    }
    private void akcjePaczki(){
        //formularzWydanePaczki.removeAll();
        //statusy.removeAll();
        //System.out.println(oddzial);

        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            String cybant = listaPaczek.get(i).getIdKurieraDostarczajacego();
            if (cybant == null){
                cybant = "cybant";
            }
            if (listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera().equals(cybant) && listaPaczek.get(i).getAktualnyStan().equals("WYDANA")){
                q.add(listaPaczek.get(i));
                //System.out.println(listaPaczek.get(i).getNumer());
            }
        }

        Grid<Paczka> grid = new Grid<>();
        ListDataProvider<Paczka> dataProvider = new ListDataProvider<>(q);
        grid.setDataProvider(dataProvider);


        //filtr numer
        Grid.Column<Paczka>  numerColumn = grid.
                addColumn(Paczka::getNumer).setHeader("numer");
        //filtr typPaczki
        Grid.Column<Paczka>  tpColumn = grid.
                addColumn(Paczka::getTypPaczki).setHeader("typ paczki");

        //filtr Imie odbiorcy
        Grid.Column<Paczka>  iColumn = grid.
                addColumn(Paczka::getOdbiorcaImie).setHeader("imie");
        //filtr nazwisko odbiorcy
        Grid.Column<Paczka>  nazColumn = grid.
                addColumn(Paczka::getOdbiorcaNazwisko).setHeader("nazwisko");
        //filtr adres
        Grid.Column<Paczka>  aColumn = grid.
                addColumn(Paczka::getAddresOdbiorcy).setHeader("adres");
        //fitr waga
        Grid.Column<Paczka>  wColumn = grid.
                addColumn(Paczka::getWaga).setHeader("waga");
        //filtr dokumenty
        Grid.Column<Paczka>  dColumn = grid.
                addColumn(Paczka::getDokumenty).setHeader("dokumenty");
        //filtr pobranie
        Grid.Column<Paczka>  pColumn = grid.
                addColumn(Paczka::getPobranie).setHeader("pobranie");

        //cybant
        Grid.Column<Paczka>  cColumn = grid.
                addColumn(Paczka::isCybant).setHeader("cybant?");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        //Button dodaj = new Button("DOSTARCZ");
        Button buttonOdmowa = new Button("ODMOWA");
        Button buttonAwizo = new Button("AWIZO");
        Button buttonDoreczona = new Button("DORECZONA");
        buttonAwizo.setId("przycisk_klient");
        buttonDoreczona.setId("przycisk_klient");
        buttonOdmowa.setId("przycisk_klient");
        buttonDoreczona.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).potwierdzDoreczenie(listaPaczek.get(i));                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" odebrana przez kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());
                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" dostarczona przez kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());

                        //System.out.println("zaz");
                    }
                }
            }
            formularzDostarczPaczki.removeAll();
            statusyAkcja.removeAll();
            akcjePaczki();
        });

        buttonAwizo.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).awizo(listaPaczek.get(i));
                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" awizo kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());

                        //System.out.println("zaz");
                    }
                }
            }
            formularzDostarczPaczki.removeAll();
            statusyAkcja.removeAll();
            akcjePaczki();
        });


        buttonOdmowa.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).odmowa(listaPaczek.get(i));
                        System.out.println("Paczka "+listaPaczek.get(i).getNumer()+" odmowa zarejestrowana przez kuriera " +listaKurierow.get(aktualnyIteratorKurier.get(aktualnyIteratorKurier.size()-1)).getIdKuriera());

                        //System.out.println("zaz");
                    }
                }
            }
            formularzDostarczPaczki.removeAll();
            statusyAkcja.removeAll();
            akcjePaczki();
        });


        statusyAkcja.add(buttonDoreczona);
        statusyAkcja.add(buttonAwizo);
        statusyAkcja.add(buttonOdmowa);

        HeaderRow filterRow = grid.appendHeaderRow();

        //filtr dokumenty

        TextField dField = new TextField();


        dField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getDokumenty(),
                        dField.getValue())));
        dField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(dColumn).setComponent(dField);
        dField.setSizeFull();
        dField.setPlaceholder("...");

        //filtr pobranie

        TextField pField = new TextField();


        pField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getPobranie()),
                        pField.getValue())));
        pField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(pColumn).setComponent(pField);
        pField.setSizeFull();
        pField.setPlaceholder("...");

        //filtr cybant

        TextField cField = new TextField();


        cField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.isCybant()),
                        cField.getValue())));
        cField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(cColumn).setComponent(cField);
        cField.setSizeFull();
        cField.setPlaceholder("...");

        //filtr Imie odbiorcy

        TextField iField = new TextField();


        iField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaImie(),
                        iField.getValue())));
        iField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(iColumn).setComponent(iField);
        iField.setSizeFull();
        iField.setPlaceholder("...");

        //filtr nazwisko odbiorcy
        TextField nazField = new TextField();


        nazField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorcaNazwisko(),
                        nazField.getValue())));
        nazField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(nazColumn).setComponent(nazField);
        nazField.setSizeFull();
        nazField.setPlaceholder("...");

        //filtr adres
        TextField aField = new TextField();


        aField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getAddresOdbiorcy(),
                        aField.getValue())));
        aField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(aColumn).setComponent(aField);
        aField.setSizeFull();
        aField.setPlaceholder("...");

        //fitr waga

        TextField wField = new TextField();


        wField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(String.valueOf(person.getWaga()),
                        wField.getValue())));
        wField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(wColumn).setComponent(wField);
        wField.setSizeFull();
        wField.setPlaceholder("...");

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


        formularzDostarczPaczki.add(grid);
        add(formularzDostarczPaczki);
        add(statusyAkcja);
        formularzDostarczPaczki.setVisible(true);
        statusyAkcja.setVisible(true);
    }
}