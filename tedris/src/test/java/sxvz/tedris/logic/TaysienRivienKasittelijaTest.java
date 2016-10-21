package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Pelilooppi;

public class TaysienRivienKasittelijaTest {
    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private Debugkokoelma kokoelma;
    private TaysienRivienKasittelija kasittelija;
    private Pisteenlaskenta laskenta;
    private Pelilooppi looppi;
    private Vaikeuttaja vaikeuttaja;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        kokoelma = new Debugkokoelma();
        laskenta = new Pisteenlaskenta(20);
        looppi = new Pelilooppi(1000);
        vaikeuttaja = new Vaikeuttaja(looppi, 0.5);
        kasittelija = new TaysienRivienKasittelija(alue, tarkastaja, laskenta, vaikeuttaja);
        kokoelma.rivinLevyinenPalikka(3);
        alue.lisaaKokoelma(kokoelma);
    }
    
    @Test
    public void TaysiRiviTuhotaan() {
        kasittelija.paivita();
        
        assertEquals(0, alue.getKokoelmat().size());
    }
    
    @Test
    public void TaysiRiviPisteytetaan() {
        kasittelija.paivita();
        
        assertTrue(0 < laskenta.getPisteet());
    }
    
    @Test
    public void VaikeuttajaaPaivitetaan(){
        kasittelija.paivita();
        
        assertEquals(500 ,looppi.getDelay());
    }
    
    @Test
    public void KokoelmatPaloitellaanOikein() {
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
    public void JamaPalikatPaatyvatPohjalle() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(2,1));
        kokoelma2.lisaaPalikka(new Palikka(2,2));
        alue.lisaaKokoelma(kokoelma2);
        
        kasittelija.paivita();
        
        assertEquals(28, alue.getKokoelmat().get(0).getPalikat().get(0).getY());
        assertEquals(29, alue.getKokoelmat().get(0).getPalikat().get(1).getY());
    }
}
