package sxvz.tedris.logic;

import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Pelilooppi;

/**
 * Luokka, joka kykenee lopettamaan ja pausettaamaan nykyisen pelin sekä
 * aloittamaan uuden pyydettäessä.
 *
 * @see sxvz.tedris.engine.Pelilooppi
 */
public class PelitilanHallinnoija {

    private Pelilooppi looppi;
    private Pelialue alue;
    private Pisteenlaskenta laskenta;
    private boolean peliKaynnissa;

    /**
     * Konstruktori, joka luo tarvittavat yhteydet.
     *
     * @param looppi Pelilooppi
     * @param alue Pelialue
     * @param laskenta Pisteenlaskija
     */
    public PelitilanHallinnoija(Pelilooppi looppi, Pelialue alue, Pisteenlaskenta laskenta) {
        this.looppi = looppi;
        this.alue = alue;
        this.laskenta = laskenta;
        peliKaynnissa = false;
    }

    /**
     * Metodi, joka nollaa pelialueen ja pisteet sekä aloittaa uuden pelin
     * valitulla vaikeusasteella.
     *
     * @param viive Peliloopin looppeusten välinen viive
     */
    public void aloitaPeli(int viive) {
        alue.tyhjenna();
        laskenta.nollaa();
        looppi.setDelay(viive);
        looppi.start();
        peliKaynnissa = true;
    }

    /**
     * Metodi, joka lopettaa nykyisen pelin.
     */
    public void lopetaPeli() {
        looppi.stop();
        peliKaynnissa = false;
    }

    /**
     * Pausettaa tai jatkaa nykyistä peliä, jos peli on käynnissä.
     */
    public void pause() {
        if (peliKaynnissa) {
            if (looppi.isRunning()) {
                looppi.stop();
            } else {
                looppi.start();
            }
        }
    }

    /**
     * Kertoo pyöriikö Pelilooppi.
     *
     * @return Pyöriikö pelilooppi
     */
    public boolean pyoriikoLooppi() {
        return looppi.isRunning();
    }

    public boolean isPeliKaynnissa() {
        return peliKaynnissa;
    }

}
