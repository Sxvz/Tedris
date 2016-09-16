
package sxvz.tedris.domain;

public class Debugkokoelma extends Palikkakokoelma {
    
    public Debugkokoelma() {
        super(null);
    }
    
    public void lisaaPalikka(Palikka p) {
        palikat.add(p);
    }
}
