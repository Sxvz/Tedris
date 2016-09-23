package sxvz.tedris.logic;

import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;

public class Vapaustarkastaja {

    private Pelilooppi peli;
    private int pelialueenKorkeus;
    private int pelialueenLeveys;

    public Vapaustarkastaja(Pelilooppi peli) {
        this.peli = peli;
        pelialueenKorkeus = peli.getPelialueenKorkeus();
        pelialueenLeveys = peli.getPelialueenLeveys();
    }

    public boolean voikoKokoelmaLiikkua(Palikkakokoelma k, Suunta s) {
        boolean vapaata = false;
        
        for (Palikka p : k.getPalikat()) {
            int x = p.getX();
            int y = p.getY();

            if (s == Suunta.ALAS) {
                vapaata = voikoLiikkua(x, y + 1);
            } else if (s == Suunta.VASEN) {
                vapaata = voikoLiikkua(x - 1, y);
            } else if (s == Suunta.OIKEA) {
                vapaata = voikoLiikkua(x + 1, y);
            }
            
            if (!vapaata) {
                return false;
            }
        }
        return vapaata;
    }

    public boolean voikoLiikkua(Palikka p, Suunta s) {
        int x = p.getX();
        int y = p.getY();

        if (s == Suunta.ALAS) {
            return voikoLiikkua(x, y + 1);
        } else if (s == Suunta.VASEN) {
            return voikoLiikkua(x - 1, y);
        } else if (s == Suunta.OIKEA) {
            return voikoLiikkua(x + 1, y);
        }
        return false;
    }

    public boolean voikoLiikkua(int x, int y) {
        return onkoVapaa(x, y);
    }

    private boolean onkoVapaa(int x, int y) {

        if (x <= -1 || x > pelialueenLeveys || y <= -1 || y > pelialueenKorkeus) {
            return false;
        }

        for (Palikkakokoelma palikkakokoelma : peli.getPalikat()) {
            for (Palikka palikka : palikkakokoelma.getPalikat()) {
                if (palikka.getX() == x && palikka.getY() == y) {
                    return false;
                }
            }
        }

        return true;
    }
}
