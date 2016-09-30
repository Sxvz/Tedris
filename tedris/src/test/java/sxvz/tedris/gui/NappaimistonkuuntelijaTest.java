
package sxvz.tedris.gui;

import sxvz.tedris.gui.Nappaimistonkuuntelija;
import java.awt.event.KeyEvent;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.engine.Pelilooppi;

public class NappaimistonkuuntelijaTest {
    private Pelilooppi peli;
    private Nappaimistonkuuntelija kuuntelija;
    private Debugkokoelma kokoelma;
    
    @Before
    public void setUp() {
        peli = new Pelilooppi(20,30);
        kuuntelija = new Nappaimistonkuuntelija(peli);
        kokoelma = new Debugkokoelma();
        kokoelma.lisaaPalikka(new Palikka(0,0));
        
    }
    
    @Test
    public void josAktiivinenPalikkaOnNullKaikkiEiRajahda() {
        peli.lisaaPalikoita(kokoelma);
        peli.setAktiivinenPalikka(null);
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
