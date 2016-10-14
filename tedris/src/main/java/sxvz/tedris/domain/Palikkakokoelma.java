package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Luokka, johon kaikki ei-abstraktit kokoelmat perustuvat.
 *
 * @see sxvz.tedris.domain.NelioKokoelma
 * @see sxvz.tedris.domain.PitkaKokoelma
 * @see sxvz.tedris.domain.GeneerinenKokoelma
 */
public abstract class Palikkakokoelma {

    protected ArrayList<Palikka> palikat;
    protected Color vari;
    protected int orientaatio;
    protected ArrayList<ArrayList<int[]>> kaantymisInfo;

    /**
     * Konstruktori oliomuutujien alustusta varten. Tätä konstruktoria käytetään
     * kaikissa tähän luokkaan pohjautuvissa luokissa.
     *
     * @param vari Kokoelmalle asetettava väri
     */
    public Palikkakokoelma(Color vari) {
        palikat = new ArrayList();
        this.vari = vari;
        orientaatio = 0;
        kaantymisInfo = new ArrayList<>();
    }

    /**
     * Metodi, joka liikuttaa koko kokoelmaa.
     * Ei tarkista onko tiellä jotakin.
     *
     * @param s Kertoo suunnan johon liikutaan
     */
    public void liiku(Suunta s) {
        for (Palikka palikka : palikat) {
            palikka.liiku(s);
        }
    }

    /**
     * Metodi, joka kääntää kokoelman kääntämisInfon ohjeiden mukaan.
     * Ei tarkista onko esteitä.
     * 
     * @param kiertosuunta Kertoo kumpaan suuntaan kokoelmaa käännetään
     */
    public void kaanny(int kiertosuunta) {
        orientaatio += kiertosuunta;
        if (orientaatio < 0) {
            orientaatio = 3;
        } else if (orientaatio > 3) {
            orientaatio = 0;
        }

        if (kaantymisInfo == null) {
            return;
        }

        int x = palikat.get(0).getX();
        int y = palikat.get(0).getY();

        for (int i = 1; i < 4; i++) {
            palikat.get(i).liiku(x + kaantymisInfo.get(orientaatio).get(i - 1)[0], y + kaantymisInfo.get(orientaatio).get(i - 1)[1]);

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
