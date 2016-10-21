package sxvz.tedris.domain;

/**
 * Olio, joka säilyttää pistemäärän ja sen ansaitsijan.
 * 
 * @see sxvz.tedris.logic.Huipputulokset
 */
public class Tulos implements Comparable<Tulos> {

    private String tekija;
    private int pisteet;

    /**
     * Määrittää tekijän ja pistemäärän.
     * 
     * @param tekija Tuloksen tekijä
     * @param pisteet Tulokseen liittyvät pisteet
     */
    public Tulos(String tekija, int pisteet) {
        this.tekija = tekija;
        this.pisteet = pisteet;
    }

    public String getTekija() {
        return tekija;
    }

    public int getPisteet() {
        return pisteet;
    }
    
    @Override
    public String toString() {
        return tekija + " " + pisteet;
    }

    @Override
    public int compareTo(Tulos t) {
        return t.getPisteet() - this.pisteet;
    }
}
