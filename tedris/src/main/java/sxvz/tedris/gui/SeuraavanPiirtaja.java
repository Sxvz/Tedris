package sxvz.tedris.gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.engine.Paivitettava;

/**
 * GUI:n osa, joka piirtää seuraavaksi vuorossa olevan kokoelman näytölle.
 * Pohjautuu Piirtoalustaan, mutta ei teknisistä syistä extendaa sitä 
 * (mm. super.paintComponent() rikkoutuu).
 *
 * @see sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija
 * @see sxvz.tedris.gui.Piirtoalusta
 * @see sxvz.tedris.engine.Paivitettava
 */
public class SeuraavanPiirtaja extends JPanel implements Paivitettava {

    private int palikanKoko;
    private Pelialue alue;

    /**
     * Konstruktori, joka välittää palikan koon piirtämistä varten.
     *
     * @param palikanKoko Palikan sivun koko pikseleinä
     * @param alue Pelialue, jolta seuraava kokoelma piirretään
     */
    public SeuraavanPiirtaja(int palikanKoko, Pelialue alue) {
        this.palikanKoko = palikanKoko;
        this.alue = alue;
    }

    /**
     * Piirtää seuraavan kokoelman näytölle.
     *
     * @param g Grafiikka, johon piirretään
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (alue.getSeuraavaKokoelma() != null) {
            piirraKokoelma(g, alue.getSeuraavaKokoelma());
        }
    }

    private void piirraKokoelma(Graphics g, Palikkakokoelma kokoelma) {
        g.setColor(kokoelma.getVari());
        for (Palikka p : kokoelma.getPalikat()) {
            g.fill3DRect((p.getX() - alue.getLeveys() / 2 + 5) * palikanKoko - (palikanKoko / 2), (p.getY() + 4) * palikanKoko, palikanKoko, palikanKoko, true);
        }
    }

    @Override
    public void paivita() {
        super.repaint();
    }

}
