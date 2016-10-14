package sxvz.tedris.engine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelilooppiTest {

    private Pelilooppi looppi;

    @Before
    public void setUp() {
        looppi = new Pelilooppi(1000);
    }

    @Test
    public void loopinKonstruktoriAsettaaMuuttujatOikein() {
        assertTrue(looppi.paivitettavat != null);
        assertEquals(100, looppi.getInitialDelay());
        assertEquals(1, looppi.getActionListeners().length);
        assertEquals(0, looppi.paivitettavat.size());
    }

    @Test
    public void looppiinVoiLisataPaivitettavia() {
        looppi.lisaaPaivitettava(null);
        looppi.lisaaPaivitettava(null);
        
        assertEquals(2, looppi.paivitettavat.size());
    }
}
