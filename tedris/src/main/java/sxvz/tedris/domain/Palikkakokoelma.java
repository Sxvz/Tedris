package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Palikkakokoelma {

    protected ArrayList<Palikka> palikat;
    protected Color vari;
    protected int orientaatio;
    protected ArrayList<ArrayList<int[]>> kaantymisInfo;

    public Palikkakokoelma(Color vari) {
        palikat = new ArrayList();
        this.vari = vari;
        orientaatio = 0;
        kaantymisInfo = new ArrayList<>();
    }

    public void liiku(Suunta s) {
        for (Palikka palikka : palikat) {
            palikka.liiku(s);
        }
    }

    public void kaanny(int kiertosuunta) {
        orientaatio += kiertosuunta;
        if (orientaatio < 0) {
            orientaatio = 3;
        } else if (orientaatio > 3) {
            orientaatio = 0;
        }
    }
    
    public ArrayList<ArrayList<int[]>> getKaantymisInfo() {
        return kaantymisInfo;
    }

    public ArrayList<Palikka> getPalikat() {
        return palikat;
    }

    public Color getVari() {
        return vari;
    }

    public int getOrientaatio() {
        return orientaatio;
    }
    
}
