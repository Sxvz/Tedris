
package sxvz.tedris.domain;

import java.awt.Color;

/**
 * Ei-abstrakti toteutus Palikkakokoelmasta testaamista varten.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 */
public class Debugkokoelma extends Palikkakokoelma {
    
    /**
     * Konstruktori tyhjän testikokoelman luontiin.
     */
    public Debugkokoelma() {
        super(Color.RED);
        kaantymisInfo = null;
    }
    
    /**
     * Konstruktori kaksipalikkaisen testikokoelman luontiin.
     * 
     * @param p Kokoelmaan sijoitettavaa palikka
     * @param p2 Kokoelmaan sijoitettavaa palikka
     */
    public Debugkokoelma(Palikka p, Palikka p2) {
        this();
        palikat.add(p);
        palikat.add(p2);
    }
    
    /**
     * Mahdollistaa helpon palikoiden lisäämisen kokoelmaan luonnin jälkeen.
     * 
     * @param p lisättävä palikka
     */
    public void lisaaPalikka(Palikka p) {
        palikat.add(p);
    }
    
    /**
     * Poistaa kokoelman aiemman sisällön ja täytää sen uudestaan palikoilla,
     * jotka täyttävät kokonaisen rivin defaulttikokoisessa pelissä.
     * Täysin testejä varten.
     * 
     * @param y y-koordinaatti uusille palikoille
     */
    public void rivinLevyinenPalikka(int y) {
        palikat.clear();
        int i = 0;
        while (i < 20) {
            palikat.add(new Palikka(i, y));
            i++;
        }
    }
}
