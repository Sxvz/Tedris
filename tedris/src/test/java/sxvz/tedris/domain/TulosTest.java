package sxvz.tedris.domain;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class TulosTest {

    private Tulos tulos;

    @Before
    public void setUp() {
        tulos = new Tulos("abc", 100);
    }

    @Test
    public void KonstruktoriToimii() {
        assertEquals("abc", tulos.getTekija());
        assertEquals(100, tulos.getPisteet());
    }
    
    @Test
    public void ToStringToimii() {
        assertEquals("abc 100", tulos.toString());
    }
    
    @Test
    public void CompareToToimii() {
        ArrayList<Tulos> lista = new ArrayList<>();
        Tulos suurempi = new Tulos("abc", 120);
        lista.add(tulos);
        lista.add(suurempi);
        Collections.sort(lista);
        
        assertEquals(suurempi, lista.get(0));
    }
}
