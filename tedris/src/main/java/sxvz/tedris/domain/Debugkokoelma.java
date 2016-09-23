
package sxvz.tedris.domain;

import java.awt.Color;

public class Debugkokoelma extends Palikkakokoelma {
    
    public Debugkokoelma() {
        super(Color.RED);
    }
    
    public Debugkokoelma(Palikka p, Palikka p2) {
        super(Color.RED);
        palikat.add(p);
        palikat.add(p2);
    }
    
    public void lisaaPalikka(Palikka p) {
        palikat.add(p);
    }
    
    public void rivinLevyinenPalikka(int y) {
        palikat.clear();
        int i = 0;
        while (i < 20) {
            palikat.add(new Palikka(i, y));
            i++;
        }
    }
}
