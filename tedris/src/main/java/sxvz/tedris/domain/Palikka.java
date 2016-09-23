
package sxvz.tedris.domain;

import sxvz.tedris.engine.Pelilooppi;

public class Palikka {
    private int x;
    private int y;
    private Pelilooppi peli;
    
    public Palikka(int x, int y, Pelilooppi peli) {
        this.x = x;
        this.y = y;
        this.peli = peli;
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
