package sxvz.tedris.domain;

import java.awt.Color;

/**
 * Palikkakokoelma, joihin ei-aktiivisten kokoelmien palikat päätyvät, kun
 * niiden ei tarvitse enää tehdä mitään muuta kuin olla olemassa.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 * @see sxvz.tedris.logic.TaysienRivienKasittelija#kasitteleTaydetRivit()
 */
public class GeneerinenKokoelma extends Palikkakokoelma {

    /**
     * Peruskonstruktori, joka luo tyhjän kokoelman.
     * 
     * @param vari Alkuperäisestä kokoelmasta saatava väri
     */
    public GeneerinenKokoelma(Color vari) {
        super(vari);
    }

    /**
     * Metodi, joka mahdollistaa palikoiden helpon lisäämisen.
     * 
     * @param p Lisättävä palikka
     */
    public void lisaaPalikka(Palikka p) {
        palikat.add(p);
    }
}
