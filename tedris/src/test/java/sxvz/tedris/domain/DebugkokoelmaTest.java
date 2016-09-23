
package sxvz.tedris.domain;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.engine.Pelilooppi;

public class DebugkokoelmaTest {
    private Pelilooppi peli;
    private Debugkokoelma kokoelma;
    
    @Before
    public void setUp() {
        peli = new Pelilooppi(20,30);
        kokoelma = new Debugkokoelma();
    }
    
    @Test
    public void debugKokoelmanKonstruktoriToimii() {
        assertEquals(Color.RED, kokoelma.getVari());
        assertTrue(kokoelma.getPalikat().isEmpty());
    }
    
    @Test
    public void debugKokoelmaOttaaPalikoitaVastaan() {
        Palikka p = new Palikka(6,8,null);
        
        kokoelma.lisaaPalikka(p);
        
        assertTrue(kokoelma.getPalikat().contains(p));
    }
    
    @Test
    public void kokoelmaLiikkuu() {         
        kokoelma.lisaaPalikka(new Palikka(6,8,peli));
        kokoelma.lisaaPalikka(new Palikka(6,7,peli));
        
        kokoelma.liiku(Suunta.ALAS);
        kokoelma.liiku(Suunta.OIKEA);
        
        assertEquals(7, kokoelma.getPalikat().get(0).getX());
        assertEquals(7, kokoelma.getPalikat().get(1).getX());
        assertEquals(9, kokoelma.getPalikat().get(0).getY());
        assertEquals(8, kokoelma.getPalikat().get(1).getY());
    }
    
    @Test
    public void liikuPalauttaaTrueJosOnnistuu() {
        kokoelma.lisaaPalikka(new Palikka(6,8,peli));
        kokoelma.lisaaPalikka(new Palikka(6,7,peli));
        
        assertTrue(kokoelma.liiku(Suunta.ALAS));
    }
    
    @Test
    public void liikuPalauttaaFalseJosEpaonnistuu() {
        kokoelma.lisaaPalikka(new Palikka(6,30,peli));
        kokoelma.lisaaPalikka(new Palikka(6,29,peli));
        
        assertFalse(kokoelma.liiku(Suunta.ALAS));
    }
}
