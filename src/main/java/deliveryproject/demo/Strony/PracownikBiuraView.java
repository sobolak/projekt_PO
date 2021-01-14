package deliveryproject.demo.Strony;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
@Route("biuro")
@PageTitle("biuro")
@StyleSheet("/styles/main.css")
public class PracownikBiuraView extends Div implements dane {

    VerticalLayout layout1 = new VerticalLayout();
    HorizontalLayout layout = new HorizontalLayout();

    HorizontalLayout formularzDoWyslaniaPaczek = new HorizontalLayout();
    HorizontalLayout statusWyslana = new HorizontalLayout();
    HorizontalLayout statusOdmowa = new HorizontalLayout();
    HorizontalLayout mapaWszystkiePaczki = new HorizontalLayout();
    HorizontalLayout formularzWyszukajPaczke = new HorizontalLayout();
    HorizontalLayout formularzOdmowy = new HorizontalLayout();
    FormLayout formularzIdentyfikator = new FormLayout();
    Paczka paczka;
    FormLayout daneD = new FormLayout();


    Button buttonDaneBiuro = new Button("Dane");
    Button buttonWyslijPaczkiMiedzyOddzialami = new Button("Wyslij paczki");
    Button buttonWyszukajpaczke= new Button("Wyszukaj paczke");
    Button buttonWszystkiePaczki = new Button("Wszystkie paczki");
    Button buttonOdmowy = new Button("Zwrot Paczek");
    Button wyloguj = new Button("wyloguj");

    Set<Paczka> buffor = new HashSet<>();

    public PracownikBiuraView() {
        start();

        wyloguj.setId("przycisk_wyloguj");
        buttonDaneBiuro.setId("przycisk_klient");
        buttonWyslijPaczkiMiedzyOddzialami.setId("przycisk_klient");
        buttonWyszukajpaczke.setId("przycisk_klient");
        buttonWszystkiePaczki.setId("przycisk_klient");
        buttonOdmowy.setId("przycisk_klient");
        formularzIdentyfikator.setId("formularz");
        daneD.setId("formularz");

        buttonDaneBiuro.addClickListener(buttonClickEvent -> {
            daneD.removeAll();
            statusOdmowa.removeAll();
           identyfikator();
        });
        buttonOdmowy.addClickListener(buttonClickEvent -> {
            daneD.removeAll();
            odeslijOdmowy();

        });
        buttonWyslijPaczkiMiedzyOddzialami.addClickListener(buttonClickEvent -> {
            daneD.removeAll();
            formularzDoWyslaniaPaczek.removeAll();
            statusWyslana.removeAll();
            statusOdmowa.removeAll();
            wyslijPaczkiMiedzyOddzialami();
            mapaWszystkiePaczki.setVisible(false);
            formularzWyszukajPaczke.setVisible(false);
            formularzOdmowy.setVisible(false);

        });
        buttonWszystkiePaczki.addClickListener(buttonClickEvent -> {
            daneD.removeAll();
            statusOdmowa.removeAll();
            wyswietlWszystkiePaczki();
        });
        buttonWyszukajpaczke.addClickListener(buttonClickEvent -> {
            daneD.removeAll();
            statusOdmowa.removeAll();
            wyszukajPaczke();
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
        TextField textField = new TextField("Biuro:");
        textField.setId("name");
        textField.setReadOnly(true);
        textField.setValue(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size() - 1)).getIdBiuro() + " " + listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size() - 1)).getImie() + " " + listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size() - 1)).getNazwisko() + " ");
        textField.setReadOnly(true);
        Image image = new Image("https://i.ibb.co/fvYQ3YN/Logo-Dobre.png","d");
        image.setId("logokierownik");
        layout1.add(image,avatar, textField);
        layout1.add(wyloguj);
        add(layout1);

        layout.setId("layout");
        formularzWyszukajPaczke.setId("layout");
        layout.add(buttonWszystkiePaczki,buttonWyslijPaczkiMiedzyOddzialami,buttonOdmowy,buttonWyszukajpaczke,buttonDaneBiuro);
        add(layout);
        statusWyslana.setId("przyciskNaSrodek");
        statusOdmowa.setId("przyciskNaSrodek");

    }
    private void wyszukajPaczke(){
        formularzWyszukajPaczke.removeAll();

        Button wyszukaj = new Button("WYSZUKAJ");
        wyszukaj.setId("przycisk_biuro");
        TextField numer = new TextField();
//        Binder<Paczka> binder= new Binder<>(Paczka.class);
//        binder.forField(numer)
//                .asRequired("Musisz podać numer")
//                .withValidator(new RegexpValidator("Proszę podać poprawny numer", "[0-9]+")
//                )
//                .bind(Paczka::getNumer, Paczka::setNumer);

//        wyszukaj.setEnabled(false);

//        binder.addStatusChangeListener(
//                event -> wyszukaj.setEnabled(binder.isValid()));

        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button ok = new Button("OK", e -> notification.close());
        Span Label = new Span("Paczka o takim numerze nie istnieje.");
        notification.add(Label, ok);
        Label.getStyle().set("margin-right", "0.5rem");
        ok.getStyle().set("margin-right", "0.5rem");

//        Notification notification2 = new Notification();
//        notification2.setPosition(Notification.Position.MIDDLE);
//        notification2.addThemeVariants(NotificationVariant.LUMO_ERROR);
//        Button ok1 = new Button("OK", e -> notification2.close());
//        Span Label1 = new Span("Numer paczki musi być liczbą .");
//        notification2.add(Label1, ok1);
//        Label1.getStyle().set("margin-right", "0.5rem");
//        ok1.getStyle().set("margin-right", "0.5rem");


        wyszukaj.addClickListener(buttonClickEvent -> {
            int semafor = 0;
            for(int i = 0 ; i < listaPaczek.size() ; i ++ ){
                if (Integer.parseInt(numer.getValue()) == listaPaczek.get(i).getNumer()){
                    semafor = 1;
                    daneD.removeAll();
                    System.out.println("Wyswietlono wsyztkie informacje o paczce " +  listaPaczek.get(i).getNumer()+ "  przez " +listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());

                    TextField numerD = new TextField();
                    TextField typPaczkiD = new TextField();
                    TextField wagaD = new TextField();
                    TextField cenaD = new TextField();
                    TextField aktualnyStanD = new TextField();
                    TextField odbiorcaImieD = new TextField();
                    TextField odbiorcaNazwiskoD = new TextField();
                    TextField nadawcaImieD = new TextField();
                    TextField nadawcaNazwiskoD = new TextField();
                    TextField adresOdbiorcyD = new TextField();
                    TextField ubezpieczenieD = new TextField();
                    TextField dokumentyD = new TextField();
                    TextField pobranieD = new TextField();
                    TextField cybantD = new TextField();
                    TextField nadawczyOddzialD = new TextField();
                    TextField odbiorczyOddzialD = new TextField();
                    TextField szerokoscD = new TextField();
                    TextField dlugoscD = new TextField();
                    TextField wysokoscD = new TextField();
                    TextField idkOdbierajacegoD = new TextField();
                    TextField idkDostarczajacegoD = new TextField();



                    numerD.setValue(String.valueOf(listaPaczek.get(i).getNumer()));
                    typPaczkiD.setValue(listaPaczek.get(i).getTypPaczki());
                    wagaD.setValue(String.valueOf(listaPaczek.get(i).getWaga()));
                    cenaD.setValue(String.valueOf(listaPaczek.get(i).getCena()));
                    aktualnyStanD.setValue(listaPaczek.get(i).getAktualnyStan());
                    odbiorcaImieD.setValue(listaPaczek.get(i).getOdbiorcaImie());
                    odbiorcaNazwiskoD.setValue(listaPaczek.get(i).getOdbiorcaNazwisko());
                    nadawcaImieD.setValue(listaPaczek.get(i).getNadawcaImie());
                    nadawcaNazwiskoD.setValue(listaPaczek.get(i).getNadawcaNazwisko());
                    adresOdbiorcyD.setValue(listaPaczek.get(i).getAddresOdbiorcy());
                    ubezpieczenieD.setValue(listaPaczek.get(i).getUbezpieczenie());
                    dokumentyD.setValue(listaPaczek.get(i).getDokumenty());
                    pobranieD.setValue(String.valueOf(listaPaczek.get(i).getPobranie()));
                    cybantD.setValue(String.valueOf(listaPaczek.get(i).isCybant()));
                    nadawczyOddzialD.setValue(listaPaczek.get(i).getNadawczyOddzial());
                    odbiorczyOddzialD.setValue(listaPaczek.get(i).getOdbiorczyOddzial());
                    szerokoscD.setValue(String.valueOf(listaPaczek.get(i).getSzerokosc()));
                    wysokoscD.setValue(String.valueOf(listaPaczek.get(i).getWysokosc()));
                    dlugoscD.setValue(String.valueOf(listaPaczek.get(i).getDlugosc()));

                    if (listaPaczek.get(i).getIdKurieraDostarczajacego() == null){
                        idkDostarczajacegoD.setValue("----");
                    }else{
                        idkDostarczajacegoD.setValue(listaPaczek.get(i).getIdKurieraDostarczajacego());
                    }
                    if (listaPaczek.get(i).getIdKurieraDostarczajacego() == null){
                        idkOdbierajacegoD.setValue("----");
                    }else {
                        idkOdbierajacegoD.setValue(listaPaczek.get(i).getIdKurieraOdbierajacego());

                    }

                    daneD.addFormItem(numerD, "Numer  ");
                    daneD.addFormItem(typPaczkiD,"Typ paczki");
                    daneD.addFormItem(wagaD,"Waga ");
                    daneD.addFormItem(cenaD,"Cena ");
                    daneD.addFormItem(aktualnyStanD ,"Status ");
                    daneD.addFormItem(odbiorcaImieD,"Imie odbiorcy ");
                    daneD.addFormItem(odbiorcaNazwiskoD,"Nazwisko odbiorcy ");
                    daneD.addFormItem(nadawcaImieD,"Imie nadawcy ");
                    daneD.addFormItem(nadawcaNazwiskoD,"Nazwisko nadawcy ");
                    daneD.addFormItem(adresOdbiorcyD,"Adres odbiorcy ");
                    daneD.addFormItem(ubezpieczenieD,"Ubezpieczenie ");
                    daneD.addFormItem(dokumentyD,"Dokumenty ");
                    daneD.addFormItem(pobranieD,"Pobranie ");
                    daneD.addFormItem(cybantD ,"Czy jestem cybantem ");
                    daneD.addFormItem(nadawczyOddzialD,"Oddzial nadawcy ");
                    daneD.addFormItem(odbiorczyOddzialD,"Oddzial odbiorcy ");
                    daneD.addFormItem(szerokoscD,"Szerokosc ");
                    daneD.addFormItem(dlugoscD,"Dlugosc ");
                    daneD.addFormItem(wysokoscD,"Wysokosc ");
                    daneD.addFormItem(idkDostarczajacegoD,"ID kuriera dost. ");
                    daneD.addFormItem(idkOdbierajacegoD,"ID kuriera odb. ");




                    numerD.setReadOnly(true);
                    typPaczkiD.setReadOnly(true);
                    wagaD.setReadOnly(true);
                    cenaD.setReadOnly(true);
                    aktualnyStanD.setReadOnly(true);
                    odbiorcaImieD.setReadOnly(true);
                    odbiorcaNazwiskoD.setReadOnly(true);
                    nadawcaImieD.setReadOnly(true);
                    nadawcaNazwiskoD.setReadOnly(true);
                    adresOdbiorcyD.setReadOnly(true);
                    ubezpieczenieD.setReadOnly(true);
                    dokumentyD.setReadOnly(true);
                    pobranieD.setReadOnly(true);
                    cybantD.setReadOnly(true);
                    nadawczyOddzialD.setReadOnly(true);
                    odbiorczyOddzialD.setReadOnly(true);
                    szerokoscD.setReadOnly(true);
                    dlugoscD.setReadOnly(true);
                    wysokoscD.setReadOnly(true);
                    idkDostarczajacegoD.setReadOnly(true);
                    idkOdbierajacegoD.setReadOnly(true);

                    numer.setValue("");
                    add(daneD);
                    break;
                }
            }
            if(semafor == 0){
                notification.open();
            }
        });

        formularzWyszukajPaczke.add(numer,wyszukaj);
        add(formularzWyszukajPaczke);
        formularzDoWyslaniaPaczek.setVisible(false);
        statusWyslana.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzWyszukajPaczke.setVisible(true);
        formularzIdentyfikator.setVisible(false);
        formularzOdmowy.setVisible(false);
    }
    private void wyslijPaczkiMiedzyOddzialami(){
        //System.out.println("sssss");
        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            if (listaPaczek.get(i).getAktualnyStan().equals("ODEBRANA")){
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

        //filtr typPaczki
        Grid.Column<Paczka>  idColumn = grid.
                addColumn(Paczka::getIdKurieraOdbierajacego).setHeader("ID kuriera odb");


        //filtr oddzial nadawcy
        Grid.Column<Paczka>  onColumn = grid.
                addColumn(Paczka::getNadawczyOddzial).setHeader("oddzial nadawcy");

        //filtr oddzial odbiorcy
        Grid.Column<Paczka>  odColumn = grid.
                addColumn(Paczka::getOdbiorczyOddzial).setHeader("oddzial odbiorcy");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        Button dodaj = new Button("WYSLIJ MIEDZY ODDZIALAMI");
        dodaj.setId("przycisk_klient");

        dodaj.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
                    if (a == listaPaczek.get(i).getNumer()){
                        System.out.println("Wyslano paczke miedzy odzzialami " +  listaPaczek.get(i).getNumer()+ " przez " +listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());

                        listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).wyslijPaczkeMiedzyOddzialami(listaPaczek.get(i));
                        //System.out.println("zaz");
                    }
                }
            }
            formularzDoWyslaniaPaczek.removeAll();
            statusWyslana.removeAll();
            wyslijPaczkiMiedzyOddzialami();
        });
        statusWyslana.add(dodaj);

        HeaderRow filterRow = grid.appendHeaderRow();


        //filtr id kuriera odbierajacego

        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKurieraOdbierajacego(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr oddizal odbiorcy

        TextField odField = new TextField();


        odField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorczyOddzial(),
                        odField.getValue())));
        odField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(odColumn).setComponent(odField);
        odField.setSizeFull();
        odField.setPlaceholder("...");

        //filtr oddizal nadawcy

        TextField onField = new TextField();


        onField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawczyOddzial(),
                        onField.getValue())));
        onField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(onColumn).setComponent(onField);
        onField.setSizeFull();
        onField.setPlaceholder("...");

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

        formularzDoWyslaniaPaczek.add(grid);
        add(formularzDoWyslaniaPaczek);
        add(statusWyslana);
        formularzDoWyslaniaPaczek.setVisible(true);
        statusWyslana.setVisible(true);
        formularzIdentyfikator.setVisible(false);
        formularzOdmowy.setVisible(false);
    }
    private void odeslijOdmowy(){
        //System.out.println("sssss");
        formularzOdmowy.removeAll();
        statusOdmowa.removeAll();
        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            if (listaPaczek.get(i).getAktualnyStan().equals("ODMOWA")){
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

        //filtr idOdbierajacego
        Grid.Column<Paczka>  idColumn = grid.
                addColumn(Paczka::getIdKurieraOdbierajacego).setHeader("ID kuriera odb");


        //filtr oddzial nadawcy
        Grid.Column<Paczka>  onColumn = grid.
                addColumn(Paczka::getNadawczyOddzial).setHeader("oddzial nadawcy");

        //filtr oddzial odbiorcy
        Grid.Column<Paczka>  odColumn = grid.
                addColumn(Paczka::getOdbiorczyOddzial).setHeader("oddzial odbiorcy");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        grid.asMultiSelect().addValueChangeListener(event -> {
            Set<Paczka> x = new HashSet<>(grid.getSelectedItems());
            buffor = x;
        });


        Button dodaj = new Button("ZWROT");
        dodaj.setId("przycisk_klient");

        dodaj.addClickListener(buttonClickEvent -> {
            Iterator<Paczka> someNumbersIterator = buffor.iterator();
            while(someNumbersIterator.hasNext()) {
                int a = someNumbersIterator.next().getNumer();
                for (int i = 0; i < listaPaczek.size(); i++) {
                    if (a == listaPaczek.get(i).getNumer()) {
                        System.out.println("Odeslano paczke" +  listaPaczek.get(i).getNumer()+ " do nadawcy przez " +listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());

                        listaPracownikowBiura.get(aktualnyIteratorBiuro.size()-1).zrobZwrot(listaPaczek.get(i));
                        String odbiorcaAdresLokalny = "---";

                        for (int j = 0; j < listaKlientow.size(); j++) {
                            if (listaPaczek.get(i).getIdNadawcy().equals(listaKlientow.get(j).getIdKlienta())) {
                                odbiorcaAdresLokalny = listaKlientow.get(j).getAdres();
                            }
                        }
                        Paczka cybancikZwrocik = new Paczka(listaPaczek.get(i).getWaga(), listaPaczek.get(i).getWysokosc(),
                                listaPaczek.get(i).getSzerokosc(), listaPaczek.get(i).getDlugosc(), odbiorcaAdresLokalny,
                                listaPaczek.get(i).getNadawcaNazwisko(),listaPaczek.get(i).odbiorcaNazwisko,
                                listaPaczek.get(i).getNadawcaImie(), listaPaczek.get(i).getOdbiorcaImie(),
                                listaPaczek.get(i).getUbezpieczenie(), listaPaczek.get(i).getDokumenty(),
                                listaPaczek.get(i).getPobranie(), listaPaczek.get(i).getOdbiorczyOddzial(),
                                listaPaczek.get(i).getNadawczyOddzial());
                        cybancikZwrocik.oddzialy.setOdbiorczyZwrot(listaPaczek.get(i).getNadawczyOddzial());
                        cybancikZwrocik.oddzialy.setNadawczyZwrot(listaPaczek.get(i).getOdbiorczyOddzial());
                        cybancikZwrocik.setOdbiorczyOddzial(listaPaczek.get(i).getNadawczyOddzial());
                        cybancikZwrocik.setNadawczyOddzial(listaPaczek.get(i).getOdbiorczyOddzial());
                        listaPaczek.add(cybancikZwrocik);
                    }
                }
            }
            formularzOdmowy.removeAll();
            statusOdmowa.removeAll();
            odeslijOdmowy();
        });
        statusOdmowa.add(dodaj);

        HeaderRow filterRow = grid.appendHeaderRow();


        //filtr id kuriera odbierajacego

        TextField idField = new TextField();


        idField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getIdKurieraOdbierajacego(),
                        idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(idColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("...");

        //filtr oddizal odbiorcy

        TextField odField = new TextField();


        odField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getOdbiorczyOddzial(),
                        odField.getValue())));
        odField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(odColumn).setComponent(odField);
        odField.setSizeFull();
        odField.setPlaceholder("...");

        //filtr oddizal nadawcy

        TextField onField = new TextField();


        onField.addValueChangeListener(event -> dataProvider.addFilter(
                person -> StringUtils.containsIgnoreCase(person.getNadawczyOddzial(),
                        onField.getValue())));
        onField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(onColumn).setComponent(onField);
        onField.setSizeFull();
        onField.setPlaceholder("...");

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

        formularzOdmowy.add(grid);
        add(formularzOdmowy);
        add(statusOdmowa);

        formularzDoWyslaniaPaczek.setVisible(false);
        statusWyslana.setVisible(false);
        statusOdmowa.setVisible(true);
        mapaWszystkiePaczki.setVisible(false);
        formularzWyszukajPaczke.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzOdmowy.setVisible(true);
    }
    private void identyfikator(){
        formularzIdentyfikator.removeAll();
        System.out.println("Wyswietlono dane pracownika biura " +listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());

        TextField idBiuro = new TextField();
        TextField haslo = new TextField();
        TextField imie = new TextField();
        TextField nazwisko = new TextField();
        TextField pensja = new TextField();
        TextField stazFirmowy = new TextField();

        listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).obliczPensje();

        idBiuro.setValue(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());
        haslo.setValue(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getHaslo());
        imie.setValue(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getImie());
        nazwisko.setValue(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getNazwisko());
        pensja.setValue(String.valueOf(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getPensja()));
        stazFirmowy.setValue(String.valueOf(listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getStazFirmowy()));

        idBiuro.setReadOnly(true);
        haslo.setReadOnly(true);
        imie.setReadOnly(true);
        nazwisko.setReadOnly(true);
        pensja.setReadOnly(true);
        stazFirmowy.setReadOnly(true);

        formularzIdentyfikator.addFormItem(imie ,"Imie ");
        formularzIdentyfikator.addFormItem(nazwisko ,"Nazwisko ");
        formularzIdentyfikator.addFormItem(idBiuro ,"ID biuro ");
        formularzIdentyfikator.addFormItem(haslo ,"Haslo ");
        formularzIdentyfikator.addFormItem(pensja ,"Pensja ");
        formularzIdentyfikator.addFormItem(stazFirmowy ,"Staz Firmowy ");

        add(formularzIdentyfikator);

        formularzDoWyslaniaPaczek.setVisible(false);
        statusWyslana.setVisible(false);
        statusOdmowa.setVisible(false);
        mapaWszystkiePaczki.setVisible(false);
        formularzWyszukajPaczke.setVisible(false);
        formularzIdentyfikator.setVisible(true);
        formularzOdmowy.setVisible(false);
    }
    private void wyswietlWszystkiePaczki(){
        //update();
        mapaWszystkiePaczki.removeAll();

        System.out.println("Wyswietlono wsyzstkie paczki przez " +listaPracownikowBiura.get(aktualnyIteratorBiuro.get(aktualnyIteratorBiuro.size()-1)).getIdBiuro());

        List<Paczka> q = new ArrayList<>();
        for(int i = 0 ; i < listaPaczek.size() ; i++ ) {
            q.add(listaPaczek.get(i));
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

        //filtr oddzial nadawcy
        Grid.Column<Paczka>  odColumn = grid.
                addColumn(Paczka::getNadawczyOddzial).setHeader("oddział nadawcy");

        //filtr id odbierajacy
        Grid.Column<Paczka> idkoColumn = grid.
                addColumn(Paczka::getIdKurieraOdbierajacego).setHeader("kurier odb.");

        //filtr id nadawcy
        Grid.Column<Paczka>  idnColumn = grid.
                addColumn(Paczka::getIdNadawcy).setHeader("nadawca");


        //filtr oddzial odbierajcy
        Grid.Column<Paczka>  ooColumn = grid.
                addColumn(Paczka::getOdbiorczyOddzial).setHeader("oddział odbiorcy");



        //filtr id dostarczajacy
        Grid.Column<Paczka>  idkdColumn = grid.
                addColumn(Paczka::getIdKurieraDostarczajacego).setHeader("kurier dost.");

        //filtr id odbiorcy
        Grid.Column<Paczka>  idoColumn = grid.
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

        formularzDoWyslaniaPaczek.setVisible(false);
        statusWyslana.setVisible(false);
        mapaWszystkiePaczki.setVisible(true);
        statusOdmowa.setVisible(false);
        formularzWyszukajPaczke.setVisible(false);
        formularzIdentyfikator.setVisible(false);
        formularzOdmowy.setVisible(false);
    }
}