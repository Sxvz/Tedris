
package sxvz.tedris.engine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.DummyPaivitettava;
import sxvz.tedris.domain.Palikka;

public class PelilooppiTest {
    private Pelilooppi peli;
    private Debugkokoelma kokoelma;
    private Debugkokoelma kokoelma2;
    
    @Before
    public void setUp() {
        peli = new Pelilooppi(20,30);
        kokoelma = new Debugkokoelma();
        kokoelma2 = new Debugkokoelma();
        
        kokoelma.lisaaPalikka(new Palikka(6,8));
        kokoelma.lisaaPalikka(new Palikka(6,7));
        kokoelma2.lisaaPalikka(new Palikka(5,9));
        kokoelma2.lisaaPalikka(new Palikka(6,9));
        
        peli.setPaivitettava(new DummyPaivitettava());
        
        peli.setAktiivinenPalikka(kokoelma);
        peli.lisaaPalikoita(kokoelma2);
    }
    
    @Test
    public void pelinKonstruktoriAsettaaMuuttujatOikein() {
        assertEquals(20, peli.getPelialueenLeveys());
        assertEquals(30, peli.getPelialueenKorkeus());
        assertTrue(peli.isPeliKaynnissa());
        assertEquals(2000, peli.getInitialDelay());
        assertEquals(1, peli.getActionListeners().length);
        assertEquals(9, peli.getCountteri());
        assertFalse(peli.getTaysienRivienKasittelija() == null);
        assertFalse(peli.getVapaustarkastaja() == null);
        assertFalse(peli.getAktiivinenPalikka() == null);
        assertFalse(peli.getPalikat() == null);
    }
    
    @Test
    public void peliPysahtyy() {
        peli.setPeliKaynnissa(false);
        peli.actionPerformed(null);
        assertFalse(peli.isRunning());
    }

    @Test
    public void countteriKasvaaOikein() {
        peli.actionPerformed(null);
        peli.actionPerformed(null);
        assertEquals(2, peli.getCountteri());
        peli.setCountteri(9);
        peli.actionPerformed(null);
        assertEquals(1, peli.getCountteri());
    }
    
    @Test
    public void uusiPalikkaLuodaanJosAktiivistaEiOle() {
        peli.setAktiivinenPalikka(null);
        peli.actionPerformed(null);
        assertTrue(peli.getAktiivinenPalikka() != null);
    }
    
    @Test
    public void erikoistoimintojaEiTehdaAina() {
        peli.setAktiivinenPalikka(null);
        peli.setCountteri(0);
        peli.actionPerformed(null);
        assertTrue(peli.getAktiivinenPalikka() == null);
        peli.setCountteri(9);
        peli.actionPerformed(null);
        assertTrue(peli.getAktiivinenPalikka() != null);
    }
    
    @Test
    public void aktiivinenPalikkaPutoaa() {
        Debugkokoelma k = new Debugkokoelma();
        k.lisaaPalikka(new Palikka(0,0));
        peli.setAktiivinenPalikka(k);
        peli.setCountteri(9);
        peli.actionPerformed(null);
        assertEquals(1, peli.getAktiivinenPalikka().getPalikat().get(0).getY());
    }
    
    @Test
    public void pohjallePaatyvaPalikkaMenettaaAktiivisuutensa() {
        Debugkokoelma kokoelma = new Debugkokoelma();
        kokoelma.lisaaPalikka(new Palikka(0, 29));
        peli.setAktiivinenPalikka(kokoelma);
        peli.actionPerformed(null);
        assertTrue(peli.getAktiivinenPalikka() == null);
    }

}
