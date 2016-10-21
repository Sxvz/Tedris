
package sxvz.tedris.logic;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.gui.LuovutaNapinKuuntelija;
import sxvz.tedris.gui.NimikirjaintenKysyja;

public class PelitilanHallinnoijaTest {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private AktiivisenKokoelmanHallinnoija hallinnoija;
    private PelitilanHallinnoija pelitilanHallinnoija;
    private Pelilooppi looppi;
    private LuovutaNapinKuuntelija napinKuuntelija;
    private JButton nappi;
    private Pisteenlaskenta laskenta;
    private NimikirjaintenKysyja kysyja;
    private Huipputulokset tulokset;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        looppi = new Pelilooppi(1000);
        laskenta = new Pisteenlaskenta(2);
        kysyja = new NimikirjaintenKysyja(null) {
             @Override
             public String kysyNimikirjaimia() {
                 return "aaa";
             }
        };
        tulokset = new Huipputulokset("testi.txt");
        pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta, tulokset, kysyja);
        nappi = new JButton();
        napinKuuntelija = new LuovutaNapinKuuntelija(nappi, nappi, new JComboBox(), pelitilanHallinnoija, laskenta);
        hallinnoija = new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, napinKuuntelija, new Random());
    }
    
    @After
    public void clean() {
        tulokset.poista();
    }
    
    @Test
    public void peliAlkaaOikein() {
        alue.lisaaKokoelma(new Debugkokoelma());
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        laskenta.pisteyta(lista);
        pelitilanHallinnoija.aloitaPeli(10000);
        
        assertTrue(alue.getKokoelmat().isEmpty());
        assertNull(alue.getAktiivinenKokoelma());
        assertNull(alue.getSeuraavaKokoelma());
        assertEquals(0, laskenta.getPisteet());
        assertEquals(0, laskenta.getLisattavatPisteet());
        assertTrue(pelitilanHallinnoija.pyoriikoLooppi());
        assertTrue(pelitilanHallinnoija.isPeliKaynnissa());
        assertEquals(10000, looppi.getDelay());
    }
    
    @Test
    public void peliPauseentuu() {
        pelitilanHallinnoija.aloitaPeli(1000);
        pelitilanHallinnoija.pause();
        
        assertFalse(pelitilanHallinnoija.pyoriikoLooppi());
        assertTrue(pelitilanHallinnoija.isPeliKaynnissa());
        
        pelitilanHallinnoija.pause();
        assertTrue(pelitilanHallinnoija.pyoriikoLooppi());
        assertTrue(pelitilanHallinnoija.isPeliKaynnissa());
    }
    
    @Test
    public void peliPaattyyOikein() {
        pelitilanHallinnoija.aloitaPeli(1000);
        ArrayList<Integer> riveja = new ArrayList<>();
        riveja.add(1);
        laskenta.pisteyta(riveja);
        pelitilanHallinnoija.lopetaPeli();
        
        assertFalse(pelitilanHallinnoija.pyoriikoLooppi());
        assertFalse(pelitilanHallinnoija.isPeliKaynnissa());
        assertEquals("aaa", tulokset.lueHuipputulokset()[0].getTekija());
        assertTrue(tulokset.lueHuipputulokset()[0].getPisteet() > 0);
    }
}
