
package sxvz.tedris.domain;

public class Palikka {
    private int x;
    private int y;
    
    public Palikka(int x, int y) {
        this.x = x;
        this.y = y;
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
    
    protected void liiku(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
