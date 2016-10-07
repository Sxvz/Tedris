package sxvz.tedris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.logic.Vapaustarkastaja;

/**
 * Luokka, joka huolehtii näppäin-inputtien kuuntelusta.
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Pelialue alue;
    private Pelilooppi looppi;
    private Vapaustarkastaja tarkastaja;
    private Paivitettava paivitettava;

    /**
     * Konstruktori, joka luo kuuntelijalle tarvittavat yhteydet.
     * 
     * @param alue Pelialue, jossa liikkuminen tapahtuu
     * @param looppi Pelilooppi, joka pausetetaan tarvittaessa
     * @param tarkastaja Vapaustarkastaja
     */
    public Nappaimistonkuuntelija(Pelialue alue, Pelilooppi looppi, Vapaustarkastaja tarkastaja) {
        this.alue = alue;
        this.looppi = looppi;
        this.tarkastaja = tarkastaja;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /**
     * Käsittelee näppäimistön painallukset ja liikuttaa aktiivista kokoelmaa
     * painetun näppäimen perusteella. Tarkistaa onko liikkuminen sallittua.
     * Päivittää ruudun liikkumisen jälkeen.
     * Q- ja E-näppäimet kääntävät aktiivista kokoelmaa.
     * A-, S, ja D-näppäimet liikuttelevat aktiivista kokoelmaa sivuille ja alas.
     * W-näppäin laittaa tauon päälle.
     * 
     * @param e Näppäimistöstä tuleva inputti
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (alue.getAktiivinenKokoelma() == null) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            pause();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            tarkistaJaLiikuta(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            tarkistaJaLiikuta(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            tarkistaJaLiikuta(Suunta.VASEN);
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            tarkistaJaKaanna(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            tarkistaJaKaanna(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void pause() {
        if (looppi.isRunning()) {
            looppi.stop();
        } else {
            looppi.setInitialDelay(100);
            looppi.start();
        }
    }

    private void tarkistaJaLiikuta(Suunta s) {
        if (tarkastaja.voikoKokoelmaLiikkua(alue.getAktiivinenKokoelma(), s)) {
            alue.getAktiivinenKokoelma().liiku(s);
        }
        paivitettava.paivita();
    }

    private void tarkistaJaKaanna(int kiertosuunta) {
        if (tarkastaja.voikoKokoelmaKaantya(alue.getAktiivinenKokoelma(), kiertosuunta)) {
            alue.getAktiivinenKokoelma().kaanny(kiertosuunta);
        }
        paivitettava.paivita();
    }
    
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }
}
