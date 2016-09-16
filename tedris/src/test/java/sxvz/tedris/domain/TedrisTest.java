
package sxvz.tedris.domain;

import java.awt.event.ActionEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.logic.Tedris;

public class TedrisTest {
    private Tedris peli;
    private Debugkokoelma kokoelma;
    private Debugkokoelma kokoelma2;
    
    @Before
    public void setUp() {
        peli = new Tedris(20,30);
        kokoelma = new Debugkokoelma();
        kokoelma2 = new Debugkokoelma();
        
        kokoelma.lisaaPalikka(new Palikka(6,8,peli));
        kokoelma.lisaaPalikka(new Palikka(6,7,peli));
        kokoelma2.lisaaPalikka(new Palikka(5,9,peli));
        kokoelma2.lisaaPalikka(new Palikka(6,9,peli));
        
        peli.setNykyinenPalikka(kokoelma);
        peli.lisaaPalikoita(kokoelma2);
    }
    
    @Test
    public void pelinKonstruktoriAsettaaMuuttujatOikein() {
        assertEquals(20, peli.getPelialueenLeveys());
        assertEquals(30, peli.getPelialueenKorkeus());
        assertTrue(peli.isPeliKaynnissa());
    }
    
    @Test
    public void onkoVapaaEiValehtele() {
        assertFalse(peli.onkoVapaa(-1, 7));
        assertFalse(peli.onkoVapaa(0, -3));
        assertFalse(peli.onkoVapaa(0, 31));
        assertFalse(peli.onkoVapaa(21, 0));
        assertTrue(peli.onkoVapaa(15, 15));
        assertTrue(peli.onkoVapaa(6, 7));
        assertFalse(peli.onkoVapaa(6, 9));
    }

}
