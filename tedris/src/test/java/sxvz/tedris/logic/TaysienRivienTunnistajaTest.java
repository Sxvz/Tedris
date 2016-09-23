package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.engine.Pelilooppi;

public class TaysienRivienTunnistajaTest {
    private Pelilooppi peli;
    private Debugkokoelma kokoelma;
    private TaysienRivienTunnistaja tunnistaja;

    @Before
    public void setUp() {
        peli = new Pelilooppi(20, 30);
        kokoelma = new Debugkokoelma();
        tunnistaja = new TaysienRivienTunnistaja(peli);
        kokoelma.rivinLevyinenPalikka(3);
    }
    
    @Test
    public void taysiRiviLoytyy() {
        peli.lisaaPalikoita(kokoelma);
        assertTrue(tunnistaja.etsiTaydetRivit().contains(3));
        assertEquals(1, tunnistaja.etsiTaydetRivit().size());
    }
}
