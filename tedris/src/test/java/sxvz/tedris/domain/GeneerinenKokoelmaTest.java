
package sxvz.tedris.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeneerinenKokoelmaTest {
    
    @Test
    public void geneeriseenKokoelmaanVoiLisataPalikoita() {
        GeneerinenKokoelma k = new GeneerinenKokoelma(null);
        
        assertEquals(0, k.getPalikat().size());
        k.lisaaPalikka(new Palikka(0,0));
        assertEquals(1, k.getPalikat().size());
        k.lisaaPalikka(new Palikka(0,0));
        assertEquals(2, k.getPalikat().size());
    }
}
