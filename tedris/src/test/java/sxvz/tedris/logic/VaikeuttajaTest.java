package sxvz.tedris.logic;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import sxvz.tedris.engine.Pelilooppi;

public class VaikeuttajaTest {

    private Pelilooppi looppi;
    private Vaikeuttaja vaikeuttaja;
    
    @Before
    public void setUp() {
        looppi = new Pelilooppi(1000);
        vaikeuttaja = new Vaikeuttaja(looppi, 0.5);
    }
    
    @Test
    public void PeliVaikeutuu() {
        vaikeuttaja.paivita();
        assertEquals(500, looppi.getDelay());
        vaikeuttaja.paivita();
        assertEquals(250, looppi.getDelay());
    }
}
