
package sxvz.tedris.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.logic.Vapaustarkastaja;

public class Nappaimistonkuuntelija implements KeyListener {
    private Pelilooppi peli;
    private Vapaustarkastaja tarkastaja;
    
    public Nappaimistonkuuntelija(Pelilooppi peli) {
        this.peli = peli;
        tarkastaja = peli.getTarkastaja();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (peli.getNykyinenPalikka() == null) {
            return;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tarkistaJaLiikuta(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tarkistaJaLiikuta(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tarkistaJaLiikuta(Suunta.VASEN);
        }
    }

    private void tarkistaJaLiikuta(Suunta s) {
        if (tarkastaja.voikoKokoelmaLiikkua(peli.getNykyinenPalikka(), s)) {
            peli.getNykyinenPalikka().liiku(s);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
