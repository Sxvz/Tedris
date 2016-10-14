package sxvz.tedris.logic;

import java.util.ArrayList;

/**
 * Pisteenlaskennan hoitava luokka.
 */
public class Pisteenlaskenta {

    private int pelialueenLeveys;
    private int vaikeusaste;
    private int pisteet;
    private int lisattavatPisteet;

    /**
     * Luo uuden pisteytysolion.
     * 
     * @param pelialueenLeveys Pelialueen leveys, josta päätellään rivillä olevien palikoiden määrä 
     */
    public Pisteenlaskenta(int pelialueenLeveys) {
        this.pelialueenLeveys = pelialueenLeveys;
        this.pisteet = 0;
        this.lisattavatPisteet = 0;
    }

    /**
     * Metodi joka lisää pisteitä pelaajan saldoon suorituksen mukaan.
     * Jokainen palikka on 100:n pisteen arvoinen.
     * Palikoista saadut pisteet kerrotaan tuhottujen rivien määrällä ja
     * vaikeusasteesta riippuvalla kertoimella.
     * 
     * @param pisteytettavatRivit Tuhotut rivit, joista annetaan pisteitä
     */
    public void pisteyta(ArrayList<Integer> pisteytettavatRivit) {
        int kerrallaTuhotutRivit = pisteytettavatRivit.size();
        lisattavatPisteet = 0;
        int vaikeusasteKerroin = 1;

        if (vaikeusaste == 1) {
            vaikeusasteKerroin = 2;
        } else if (vaikeusaste == 2) {
            vaikeusasteKerroin = 5;
        }

        for (int i = 0; i < pisteytettavatRivit.size(); i++) {
            lisattavatPisteet += pelialueenLeveys * 100 * kerrallaTuhotutRivit * vaikeusasteKerroin;
        }

        pisteet += lisattavatPisteet;
    }
    
    /**
     * Nollaa pistelaskurin.
     */
    public void nollaa() {
        pisteet = 0;
        lisattavatPisteet = 0;
    }

    public int getPisteet() {
        return pisteet;
    }

    public int getLisattavatPisteet() {
        return lisattavatPisteet;
    }

    public void setVaikeusaste(int vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
    }

}
