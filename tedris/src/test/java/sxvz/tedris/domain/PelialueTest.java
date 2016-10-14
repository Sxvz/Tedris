package sxvz.tedris.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelialueTest {

    private Pelialue alue;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
    }

    @Test
    public void konstruktoriAsettaaAlkuarvotOikein() {
        assertEquals(20, alue.getLeveys());
        assertEquals(30, alue.getKorkeus());
        assertTrue(alue.getKokoelmat() != null);
        assertTrue(alue.getKokoelmat().isEmpty());
        assertTrue(alue.getAktiivinenKokoelma() == null);
    }

    @Test
    public void pelialueellePystyyLisaamaanKokoelmia() {
        alue.lisaaKokoelma(new NelioKokoelma(null, 10, 10));

        assertEquals(1, alue.getKokoelmat().size());
    }

    @Test
    public void pelialueTyhjenee() {
        alue.lisaaKokoelma(new Debugkokoelma());
        alue.tyhjenna();

        assertEquals(0, alue.getKokoelmat().size());
    }

}
