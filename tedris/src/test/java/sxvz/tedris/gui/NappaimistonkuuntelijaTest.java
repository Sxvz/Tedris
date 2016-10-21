
package sxvz.tedris.gui;

//import java.awt.event.KeyEvent;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Pelialue;

public class NappaimistonkuuntelijaTest {
    private Pelialue alue;
    private Nappaimistonkuuntelija kuuntelija;
    private Debugkokoelma kokoelma;
    
    @Before
    public void setUp() {
        alue = new Pelialue(20, 30);
        kuuntelija = new Nappaimistonkuuntelija(alue, null, null, null);
        kokoelma = new Debugkokoelma();
        kokoelma.lisaaPalikka(new Palikka(0, 0));
        
    }
    
    @Test
    public void josAktiivinenKokoelmaOnNullKaikkiEiRajahda() {
        alue.lisaaKokoelma(kokoelma);
        alue.setAktiivinenKokoelma(null);
        kuuntelija.keyPressed(null);
        assertEquals(0, kokoelma.getPalikat().get(0).getX());
        assertEquals(0, kokoelma.getPalikat().get(0).getY());
    }
    
//    @Test
//    public void ylosPainaminenEiLiikutaPalikkaa() {
//        peli.setAktiivinenPalikka(kokoelma);
//        kuuntelija.keyPressed(new KeyEvent(null, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'a'));
//        assertEquals(0, kokoelma.getPalikat().get(0).getX());
//        assertEquals(0, kokoelma.getPalikat().get(0).getY());
//    }
}
