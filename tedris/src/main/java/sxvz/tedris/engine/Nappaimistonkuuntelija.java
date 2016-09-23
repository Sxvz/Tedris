
package sxvz.tedris.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sxvz.tedris.domain.Suunta;

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
        if (peli.getNykyinenPalikka() == null) {
            return;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            peli.getNykyinenPalikka().liiku(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            peli.getNykyinenPalikka().liiku(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            peli.getNykyinenPalikka().liiku(Suunta.VASEN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
