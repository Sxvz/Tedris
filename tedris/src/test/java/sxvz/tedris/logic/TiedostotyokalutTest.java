package sxvz.tedris.logic;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class TiedostotyokalutTest {

    private Tiedostotyokalut tyokalut;
    private File tiedosto;
    private ArrayList<String> lista;
    
    @Before
    public void setUp() {
        tiedosto = new File("testi.txt");
        tiedosto.delete();
        tyokalut = new Tiedostotyokalut(tiedosto);
        lista = new ArrayList<>();
        lista.add("a");
        lista.add("bb");
        lista.add("@");
    }
    
    @After
    public void clean() {
        tiedosto.delete();
    }
    
    @Test
    public void KonstruktoriLuoTiedostonTarvittaessa() {
        assertTrue(tiedosto.exists());
    }
    
    @Test
    public void KirjoittaminenJaLukeminenToimii() {
        tyokalut.kirjoita(lista);
        ArrayList<String> luetut = tyokalut.lue();
        
        assertEquals(lista, luetut);
    }
    
    @Test
    public void LuominenJaPoistaminenToimii() {
        tyokalut.luoTiedostoJosEiOlemassa();
        assertTrue(tiedosto.exists());
        tyokalut.poista();
        assertFalse(tiedosto.exists());
    }
}
