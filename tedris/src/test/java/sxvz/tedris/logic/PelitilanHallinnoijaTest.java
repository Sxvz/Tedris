
package sxvz.tedris.logic;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.gui.NapinKuuntelija;

public class PelitilanHallinnoijaTest {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private AktiivisenKokoelmanHallinnoija hallinnoija;
    private PelitilanHallinnoija pelitilanHallinnoija;
    private Pelilooppi looppi;
    private NapinKuuntelija napinKuuntelija;
    private JButton nappi;
    private Pisteenlaskenta laskenta;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        looppi = new Pelilooppi(1000);
        laskenta = new Pisteenlaskenta(2);
        pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta);
        nappi = new JButton();
        napinKuuntelija = new NapinKuuntelija(nappi, new JComboBox(), pelitilanHallinnoija, laskenta);
        hallinnoija = new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, napinKuuntelija, new Random());
    }
    
    @Test
    public void peliAlkaa() {
        alue.lisaaKokoelma(new Debugkokoelma());
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        laskenta.pisteyta(lista);
        pelitilanHallinnoija.aloitaPeli(1000);
        
        assertTrue(alue.getKokoelmat().isEmpty());
        assertNull(alue.getAktiivinenKokoelma());
        assertNull(alue.getSeuraavaKokoelma());
        assertEquals(0, laskenta.getPisteet());
        assertEquals(0, laskenta.getLisattavatPisteet());
        assertTrue(pelitilanHallinnoija.pyoriikoLooppi());
        assertTrue(pelitilanHallinnoija.isPeliKaynnissa());
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
    public void peliPaattyy() {
        pelitilanHallinnoija.aloitaPeli(1000);
        pelitilanHallinnoija.lopetaPeli();
        
        assertFalse(pelitilanHallinnoija.pyoriikoLooppi());
        assertFalse(pelitilanHallinnoija.isPeliKaynnissa());
    }
}
