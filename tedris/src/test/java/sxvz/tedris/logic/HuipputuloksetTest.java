package sxvz.tedris.logic;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import sxvz.tedris.domain.Tulos;

public class HuipputuloksetTest {

    private Huipputulokset tulokset;
    private Tulos uusi;
    private Tulos luettu;
    private File tiedosto;
    private Tiedostotyokalut tyokalut;

    @Before
    public void setUp() {
        String nimi = "testi.txt";
        tulokset = new Huipputulokset(nimi);
        uusi = new Tulos("abc", 100);
        tiedosto = new File(nimi);
        tyokalut = new Tiedostotyokalut(tiedosto);
    }

    @After
    public void clean() {
        tulokset.poista();
    }

    @Test
    public void KonstruktorissaAsetetaanOikeanlainenErotin() {
        String erotin = tulokset.getErotin();

        assertNotNull(erotin);
        assertTrue(erotin.length() > 3);
        assertTrue(erotin.charAt(0) != erotin.charAt(3));
    }

    @Test
    public void VertaaminenTyhjaanTiedostoonOnnistuu() {
        assertTrue(tulokset.vertaaHuipputuloksiin(100));
    }

    @Test
    public void UusiTulosVoidaanLisataJaLukeaTiedostosta() {
        tulokset.talletaHuipputuloksiin(uusi);
        luettu = tulokset.lueHuipputulokset()[0];

        assertEquals(uusi.toString(), luettu.toString());
    }

    @Test
    public void KorruptoitunutDataKasitellaanOikein() {
        uusi = new Tulos("abcd", 100);

        tulokset.talletaHuipputuloksiin(uusi);
        luettu = tulokset.lueHuipputulokset()[0];

        assertNull(luettu);
        assertTrue(tiedosto.exists());
        assertTrue(tyokalut.lue().isEmpty());

        ArrayList<String> rivit = new ArrayList<>();
        rivit.add("abc" + tulokset.getErotin() + "@");
        tyokalut.kirjoita(rivit);
        luettu = tulokset.lueHuipputulokset()[0];

        assertNull(luettu);
        
        rivit.clear();
        rivit.add("abc" + tulokset.getErotin() + -1);
        tyokalut.kirjoita(rivit);
        luettu = tulokset.lueHuipputulokset()[0];

        assertNull(luettu);
    }

    @Test
    public void PoistaminenToimii() {
        tulokset.talletaHuipputuloksiin(uusi);
        tulokset.poista();

        assertFalse(new File("testi.txt").exists());
    }

    @Test
    public void VertailuToimii() {
        TaytaTulokset();

        assertFalse(tulokset.vertaaHuipputuloksiin(90));
        assertTrue(tulokset.vertaaHuipputuloksiin(110));
    }

    private void TaytaTulokset() {
        for (int i = 0; i < 10; i++) {
            tulokset.talletaHuipputuloksiin(uusi);
        }
    }

    @Test
    public void TiedostoonPaatyyVainSortattua10Tulosta() {
        TaytaTulokset();

        tulokset.talletaHuipputuloksiin(new Tulos("a  ", 110));
        assertEquals(10, tyokalut.lue().size());
        assertEquals(110, tulokset.lueHuipputulokset()[0].getPisteet());
    }

    @Test
    public void resetointiToimii() {
        tulokset.talletaHuipputuloksiin(uusi);
        tulokset.resetoi();
        
        assertTrue(tiedosto.exists());
        assertTrue(tyokalut.lue().isEmpty());
    }
}
