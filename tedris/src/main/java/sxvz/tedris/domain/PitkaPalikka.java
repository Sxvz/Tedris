package sxvz.tedris.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Neljän pituinen kokoelma, joka osaa kääntyä.
 * 
 * @see sxvz.tedris.domain.Palikkakokoelma
 */
public class PitkaPalikka extends Palikkakokoelma {

    /**
     * Konstruktori kokoelman luontia varten.
     * Luo infon palikan kääntymistä varten.
     * 
     * @param vari Pelin asettama väri
     * @param x X-koordinaati pääpalikalle
     * @param y Y-koordinaatti pääpalikalle
     */
    public PitkaPalikka(Color vari, int x, int y) {
        super(vari);
        palikat.add(new Palikka(x, y));
        palikat.add(new Palikka(x - 1, y));
        palikat.add(new Palikka(x + 1, y));
        palikat.add(new Palikka(x + 2, y));
        luoKaantymisInfo();

    }

    private void luoKaantymisInfo() {
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.add(new ArrayList<int[]>());
        kaantymisInfo.get(0).add(new int[]{-1, 0});
        kaantymisInfo.get(0).add(new int[]{1, 0});
        kaantymisInfo.get(0).add(new int[]{2, 0});
        kaantymisInfo.get(1).add(new int[]{0, -1});
        kaantymisInfo.get(1).add(new int[]{0, 1});
        kaantymisInfo.get(1).add(new int[]{0, 2});
        kaantymisInfo.get(2).add(new int[]{1, 0});
        kaantymisInfo.get(2).add(new int[]{-1, 0});
        kaantymisInfo.get(2).add(new int[]{-2, 0});
        kaantymisInfo.get(3).add(new int[]{0, 1});
        kaantymisInfo.get(3).add(new int[]{0, -1});
        kaantymisInfo.get(3).add(new int[]{0, -2});
    }
    
    /**
     * Metodi, joka kääntää palikan kääntämisInfon ohjeiden mukaan.
     * Ei tarkista onko esteitä.
     * 
     * @param kiertosuunta Kertoo kumpaan suuntaan palikkaa käännetään
     */
    @Override
    public void kaanny(int kiertosuunta) {
        super.kaanny(kiertosuunta);
        int x = palikat.get(0).getX();
        int y = palikat.get(0).getY();

        for (int i = 1; i < 4; i++) {
            palikat.get(i).liiku(x + kaantymisInfo.get(orientaatio).get(i - 1)[0], y + kaantymisInfo.get(orientaatio).get(i - 1)[1]);
        }
    }
}
