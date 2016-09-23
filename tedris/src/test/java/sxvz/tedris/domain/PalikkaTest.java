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
        Palikka p = new Palikka(6,8);
        
        assertEquals(6, p.getX());
        assertEquals(8, p.getY());
    }
    
    @Test
    public void palikkaLiikkuuSuunnilla() {
        Palikka p = new Palikka(6,8);
        
        p.liiku(Suunta.ALAS);
        p.liiku(Suunta.VASEN);

        assertEquals(5, p.getX());
        assertEquals(9, p.getY());
    }
    
    @Test
    public void palikkaLiikkuuKoordinaateilla() {
        Palikka p = new Palikka(6,8);
        
        p.liiku(5, -3);
        assertEquals(11, p.getX());
        assertEquals(5, p.getY());
    }
    
}
