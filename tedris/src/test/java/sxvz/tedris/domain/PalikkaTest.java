package sxvz.tedris.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.engine.Pelilooppi;


public class PalikkaTest {
    
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
        
        p.liiku(7, 10);
        assertEquals(7, p.getX());
        assertEquals(10, p.getY());
    }
    
}
