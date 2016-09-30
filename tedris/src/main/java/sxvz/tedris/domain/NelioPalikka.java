package sxvz.tedris.domain;

import java.awt.Color;

/**
 * Neliön muotoinen palikka, joka ei käänny.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 */
public class NelioPalikka extends Palikkakokoelma {

    /**
     * Konstruktori kokoelman luontia varten.
     * 
     * @param vari Pelin arpoma väri
     * @param x Pääpalikan x-koordinaatti
     * @param y Pääpalikan y-koordinaatti
     * 
     * @see sxvz.tedris.logic.AktiivisenPalikanHallinnoija#hallinnoiAktiivistaPalikkaa(sxvz.tedris.domain.Palikkakokoelma) 
     */
    public NelioPalikka(Color vari, int x, int y) {
        super(vari);
        palikat.add(new Palikka(x, y));
        palikat.add(new Palikka(x + 1, y));
        palikat.add(new Palikka(x, y + 1));
        palikat.add(new Palikka(x + 1, y + 1));
        kaantymisInfo = null;

    }

    /**
     * Metodi, joka varmistaa palikan kääntymättömyyden.
     * 
     * @param kiertosuunta Ei tee mitään tässä palikassa
     */
    @Override
    public void kaanny(int kiertosuunta) {
    }
}
