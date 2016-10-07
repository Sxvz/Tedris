package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;

public class TaysienRivienKasittelijaTest {
    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private Debugkokoelma kokoelma;
    private TaysienRivienKasittelija kasittelija;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        kokoelma = new Debugkokoelma();
        kasittelija = new TaysienRivienKasittelija(alue, tarkastaja);
        kokoelma.rivinLevyinenPalikka(3);
        alue.lisaaKokoelma(kokoelma);
    }
    
    @Test
    public void taysiRiviTuhotaan() {
        kasittelija.paivita();
        
        assertEquals(0, alue.getKokoelmat().size());
    }
    
    @Test
    public void kokoelmatPaloitellaanOikein() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(2,2));
        kokoelma2.lisaaPalikka(new Palikka(2,1));
        kokoelma2.lisaaPalikka(new Palikka(1,2));
        kokoelma2.lisaaPalikka(new Palikka(3,2));
        kokoelma2.lisaaPalikka(new Palikka(2,4));
        kokoelma2.lisaaPalikka(new Palikka(2,5));
        kokoelma2.lisaaPalikka(new Palikka(1,4));
        kokoelma2.lisaaPalikka(new Palikka(3,4));
        alue.lisaaKokoelma(kokoelma2);
        
        kasittelija.paivita();
        
        assertEquals(2, alue.getKokoelmat().size());
    }
    
    @Test
    public void jamaPalikatPaatyvatPohjalle() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(2,1));
        kokoelma2.lisaaPalikka(new Palikka(2,2));
        alue.lisaaKokoelma(kokoelma2);
        
        kasittelija.paivita();
        
        assertEquals(28, alue.getKokoelmat().get(0).getPalikat().get(0).getY());
        assertEquals(29, alue.getKokoelmat().get(0).getPalikat().get(1).getY());
    }
}
