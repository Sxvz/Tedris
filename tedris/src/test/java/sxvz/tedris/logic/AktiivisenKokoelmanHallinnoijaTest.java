
package sxvz.tedris.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;

public class AktiivisenKokoelmanHallinnoijaTest {
    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private AktiivisenKokoelmanHallinnoija hallinnoija;
    
    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        hallinnoija = new AktiivisenKokoelmanHallinnoija(alue, tarkastaja);
    }

    @Test
    public void aktiivinenKokoelmaPutoaa() {
        Debugkokoelma k = new Debugkokoelma();
        k.lisaaPalikka(new Palikka(2, 28));
        
        alue.setAktiivinenKokoelma(k);
        hallinnoija.paivita();
        
        assertFalse(alue.getKokoelmat().contains(k));
        assertEquals(29, alue.getAktiivinenKokoelma().getPalikat().get(0).getY());
    }
    
    @Test
    public void josKokoelmaSaavuttaaPohjanSeLisataanEiAktiivistenJoukkoon() {
        Debugkokoelma k = new Debugkokoelma();
        k.lisaaPalikka(new Palikka(2, 30));
        
        alue.setAktiivinenKokoelma(k);
        hallinnoija.paivita();
        
        assertTrue(alue.getKokoelmat().contains(k));
        assertTrue(alue.getAktiivinenKokoelma() == null);
    }
    
    @Test
    public void uusiKokoelmaLuodaanOikein() {
        hallinnoija.paivita();
        
        assertNotNull(alue.getAktiivinenKokoelma());
        assertNotNull(alue.getAktiivinenKokoelma().getVari());
//        assertEquals(alue.getLeveys() / 2, alue.getAktiivinenKokoelma().getPalikat().get(0).getX());
//        assertEquals(0, alue.getAktiivinenKokoelma().getPalikat().get(0).getY());
    }
}
