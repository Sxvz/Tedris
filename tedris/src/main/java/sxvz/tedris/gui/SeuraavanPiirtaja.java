package sxvz.tedris.gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Paivitettava;

/**
 * GUI:n osa, joka piirtää seuraavan kokoelman näytölle. Pohjautuu
 * Piirtoalustaan, mutta ei teknisistä syistä extendaa sitä.
 *
 * @see sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija
 * @see sxvz.tedris.gui.Piirtoalusta
 */
public class SeuraavanPiirtaja extends JPanel implements Paivitettava {

    private int palikanKoko;
    private Palikkakokoelma seuraavaKokoelma;

    /**
     * Konstruktori, joka välittää palikan koon piirtämistä varten.
     *
     * @param palikanKoko Palikan sivun koko pikseleinä
     */
    public SeuraavanPiirtaja(int palikanKoko) {
        this.palikanKoko = palikanKoko;
    }

    /**
     * Piirtää seuraavan kokoelman näytölle.
     *
     * @param g Grafiikka, johon piirretään
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (seuraavaKokoelma != null) {
            piirraKokoelma(g, seuraavaKokoelma);
        }
    }

    protected void piirraKokoelma(Graphics g, Palikkakokoelma kokoelma) {
        g.setColor(kokoelma.getVari());
        for (Palikka p : kokoelma.getPalikat()) {
            g.fillRect((p.getX() - 5) * palikanKoko - (palikanKoko / 2), (p.getY() + 4) * palikanKoko, palikanKoko, palikanKoko);
        }
    }

    public void setSeuraavaKokoelma(Palikkakokoelma seuraavaKokoelma) {
        this.seuraavaKokoelma = seuraavaKokoelma;
    }

    @Override
    public void paivita() {
        super.repaint();
    }

}
