package sxvz.tedris.logic;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PisteenLaskentaTest {

    private Pisteenlaskenta laskenta;
    private ArrayList<Integer> pisteytettavat;

    @Before
    public void setUp() {
        laskenta = new Pisteenlaskenta(2);
        pisteytettavat = new ArrayList<>();
    }

    @Test
    public void pisteitaAnnetaanOikeinHelpolla() {
        laskenta.setVaikeusaste(0);
        pisteytettavat.add(1);
        pisteytettavat.add(1);

        laskenta.pisteyta(pisteytettavat);

        assertEquals(800, laskenta.getPisteet());
        assertEquals(800, laskenta.getLisattavatPisteet());

        pisteytettavat.add(1);

        laskenta.pisteyta(pisteytettavat);

        assertEquals(2600, laskenta.getPisteet());
        assertEquals(1800, laskenta.getLisattavatPisteet());
    }

    @Test
    public void pisteitaAnnetaanOikeinNormaalilla() {
        laskenta.setVaikeusaste(1);
        pisteytettavat.add(1);

        laskenta.pisteyta(pisteytettavat);

        assertEquals(400, laskenta.getPisteet());
        assertEquals(400, laskenta.getLisattavatPisteet());
    }
    
    @Test
    public void pisteitaAnnetaanOikeinVaikealla() {
        laskenta.setVaikeusaste(2);
        pisteytettavat.add(1);

        laskenta.pisteyta(pisteytettavat);

        assertEquals(1000, laskenta.getPisteet());
        assertEquals(1000, laskenta.getLisattavatPisteet());
    }
    @Test
    public void pisteetNollaantuvat() {
        laskenta.setVaikeusaste(0);
        pisteytettavat.add(1);
        laskenta.pisteyta(pisteytettavat);
        
        laskenta.nollaa();

        assertEquals(0, laskenta.getPisteet());
        assertEquals(0, laskenta.getLisattavatPisteet());
    }
}
