package sxvz.tedris.logic;

import java.util.ArrayList;
import sxvz.tedris.domain.GeneerinenKokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Paivitettava;

/**
 * Luokka, joka etsii ja tuhoaa täydet rivit pyydettäessä.
 * 
 * @see sxvz.tedris.domain.Pelialue
 * @see sxvz.tedris.logic.Vapaustarkastaja
 */
public class TaysienRivienKasittelija implements Paivitettava {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;

    /**
     * Luo yhteyden pelialueeseen ja vapaustarkastajaan.
     * 
     * @param alue Pelialue
     * @param tarkastaja Vapaustarkastaja
     */
    public TaysienRivienKasittelija(Pelialue alue, Vapaustarkastaja tarkastaja) {
        this.alue = alue;
        this.tarkastaja = tarkastaja;
    }

    private void kasitteleTaydetRivit() {
        ArrayList<Integer> taydetRivit = etsiTaydetRivit();

        if (!taydetRivit.isEmpty()) {
            tuhoaPalikatRivilta(taydetRivit);
            jaaKokoelmat();
            pudotaKokoelmat();
        }
    }

    private ArrayList<Integer> etsiTaydetRivit() {
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        int[] palikatRivilla = new int[alue.getKorkeus()];

        for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
            for (Palikka p : kokoelma.getPalikat()) {
                palikatRivilla[p.getY()]++;
            }
        }

        for (int i = 0; i < palikatRivilla.length; i++) {
            if (palikatRivilla[i] == alue.getLeveys()) {
                taydetRivit.add(i);
            }
        }

        return taydetRivit;
    }

    private void jaaKokoelmat() {
        ArrayList<Palikkakokoelma> uudet = new ArrayList<>();
        for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
            GeneerinenKokoelma uusi1 = new GeneerinenKokoelma(kokoelma.getVari());
            GeneerinenKokoelma uusi2 = new GeneerinenKokoelma(kokoelma.getVari());
            uusi1.lisaaPalikka(kokoelma.getPalikat().get(0));
            for (int i = 1; i < kokoelma.getPalikat().size(); i++) {
                Palikka p = kokoelma.getPalikat().get(i);
                if (onkoPalikkaKokoelmanVieressa(uusi1, p)) {
                    uusi1.lisaaPalikka(p);
                } else {
                    uusi2.lisaaPalikka(p);
                }
            }
            uudet.add(uusi1);
            if (!uusi2.getPalikat().isEmpty()) {
                uudet.add(uusi2);
            }
        }
        alue.setKokoelmat(uudet);
    }

    private boolean onkoPalikkaKokoelmanVieressa(Palikkakokoelma k, Palikka p1) {
        for (Palikka p2 : k.getPalikat()) {
            if (p1.getX() == p2.getX() && (p1.getY() - 1 == p2.getY() || p1.getY() + 1 == p2.getY())) {
                return true;
            } else if (p1.getY() == p2.getY() && (p1.getX() - 1 == p2.getX() || p1.getX() + 1 == p2.getX())) {
                return true;
            }
        }
        return false;
    }

    private void tuhoaPalikatRivilta(ArrayList<Integer> taydetRivit) {
        for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
            for (int i = 0; i < kokoelma.getPalikat().size(); i++) {
                if (taydetRivit.contains(kokoelma.getPalikat().get(i).getY())) {
                    kokoelma.getPalikat().remove(i);
                    i--;
                }
            }
        }
        ArrayList<Palikkakokoelma> tyhjat = new ArrayList<>();
        for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
            if (kokoelma.getPalikat().isEmpty()) {
                tyhjat.add(kokoelma);
            }
        }
        alue.getKokoelmat().removeAll(tyhjat);
    }

    private void pudotaKokoelmat() {
        boolean jokinLiikkui = true;
        while (jokinLiikkui) {
            jokinLiikkui = false;
            for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
                if (tarkastaja.voikoKokoelmaLiikkua(kokoelma, Suunta.ALAS)) {
                    kokoelma.liiku(Suunta.ALAS);
                    jokinLiikkui = true;
                }
            }
        }
    }

    
    /**
     * Etsii täydet rivit.
     * Jos täysiä rivejä löytyy ne tuhotaan ja palikat jaetaan uusiin kokoelmiin.
     */
    @Override
    public void paivita() {
        kasitteleTaydetRivit();
    }
}
