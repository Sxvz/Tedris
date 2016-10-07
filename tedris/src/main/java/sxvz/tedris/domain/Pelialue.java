package sxvz.tedris.domain;

import java.util.ArrayList;

/**
 * Luokka, joka sisältää kaiken tiedon pelitilanteesta.
 */
public class Pelialue {

    private int leveys;
    private int korkeus;
    private ArrayList<Palikkakokoelma> kokoelmat;
    private Palikkakokoelma aktiivinenKokoelma;

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
    
    
}
