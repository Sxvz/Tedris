package sxvz.tedris.domain;

import java.awt.Color;

public class NelioPalikka extends Palikkakokoelma {

    public NelioPalikka(Color vari, int x, int y) {
        super(vari);
        palikat.add(new Palikka(x, y));
        palikat.add(new Palikka(x + 1, y));
        palikat.add(new Palikka(x, y + 1));
        palikat.add(new Palikka(x + 1, y + 1));
        kaantymisInfo = null;

    }

    @Override
    public void kaanny(int kiertosuunta) {
    }
}
