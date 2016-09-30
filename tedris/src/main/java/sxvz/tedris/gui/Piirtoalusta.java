package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Graphics;
import javax.swing.JPanel;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Pelilooppi;

/**
 * GUI:n osa, joka piirtää pelin tilanteen näytölle.
 * 
 * @see sxvz.tedris.engine.Pelilooppi
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Pelilooppi peli;
    private int palikanKoko;

    /**
     * Konstruktori, joka välittää palikan koo piirtämistä varten.
     * 
     * @param peli
     * @param palikanKoko Palikan sivun koko pikseleinä
     */
    public Piirtoalusta(Pelilooppi peli, int palikanKoko) {
        this.peli = peli;
        this.palikanKoko = palikanKoko;
    }

    /**
     * Piirtää kaikki palikat näytölle.
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (peli.getAktiivinenPalikka() != null) {
            piirraKokoelma(g, peli.getAktiivinenPalikka());
        }
        
        if (peli.getPalikat().isEmpty()) {
            return;
        }
        
        for (Palikkakokoelma kokoelma : peli.getPalikat()) {
            piirraKokoelma(g, kokoelma);
        }
    }

    private void piirraKokoelma(Graphics g, Palikkakokoelma kokoelma) {
        g.setColor(kokoelma.getVari());
        for (Palikka p : kokoelma.getPalikat()) {
            g.fillRect(p.getX() * palikanKoko, p.getY() * palikanKoko, palikanKoko, palikanKoko);
        }
    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
