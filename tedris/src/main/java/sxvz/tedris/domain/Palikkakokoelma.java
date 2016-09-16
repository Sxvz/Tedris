
package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;
import sxvz.tedris.engine.Suunta;

public abstract class Palikkakokoelma {
    protected ArrayList<Palikka> palikat;
    protected Color vari;
    
    public Palikkakokoelma(Color vari) {
        palikat = new ArrayList();
        this.vari = vari;
    }
    
    public boolean liiku(Suunta s) {
        boolean liikkuminenSallittu = false;
        for (Palikka palikka : palikat) {
            liikkuminenSallittu = palikka.voikoLiikkua(s);
            if (liikkuminenSallittu == false) {
                return false;
            }
        }
        
        if (liikkuminenSallittu) {
            for (Palikka palikka : palikat) {
                palikka.liiku(s);
            }
        }
        
        return liikkuminenSallittu;
    }
    
    public boolean kaanny() {
        return false;
    }

    public ArrayList<Palikka> getPalikat() {
        return palikat;
    }

    public Color getVari() {
        return vari;
    }
    
}
