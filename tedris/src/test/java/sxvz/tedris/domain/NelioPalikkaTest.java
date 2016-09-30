
package sxvz.tedris.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class NelioPalikkaTest {
    Palikkakokoelma k;
    
    @Before
    public void setUp() {
        k = new NelioPalikka(null, 5, 5);
    }
    
    @Test
    public void nelioPalikkaLuodaanOikein() {
        assertEquals(5, k.getPalikat().get(0).getX());
        assertEquals(5, k.getPalikat().get(0).getY());
        assertEquals(6, k.getPalikat().get(1).getX());
        assertEquals(5, k.getPalikat().get(1).getY());
        assertEquals(5, k.getPalikat().get(2).getX());
        assertEquals(6, k.getPalikat().get(2).getY());
        assertEquals(6, k.getPalikat().get(3).getX());
        assertEquals(6, k.getPalikat().get(3).getY());
    }
    
    @Test
    public void nelioPalikkaEiKaanny() {
        k.kaanny(1);
        assertEquals(5, k.getPalikat().get(0).getX());
        assertEquals(5, k.getPalikat().get(0).getY());
        assertEquals(6, k.getPalikat().get(1).getX());
        assertEquals(5, k.getPalikat().get(1).getY());
        assertEquals(5, k.getPalikat().get(2).getX());
        assertEquals(6, k.getPalikat().get(2).getY());
        assertEquals(6, k.getPalikat().get(3).getX());
        assertEquals(6, k.getPalikat().get(3).getY());
        
        k.kaanny(-1);
        assertEquals(5, k.getPalikat().get(0).getX());
        assertEquals(5, k.getPalikat().get(0).getY());
        assertEquals(6, k.getPalikat().get(1).getX());
        assertEquals(5, k.getPalikat().get(1).getY());
        assertEquals(5, k.getPalikat().get(2).getX());
        assertEquals(6, k.getPalikat().get(2).getY());
        assertEquals(6, k.getPalikat().get(3).getX());
        assertEquals(6, k.getPalikat().get(3).getY());
    }
    
    @Test
    public void nelioPalikkanKaantymisInfoOnNull() {
        assertEquals(null, k.getKaantymisInfo());
    }
}
