
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
}
