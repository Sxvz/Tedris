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
        for (Palikka p : k.getPalikat()) {   
            if (!voikoLiikkua(k, p, s)) {
                return false;
            }
        }
        return true;
    }

    public boolean voikoLiikkua(Palikkakokoelma k, Palikka p, Suunta s) {
        int x = p.getX();
        int y = p.getY();

        if (s == Suunta.ALAS) {
            return voikoLiikkua(k, x, y + 1);
        } else if (s == Suunta.VASEN) {
            return voikoLiikkua(k, x - 1, y);
        } else if (s == Suunta.OIKEA) {
            return voikoLiikkua(k, x + 1, y);
        }
        return false;
    }

    public boolean voikoLiikkua(Palikkakokoelma liikkuvanKokoelma, int x, int y) {
        
        if (x <= -1 || x >= pelialueenLeveys || y <= -1 || y >= pelialueenKorkeus) {
            return false;
        }

        for (Palikkakokoelma palikkakokoelma : peli.getPalikat()) {
            if (palikkakokoelma.equals(liikkuvanKokoelma)) {
                continue;
            }
            for (Palikka palikka : palikkakokoelma.getPalikat()) {
                if (palikka.getX() == x && palikka.getY() == y) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public boolean voikoKokoelmaKaantya(Palikkakokoelma k, int kiertosuunta) {
        if (k.getKaantymisInfo() == null) {
            return false;
        }
        
        Palikka p;
        int haettavaKaantymisInfo;
        for (int i = 1; i < 4; i++) {
            p = k.getPalikat().get(i);
            haettavaKaantymisInfo = k.getOrientaatio() + kiertosuunta;
            
            if (haettavaKaantymisInfo > 3) {
                haettavaKaantymisInfo = 0;
            } else if (haettavaKaantymisInfo < 0) {
                haettavaKaantymisInfo = 3;
            }
            
            if (!voikoLiikkua(k,
                    k.getPalikat().get(0).getX() + k.getKaantymisInfo().get(haettavaKaantymisInfo).get(i - 1)[0],
                    k.getPalikat().get(0).getY() + k.getKaantymisInfo().get(haettavaKaantymisInfo).get(i - 1)[1])) {
                return false;
            }
        }
        
        return true;
    }
}
