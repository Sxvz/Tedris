package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.engine.Pelilooppi;

public class TaysienRivienTunnistajaTest {
    private Pelilooppi peli;
    private Debugkokoelma kokoelma;
    private TaysienRivienKasittelija kasittelija;

    @Before
    public void setUp() {
        peli = new Pelilooppi(20, 30);
        kokoelma = new Debugkokoelma();
        kasittelija = peli.getTaysienRivienKasittelija();
        kokoelma.rivinLevyinenPalikka(3);
        peli.lisaaPalikoita(kokoelma);
    }
    
    @Test
    public void taysiRiviTuhotaan() {
        kasittelija.kasitteleTaydetRivit();
        
        assertEquals(0, peli.getPalikat().size());
    }
    
    @Test
    public void kokoelmatPaloitellaanOikein() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(2,2));
        kokoelma2.lisaaPalikka(new Palikka(2,4));
        peli.lisaaPalikoita(kokoelma2);
        
        kasittelija.kasitteleTaydetRivit();
        
        assertEquals(2, peli.getPalikat().size());
    }
    
    @Test
    public void jamaPalikatPaatyvatPohjalle() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(2,1));
        kokoelma2.lisaaPalikka(new Palikka(2,2));
        peli.lisaaPalikoita(kokoelma2);
        
        kasittelija.kasitteleTaydetRivit();
        
        assertEquals(28, peli.getPalikat().get(0).getPalikat().get(0).getY());
        assertEquals(29, peli.getPalikat().get(0).getPalikat().get(1).getY());
    }
}
