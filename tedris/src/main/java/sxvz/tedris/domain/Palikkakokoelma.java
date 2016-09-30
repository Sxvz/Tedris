package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Luokka, johon kaikki ei-abstraktit kokoelmat perustuvat.
 * 
 * @see sxvz.tedris.domain.NelioPalikka
 * @see sxvz.tedris.domain.PitkaPalikka
 * @see sxvz.tedris.domain.GeneerinenKokoelma
 */
public abstract class Palikkakokoelma {
    protected ArrayList<Palikka> palikat;
    protected Color vari;
    protected int orientaatio;
    protected ArrayList<ArrayList<int[]>> kaantymisInfo;

    /**
     * Konstruktori oliomuutujien alustusta varten.
     * Tätä konstruktoria käytetään kaikissa tähän pohjautuvissa luokissa.
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
     * Metodi, joka huolehtii kokoelman orientaatiosta.
     * Suoritetaan muissa kokoelmissa ennen itse kokoelman kääntävää osaa.
     * 
     * @param kiertosuunta Kertoo käännetäänkö kokoelmaa vasta- vai myötäpäivään
     * 
     * @see sxvz.tedris.domain.PitkaPalikka
     */
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
