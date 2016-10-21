package sxvz.tedris.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import sxvz.tedris.domain.Tulos;

/**
 * Luokka, joka toimii rajapintana ohjelman ja huipputulostiedoston välillä.
 */
public class Huipputulokset {

    private Tiedostotyokalut tiedostotyokalut;
    private String erotin;

    /**
     * Konstruktori, joka luo luokalle työkalut tiedostonkäsittelyyn. Määrittää
     * huipputulostiedoston nimen ja tiedostossa käytetyn erottimen.
     *
     * @param nimi Huipputulostiedoston nimi
     */
    public Huipputulokset(String nimi) {
        tiedostotyokalut = new Tiedostotyokalut(new File(nimi));
        erotin = ";-;p";
    }

    /**
     * Lukee huipputulokset tiedostosta. Jos tiedosto on korruptoitunut se
     * tuhotaan ja tilalle luodaan uusi.
     *
     * @return Kymmenen huipputulosta
     */
    public Tulos[] lueHuipputulokset() {
        Tulos[] tulokset = new Tulos[10];
        ArrayList<String> tuloksetTiedostosta = tiedostotyokalut.lue();

        for (int i = 0; i < tulokset.length; i++) {
            if (i == tuloksetTiedostosta.size()) {
                break;
            }

            String rivi = tuloksetTiedostosta.get(i);
            String[] osat = rivi.split(erotin);

            if (tarkistaFormaatti(osat)) {
                tulokset[i] = new Tulos(osat[0], Integer.parseInt(osat[1]));
            } else {
                poistaJaAlustaTulostiedosto();
                return new Tulos[10];
            }
        }

        return tulokset;
    }

    private void poistaJaAlustaTulostiedosto() {
        tiedostotyokalut.poista();
        tiedostotyokalut.luoTiedostoJosEiOlemassa();
    }

    private boolean tarkistaFormaatti(String[] osat) {
        if (osat.length != 2 || osat[0].length() > 3) {
            return false;
        }

        try {
            if (Integer.parseInt(osat[1]) < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Vertaa pistemäärää huipputulosten pistemääriin.
     *
     * @param pisteet Verrattava pistemäärä
     * @return Oliko suurempi kuin jokin huipputuloksista
     */
    public boolean vertaaHuipputuloksiin(int pisteet) {
        Tulos[] tulokset = lueHuipputulokset();

        for (Tulos tulos : tulokset) {
            if (tulos == null) {
                return true;
            }

            if (tulos.getPisteet() < pisteet) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tallettaa uuden huipputuloksen tiedostoon. Pitää huolta, että tulokset
     * ovat suuruusjärjestyksessä. Varmistaa, että tiedostoon päätyy max 10
     * tulosta.
     *
     * @param uusiTulos Lisättävä tulos
     */
    public void talletaHuipputuloksiin(Tulos uusiTulos) {
        ArrayList<Tulos> tulokset = new ArrayList<>();
        ArrayList<String> kirjoitettavatTulokset = new ArrayList<>();
        Tulos[] tuloksetTiedostosta = lueHuipputulokset();
        tulokset.add(uusiTulos);

        for (Tulos tulos : tuloksetTiedostosta) {
            if (tulos != null) {
                tulokset.add(tulos);
            }
        }

        Collections.sort(tulokset);

        while (tulokset.size() > 10) {
            tulokset.remove(tulokset.size() - 1);
        }

        for (Tulos tulos : tulokset) {
            kirjoitettavatTulokset.add(tulos.getTekija() + erotin + tulos.getPisteet());
        }

        tiedostotyokalut.kirjoita(kirjoitettavatTulokset);
    }
    
    /**
     * Tuhoaa pistetiedoston ja luo uuden.
     * Käytetään, kun pistetiedosto halutaan resetoida.
     */
    public void resetoi() {
        poistaJaAlustaTulostiedosto();
    }

    /**
     * Poistaa pistetiedoston.
     */
    public void poista() {
        tiedostotyokalut.poista();
    }

    public String getErotin() {
        return erotin;
    }

}
