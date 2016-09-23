package sxvz.tedris.domain;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sxvz.tedris.engine.Pelilooppi;

public class DebugkokoelmaTest {

    private Pelilooppi peli;
    private Debugkokoelma kokoelma;

    @Before
    public void setUp() {
        peli = new Pelilooppi(20, 30);
        kokoelma = new Debugkokoelma();
    }

    @Test
    public void debugKokoelmanKonstruktoriToimii() {
        assertEquals(Color.RED, kokoelma.getVari());
        assertEquals(0, kokoelma.getPalikat().size());
    }

    @Test
    public void debugKokoelmanToinenKonstruktoriToimii() {
        Debugkokoelma kokoelma2 = new Debugkokoelma(new Palikka(0, 0), new Palikka(0, 1));
        assertEquals(Color.RED, kokoelma2.getVari());
        assertEquals(2, kokoelma2.getPalikat().size());
    }

    @Test
    public void debugKokoelmaOttaaPalikoitaVastaan() {
        Palikka p = new Palikka(6, 8);

        kokoelma.lisaaPalikka(p);

        assertTrue(kokoelma.getPalikat().contains(p));
    }

    @Test
    public void kokoelmaLiikkuu() {
        kokoelma.lisaaPalikka(new Palikka(6, 8));
        kokoelma.lisaaPalikka(new Palikka(6, 7));

        kokoelma.liiku(Suunta.ALAS);
        kokoelma.liiku(Suunta.OIKEA);

        assertEquals(7, kokoelma.getPalikat().get(0).getX());
        assertEquals(7, kokoelma.getPalikat().get(1).getX());
        assertEquals(9, kokoelma.getPalikat().get(0).getY());
        assertEquals(8, kokoelma.getPalikat().get(1).getY());
    }

    @Test
    public void rivinLevyinenPalikkaSyntyy() {
        kokoelma.lisaaPalikka(new Palikka(6, 8));
        kokoelma.rivinLevyinenPalikka(7);

        assertEquals(20, kokoelma.getPalikat().size());
        assertEquals(7, kokoelma.getPalikat().get(2).getY());
    }
}
