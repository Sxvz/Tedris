
package sxvz.tedris.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Pelilooppi;

public class AktiivisenPalikanHallinnoijaTest {
    private Pelilooppi peli;
    private AktiivisenPalikanHallinnoija hallinnoija;
    
    @Before
    public void setUp() {
        peli = new Pelilooppi(20, 30);
        hallinnoija = peli.getAktiivisenPalikanHallinnoija();
    }
    
    @Test
    public void josPalikkaSaavuttaaPohjanSeLisataanEiAktiivistenJoukkoon() {
        Debugkokoelma k = new Debugkokoelma();
        k.lisaaPalikka(new Palikka(2, 30));
        
        peli.setAktiivinenPalikka(k);
        hallinnoija.hallinnoiAktiivistaPalikkaa(k);
        
        assertTrue(peli.getPalikat().contains(k));
    }
    
    @Test
    public void uusiPalikkaLuodaanOikein() {
        hallinnoija.hallinnoiAktiivistaPalikkaa(null);
        
        assertFalse(peli.getAktiivinenPalikka() == null);
        assertFalse(peli.getAktiivinenPalikka().getVari() == null);
        assertEquals(peli.getPelialueenLeveys() / 2, peli.getAktiivinenPalikka().getPalikat().get(0).getX());
        assertEquals(0, peli.getAktiivinenPalikka().getPalikat().get(0).getY());
    }
}
