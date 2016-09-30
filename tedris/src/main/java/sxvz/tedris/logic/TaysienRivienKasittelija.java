package sxvz.tedris.logic;

import java.util.ArrayList;
import sxvz.tedris.domain.GeneerinenKokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;

public class TaysienRivienKasittelija {

    private Pelilooppi peli;

    public TaysienRivienKasittelija(Pelilooppi peli) {
        this.peli = peli;
    }

    public void kasitteleTaydetRivit() {
        ArrayList<Integer> taydetRivit = etsiTaydetRivit();

        if (!taydetRivit.isEmpty()) {
            tuhoaPalikatRivilta(taydetRivit);
            jaaKokoelmat();
            pudotaPalikat();
        }
    }

    private ArrayList<Integer> etsiTaydetRivit() {
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        int[] palikatRivilla = new int[peli.getPelialueenKorkeus()];

        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
            for (Palikka p : kokoelma.getPalikat()) {
                palikatRivilla[p.getY()]++;
            }
        }

        for (int i = 0; i < palikatRivilla.length; i++) {
            if (palikatRivilla[i] == peli.getPelialueenLeveys()) {
                taydetRivit.add(i);
            }
        }

        return taydetRivit;
    }

    private void jaaKokoelmat() {
        ArrayList<Palikkakokoelma> uudet = new ArrayList<>();
        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
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
        peli.setPalikat(uudet);
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
        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
            for (int i = 0; i < kokoelma.getPalikat().size(); i++) {
                if (taydetRivit.contains(kokoelma.getPalikat().get(i).getY())) {
                    kokoelma.getPalikat().remove(i);
                    i--;
                }
            }
        }
        ArrayList<Palikkakokoelma> tyhjat = new ArrayList<>();
        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
            if (kokoelma.getPalikat().isEmpty()) {
                tyhjat.add(kokoelma);
            }
        }
        peli.getPalikat().removeAll(tyhjat);
    }

    private void pudotaPalikat() {
        boolean jokinLiikkui = true;
        while (jokinLiikkui) {
            jokinLiikkui = false;
            for (Palikkakokoelma kokoelma : peli.getPalikat()) {
                if (peli.getVapaustarkastaja().voikoKokoelmaLiikkua(kokoelma, Suunta.ALAS)) {
                    kokoelma.liiku(Suunta.ALAS);
                    jokinLiikkui = true;
                }
            }
        }
    }
}
