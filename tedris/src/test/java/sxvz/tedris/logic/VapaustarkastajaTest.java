package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;

public class VapaustarkastajaTest {

    private Pelilooppi peli;
    private Debugkokoelma kokoelma;
    private Vapaustarkastaja tarkastaja;

    @Before
    public void setUp() {
        peli = new Pelilooppi(20, 30);
        kokoelma = new Debugkokoelma();
        tarkastaja = new Vapaustarkastaja(peli);
        kokoelma.lisaaPalikka(new Palikka(4, 5));
//        kokoelma.lisaaPalikka(new Palikka(7, 5));
        kokoelma.lisaaPalikka(new Palikka(5, 6));
        peli.lisaaPalikoita(kokoelma);
    }

    @Test
    public void voikoKokoelmaLiikkuaToimii() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(5, 5));
        kokoelma2.lisaaPalikka(new Palikka(6, 5));

        assertFalse(tarkastaja.voikoKokoelmaLiikkua(kokoelma2, Suunta.ALAS));
        assertFalse(tarkastaja.voikoKokoelmaLiikkua(kokoelma2, Suunta.VASEN));
        assertTrue(tarkastaja.voikoKokoelmaLiikkua(kokoelma2, Suunta.OIKEA));
    }

    @Test
    public void voikoKokoelmaLiikkuaEiAnnaPoistuaPelialueelta() {
        Debugkokoelma kokoelma2 = new Debugkokoelma();
        kokoelma2.lisaaPalikka(new Palikka(0, 29));
        kokoelma2.lisaaPalikka(new Palikka(1, 29));

        assertFalse(tarkastaja.voikoKokoelmaLiikkua(kokoelma2, Suunta.ALAS));
        assertFalse(tarkastaja.voikoKokoelmaLiikkua(kokoelma2, Suunta.VASEN));
    }
    
    @Test
    public void voikoLiikkuaToimii() {
        Palikka p = new Palikka(5, 5);

        assertFalse(tarkastaja.voikoLiikkua(p, Suunta.ALAS));
        assertFalse(tarkastaja.voikoLiikkua(p, Suunta.VASEN));
        assertTrue(tarkastaja.voikoLiikkua(p, Suunta.OIKEA));
    }
}
