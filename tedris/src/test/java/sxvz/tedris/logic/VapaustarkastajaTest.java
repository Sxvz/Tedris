package sxvz.tedris.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.NelioKokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.PitkaKokoelma;
import sxvz.tedris.domain.Suunta;

public class VapaustarkastajaTest {

    private Pelialue alue;
    private Debugkokoelma kokoelma;
    private Vapaustarkastaja tarkastaja;

    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        kokoelma = new Debugkokoelma();
        tarkastaja = new Vapaustarkastaja(alue);
        kokoelma.lisaaPalikka(new Palikka(4, 5));
//        kokoelma.lisaaPalikka(new Palikka(7, 5));
        kokoelma.lisaaPalikka(new Palikka(5, 6));
        alue.lisaaKokoelma(kokoelma);
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

        assertFalse(tarkastaja.voikoLiikkua(null, p, Suunta.ALAS));
        assertFalse(tarkastaja.voikoLiikkua(null, p, Suunta.VASEN));
        assertTrue(tarkastaja.voikoLiikkua(null, p, Suunta.OIKEA));
        assertFalse(tarkastaja.voikoLiikkua(null, p, null));
    }
    
    @Test
    public void voikoLiikkuaToimiiKoordinaateilla() {
        Palikka p = new Palikka(5, 5);

        assertFalse(tarkastaja.voikoLiikkua(null, -1, 0));
        assertFalse(tarkastaja.voikoLiikkua(null, 0, -1));
        assertFalse(tarkastaja.voikoLiikkua(null, 21, 0));
        assertFalse(tarkastaja.voikoLiikkua(null, 0, 30));
    }
    
    @Test
    public void palikkaEiPoistuPelialueelta() {
        Palikka p = new Palikka(0, 30);
        Palikka p2 = new Palikka(20, 0);

        assertFalse(tarkastaja.voikoLiikkua(null, p, Suunta.VASEN));
        assertFalse(tarkastaja.voikoLiikkua(null, p2, Suunta.OIKEA));
        assertFalse(tarkastaja.voikoLiikkua(null, p, Suunta.ALAS));
    }
    
    @Test
    public void kieltaaKaantymisenJosTiellaSeina() {
        PitkaKokoelma k = new PitkaKokoelma(null, 1, 0);

        assertFalse(tarkastaja.voikoKokoelmaKaantya(k, 1));
    }
    
    @Test
    public void kieltaaKaantymisenJosTiellaPalikka() {
        PitkaKokoelma k = new PitkaKokoelma(null, 5, 4);

        assertFalse(tarkastaja.voikoKokoelmaKaantya(k, 1));
    }
    
    @Test
    public void salliiKaantymisenJosVapaata() {
        PitkaKokoelma k = new PitkaKokoelma(null, 10, 10);

        assertTrue(tarkastaja.voikoKokoelmaKaantya(k, 1));
    }
    
    @Test
    public void kieltaaKaantymisenJosInfoOnNull() {
        NelioKokoelma k = new NelioKokoelma(null, 10, 10);

        assertFalse(tarkastaja.voikoKokoelmaKaantya(k, 1));
    }
    
    @Test
    public void kaantumisenTutkijaEiHammennyRajojenYlityksista() {
        PitkaKokoelma k = new PitkaKokoelma(null, 10, 10);

        k.kaanny(1);
        k.kaanny(1);
        k.kaanny(1);
        assertTrue(tarkastaja.voikoKokoelmaKaantya(k, 1));
        
        k.kaanny(1);
        assertTrue(tarkastaja.voikoKokoelmaKaantya(k, -1));
    }
}
