package sxvz.tedris.logic;

import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Pelilooppi;
import sxvz.tedris.gui.LuovutaNapinKuuntelija;

public class AktiivisenKokoelmanHallinnoijaTest {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private AktiivisenKokoelmanHallinnoija hallinnoija;
    private PelitilanHallinnoija pelitilanHallinnoija;
    private Pelilooppi looppi;
    private LuovutaNapinKuuntelija napinKuuntelija;
    private JButton nappi;
    private Pisteenlaskenta laskenta;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        tarkastaja = new Vapaustarkastaja(alue);
        looppi = new Pelilooppi(1000);
        laskenta = new Pisteenlaskenta(1);
        pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta, null, null);
        nappi = new JButton();
        napinKuuntelija = new LuovutaNapinKuuntelija(nappi, nappi, new JComboBox(), pelitilanHallinnoija, laskenta);
        Random random = new Random() {
            @Override
            public int nextInt(int i) {
                return 1;
            }
        };
        hallinnoija = new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, napinKuuntelija, random);
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
    public void uudetKokoelmatLuodaanTarvittaessa() {
        alue.tyhjenna();
        hallinnoija.paivita();

        assertNotNull(alue.getSeuraavaKokoelma());
        assertNotNull(alue.getSeuraavaKokoelma().getVari());
        assertNotNull(alue.getAktiivinenKokoelma());
        assertNotNull(alue.getAktiivinenKokoelma().getVari());
    }

    @Test
    public void peliPaattyyJosUudelleKokoelmalleEiOleTilaa() {
        pelitilanHallinnoija.aloitaPeli(10000);
        alue.tyhjenna();
        Debugkokoelma k = new Debugkokoelma();
        k.lisaaPalikka(new Palikka(alue.getLeveys() / 2, 0));
        k.lisaaPalikka(new Palikka(alue.getLeveys() / 2, 1));
        alue.lisaaKokoelma(k);
        nappi.setText("Luovuta");

        hallinnoija.paivita();

        assertFalse(pelitilanHallinnoija.isPeliKaynnissa());
    }
}
