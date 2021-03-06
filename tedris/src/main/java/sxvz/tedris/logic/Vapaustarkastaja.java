package sxvz.tedris.logic;

import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.Suunta;

/**
 * Huolehtii siitä, että kokoelmat eivät poistu pelialueelta tai mene päällekkäin.
 * 
 * @see sxvz.tedris.domain.Pelialue
 */
public class Vapaustarkastaja {

    private Pelialue alue;
    private int pelialueenKorkeus;
    private int pelialueenLeveys;

    /**
     * Antaa yhteyden pelialueeseen.
     * 
     * @param alue Pelialue, jonka perusteella tarkistukset suoritetaan
     */
    public Vapaustarkastaja(Pelialue alue) {
        this.alue = alue;
        pelialueenKorkeus = alue.getKorkeus();
        pelialueenLeveys = alue.getLeveys();
    }

    /**
     * Kertoo voiko kokonainen kokoelma liikkua haluttuun suuntaan.
     * 
     * @param k Liikutettava kokoelma
     * @param s Haluttu suunta
     * 
     * @return Kertoo onko vapaata liikua
     */
    public boolean voikoKokoelmaLiikkua(Palikkakokoelma k, Suunta s) {
        for (Palikka p : k.getPalikat()) {   
            if (!voikoLiikkua(k, p, s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kertoo voiko yksittäinen palikka liikkua haluttuun suuntaan.
     * Parametrinä oleva kokoelma välitetään kutsuttavalle metodille.
     * Tämän kokoelman palikat eivät estä liikkumista.
     * 
     * @param k Kokoelma, joka ei estä liikkumista
     * @param p Palikka, jota liikutetaan
     * @param s Haluttu suunta
     * 
     * @return Onko liikkuminen mahdollista
     */
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

    /**
     * Kertoo voiko palikka liikkua annettuihin koordinaatteihin.
     * Jos parametrinä annetaan kokoelma, kyseisen kokoelman palikat eivät
     * estä liikkumista.
     * 
     * @param liikkuvanKokoelma Liikutettavan palikan kokoelma
     * @param x Kohde x-koordinaatti
     * @param y Kohde y-koordinaatti
     * 
     * @return Onnistuuko liikkuminen
     */
    public boolean voikoLiikkua(Palikkakokoelma liikkuvanKokoelma, int x, int y) {
        
        if (x <= -1 || x >= pelialueenLeveys || y <= -1 || y >= pelialueenKorkeus) {
            return false;
        }

        for (Palikkakokoelma palikkakokoelma : alue.getKokoelmat()) {
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
    
    /**
     * Kertoo voiko kokoelma kääntyä esteettä.
     * Lukee kääntymiseen vaadittavat paikat kokoelman kääntymisinfosta.
     * Positiivinen kiertosuunta vastaa myötäpäivään kääntymistä ja negatiivinen
     * vastapäivään kääntymistä.
     * 
     * @param k Käännettävä kokoelma
     * @param kiertosuunta Haluttu kiertosuunta
     * 
     * @return Onko kääntyminen mahdollista
     */
    public boolean voikoKokoelmaKaantya(Palikkakokoelma k, int kiertosuunta) {
        if (k.getKaantymisInfo() == null) {
            return false;
        }
        
        int haettavaKaantymisInfo;
        for (int i = 1; i < 4; i++) {
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
