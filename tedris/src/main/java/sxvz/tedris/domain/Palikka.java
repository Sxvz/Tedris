
package sxvz.tedris.domain;

import sxvz.tedris.engine.Suunta;
import sxvz.tedris.logic.Tedris;

public class Palikka {
    private int x;
    private int y;
    private Tedris peli;
    
    public Palikka(int x, int y, Tedris peli) {
        this.x = x;
        this.y = y;
        this.peli = peli;
    }
    
    protected boolean voikoLiikkua(Suunta s) {
        if (s == Suunta.ALAS) {
            return voikoLiikkua(x, y + 1);
        } else if (s == Suunta.VASEN) {
            return voikoLiikkua(x - 1, y);
        } else if (s == Suunta.OIKEA) {
            return voikoLiikkua(x + 1, y);
        }
        return false;
    }
    
    protected boolean voikoLiikkua(int x, int y) {
        return peli.onkoVapaa(x, y);
    }
    
    protected void liiku(Suunta s) {
        if (s == Suunta.ALAS) {
            y++;
        } else if (s == Suunta.VASEN) {
            x--;
        } else if (s == Suunta.OIKEA) {
            x++;
        }
    }
    
    protected void liiku(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
