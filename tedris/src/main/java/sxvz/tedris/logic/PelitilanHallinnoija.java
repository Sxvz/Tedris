package sxvz.tedris.logic;

import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.Tulos;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.gui.NimikirjaintenKysyja;

/**
 * Luokka, joka kykenee lopettamaan ja pausettaamaan nykyisen pelin sekä
 * aloittamaan uuden pyydettäessä. Lisaksi huolehtii, että kaikki tarvittavat
 * operaatiot suoritetaan peliä aloittaessa tai lopettaessa.
 *
 * @see sxvz.tedris.engine.Pelilooppi
 * @see sxvz.tedris.gui.LuovutaNapinKuuntelija
 */
public class PelitilanHallinnoija {

    private Pelilooppi looppi;
    private Pelialue alue;
    private Pisteenlaskenta laskenta;
    private Huipputulokset tulokset;
    private NimikirjaintenKysyja kysyja;
    private boolean peliKaynnissa;

    /**
     * Konstruktori, joka luo tarvittavat yhteydet.
     *
     * @param looppi Pelilooppi
     * @param alue Pelialue
     * @param laskenta Pisteitä laskeva luokka
     * @param tulokset Huipputuloksista huolehtiva luokka
     * @param kysyja Luokka, joka kysyy pelaajalta nimikirjaimia
     */
    public PelitilanHallinnoija(Pelilooppi looppi, Pelialue alue, Pisteenlaskenta laskenta, Huipputulokset tulokset, NimikirjaintenKysyja kysyja) {
        this.looppi = looppi;
        this.alue = alue;
        this.laskenta = laskenta;
        this.tulokset = tulokset;
        this.kysyja = kysyja;
        peliKaynnissa = false;
    }

    /**
     * Metodi, joka nollaa pelialueen ja pisteet sekä aloittaa uuden pelin
     * valitulla vaikeusasteella.
     *
     * @param viive Peliloopin looppausten välinen viive
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
     * Jos pelaaja pärjäsi hyvin, kysyy nimikirjaimia top 10 listaa varten.
     */
    public void lopetaPeli() {
        looppi.stop();
        peliKaynnissa = false;
        hoidaMahdollinenHuipputulos();
    }

    private void hoidaMahdollinenHuipputulos() {
        if (tulokset == null) {
            return;
        }
        int ansaitutPisteet = laskenta.getPisteet();
        if (tulokset.vertaaHuipputuloksiin(ansaitutPisteet)) {
            String nimikirjaimet = kysyja.kysyNimikirjaimia();
            if (nimikirjaimet == null) {
                return;
            }
            Tulos uusiTulos = new Tulos(nimikirjaimet, ansaitutPisteet);
            tulokset.talletaHuipputuloksiin(uusiTulos);
        }
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
