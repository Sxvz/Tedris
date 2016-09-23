package sxvz.tedris.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.engine.Pelilooppi;


public class PalikkaTest {
    private Pelilooppi peli;
    
    @Before
    public void setUp() {
        peli = new Pelilooppi(20,30);
    }
    
    @Test
    public void palikkaSaaLuodessaHalututKoordinaatit() {
        Palikka p = new Palikka(6,8,null);
        
        assertEquals(6, p.getX());
        assertEquals(8, p.getY());
    }
    
    @Test
    public void voikoLiikkuaToimii() {
        Palikka p = new Palikka(6,8,peli);
        Palikka p2 = new Palikka(6,9,peli);
        Palikka p3 = new Palikka(7,8,peli);
        
        Debugkokoelma kokoelma = new Debugkokoelma();
        kokoelma.lisaaPalikka(p2);
        kokoelma.lisaaPalikka(p3);
        peli.lisaaPalikoita(kokoelma);
        
        assertFalse(p.voikoLiikkua(Suunta.ALAS));
        assertTrue(p.voikoLiikkua(Suunta.VASEN));
        assertFalse(p.voikoLiikkua(7,8));
        assertTrue(p.voikoLiikkua(10,11));
    }
    
    @Test
    public void voikoLiikkuaEiAnnaPoistuaPelialueelta() {
        Palikka p = new Palikka(0,30,peli);
        
        assertFalse(p.voikoLiikkua(Suunta.ALAS));
        assertFalse(p.voikoLiikkua(Suunta.VASEN));
    }
    
    @Test
    public void palikkaLiikkuuSuunnilla() {
        Palikka p = new Palikka(6,8,null);
        
        p.liiku(Suunta.ALAS);
        p.liiku(Suunta.VASEN);

        assertEquals(5, p.getX());
        assertEquals(9, p.getY());
    }
    
    @Test
    public void palikkaLiikkuuKoordinaateilla() {
        Palikka p = new Palikka(6,8,null);
        
        p.liiku(5, -3);
        assertEquals(11, p.getX());
        assertEquals(5, p.getY());
    }
    
}
