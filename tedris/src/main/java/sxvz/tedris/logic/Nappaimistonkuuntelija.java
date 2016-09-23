package sxvz.tedris.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelilooppi peli;
    private Vapaustarkastaja tarkastaja;

    public Nappaimistonkuuntelija(Pelilooppi peli) {
        this.peli = peli;
        tarkastaja = peli.getVapaustarkastaja();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (peli.getAktiivinenPalikka() == null) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pause();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tarkistaJaLiikuta(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tarkistaJaLiikuta(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tarkistaJaLiikuta(Suunta.VASEN);
        }
    }

    private void pause() {
        if (peli.isPeliKaynnissa()) {
            peli.setPeliKaynnissa(false);
        } else {
            peli.setPeliKaynnissa(true);
            peli.setInitialDelay(100);
            peli.start();
        }
    }

    private void tarkistaJaLiikuta(Suunta s) {
        if (tarkastaja.voikoKokoelmaLiikkua(peli.getAktiivinenPalikka(), s)) {
            peli.getAktiivinenPalikka().liiku(s);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
