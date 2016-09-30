package sxvz.tedris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.logic.Vapaustarkastaja;

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelilooppi peli;

    public Nappaimistonkuuntelija(Pelilooppi peli) {
        this.peli = peli;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (peli.getAktiivinenPalikka() == null) {
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
        if (peli.isPeliKaynnissa()) {
            peli.setPeliKaynnissa(false);
        } else {
            peli.setPeliKaynnissa(true);
            peli.setInitialDelay(100);
            peli.start();
        }
    }

    private void tarkistaJaLiikuta(Suunta s) {
        if (peli.getVapaustarkastaja().voikoKokoelmaLiikkua(peli.getAktiivinenPalikka(), s)) {
            peli.getAktiivinenPalikka().liiku(s);
        }
    }

    private void tarkistaJaKaanna(int kiertosuunta) {
        if (peli.getVapaustarkastaja().voikoKokoelmaKaantya(peli.getAktiivinenPalikka(), kiertosuunta)) {
            peli.getAktiivinenPalikka().kaanny(kiertosuunta);
        }
    }
}
