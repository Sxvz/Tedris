package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Lyhyen T:n näköinen kokoelma, joka osaa kääntyä.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 */
public class TKokoelma extends Palikkakokoelma {

    /**
     * Konstruktori kokoelman luontia varten.
     * Luo infon palikan kääntymistä varten.
     * 
     * @param vari Kokoelman vari
     * @param x X-koordinaati pääpalikalle
     * @param y Y-koordinaatti pääpalikalle
     */
    public TKokoelma(Color vari, int x, int y) {
        super(vari);
        palikat.add(new Palikka(x, y));
        palikat.add(new Palikka(x - 1, y));
        palikat.add(new Palikka(x + 1, y));
        palikat.add(new Palikka(x, y + 1));
        luoKaantymisInfo();

    }

    private void luoKaantymisInfo() {
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.get(0).add(new int[]{-1, 0});
        kaantymisInfo.get(0).add(new int[]{1, 0});
        kaantymisInfo.get(0).add(new int[]{0, 1});
        kaantymisInfo.get(1).add(new int[]{0, -1});
        kaantymisInfo.get(1).add(new int[]{0, 1});
        kaantymisInfo.get(1).add(new int[]{-1, 0});
        kaantymisInfo.get(2).add(new int[]{1, 0});
        kaantymisInfo.get(2).add(new int[]{-1, 0});
        kaantymisInfo.get(2).add(new int[]{0, -1});
        kaantymisInfo.get(3).add(new int[]{0, 1});
        kaantymisInfo.get(3).add(new int[]{0, -1});
        kaantymisInfo.get(3).add(new int[]{1, 0});
    }

}
