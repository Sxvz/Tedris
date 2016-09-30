package sxvz.tedris.domain;

import java.awt.Color;

public class GeneerinenKokoelma extends Palikkakokoelma {

    public GeneerinenKokoelma(Color vari) {
        super(vari);
    }

    public void lisaaPalikka(Palikka p) {
        palikat.add(p);
    }
}
