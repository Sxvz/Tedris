package sxvz.tedris.domain;

import java.awt.Color;

/**
 * Neliön muotoinen kokoelma, joka ei käänny.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 */
public class NelioKokoelma extends Palikkakokoelma {

    /**
     * Konstruktori kokoelman luontia varten.
     * 
     * @param vari Kokoelman väri
     * @param x Pääpalikan x-koordinaatti
     * @param y Pääpalikan y-koordinaatti
     * 
     * @see sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija 
     */
    public NelioKokoelma(Color vari, int x, int y) {
        super(vari);
        palikat.add(new Palikka(x, y));
        palikat.add(new Palikka(x + 1, y));
        palikat.add(new Palikka(x, y + 1));
        palikat.add(new Palikka(x + 1, y + 1));
        kaantymisInfo = null;

    }

}
