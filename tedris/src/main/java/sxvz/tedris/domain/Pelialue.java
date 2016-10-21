package sxvz.tedris.domain;

import java.util.ArrayList;

/**
 * Luokka, joka sisältää tiedon pelissä olevista kokoelmaista.
 */
public class Pelialue {

    private int leveys;
    private int korkeus;
    private ArrayList<Palikkakokoelma> kokoelmat;
    private Palikkakokoelma aktiivinenKokoelma;
    private Palikkakokoelma seuraavaKokoelma;

    /**
     * Konstruktori, joka luo annetun kokoisen pelialueen.
     *
     * @param leveys Alueen leveys
     * @param korkeus Alueen korkeus
     */
    public Pelialue(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        kokoelmat = new ArrayList<>();
    }

    /**
     * Metodi kokoelmien lisäämistä varten.
     *
     * @param k Lisättävä kokoelma
     */
    public void lisaaKokoelma(Palikkakokoelma k) {
        kokoelmat.add(k);
    }

    /**
     * Tyhjentää pelialueen uuden pelin aloitusta varten.
     */
    public void tyhjenna() {
        kokoelmat.clear();
        aktiivinenKokoelma = null;
        seuraavaKokoelma = null;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public ArrayList<Palikkakokoelma> getKokoelmat() {
        return kokoelmat;
    }

    public Palikkakokoelma getAktiivinenKokoelma() {
        return aktiivinenKokoelma;
    }

    public void setAktiivinenKokoelma(Palikkakokoelma aktiivinenKokoelma) {
        this.aktiivinenKokoelma = aktiivinenKokoelma;
    }

    public void setKokoelmat(ArrayList<Palikkakokoelma> kokoelmat) {
        this.kokoelmat = kokoelmat;
    }

    public Palikkakokoelma getSeuraavaKokoelma() {
        return seuraavaKokoelma;
    }

    public void setSeuraavaKokoelma(Palikkakokoelma seuraavaKokoelma) {
        this.seuraavaKokoelma = seuraavaKokoelma;
    }

}
