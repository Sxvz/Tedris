
package sxvz.tedris.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Nappaimistonkuuntelija implements KeyListener {

    public Nappaimistonkuuntelija() {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
