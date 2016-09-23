package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Palikkakokoelma {

    protected ArrayList<Palikka> palikat;
    protected Color vari;

    public Palikkakokoelma(Color vari) {
        palikat = new ArrayList();
        this.vari = vari;
    }

    public void liiku(Suunta s) {
        for (Palikka palikka : palikat) {
            palikka.liiku(s);
        }
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
