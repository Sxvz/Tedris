
package sxvz.tedris.logic;

import java.util.ArrayList;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Pelilooppi;

public class TaysienRivienTunnistaja {
    private Pelilooppi peli;
    
    public TaysienRivienTunnistaja(Pelilooppi peli) {
        this.peli = peli;
    }
    
    public ArrayList<Integer> etsiTaydetRivit() {
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        int[] palikatRivilla = new int[peli.getPelialueenKorkeus()];
        
        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
            for (Palikka p : kokoelma.getPalikat()) {
                palikatRivilla[p.getY()]++;
            }
        }
        
        for (int i = 0; i < palikatRivilla.length; i++) {
            if (palikatRivilla[i] == peli.getPelialueenLeveys()) {
                taydetRivit.add(i);
            }
        }
        
        return taydetRivit;
    }
}
