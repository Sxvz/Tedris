package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Graphics;
import javax.swing.JPanel;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;

/**
 * GUI:n osa, joka piirtää pelitilanteen näytölle.
 * 
 * @see sxvz.tedris.domain.Pelialue
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    protected Pelialue alue;
    protected int palikanKoko;

    /**
     * Konstruktori, joka välittää palikan koon piirtämistä varten.
     * 
     * @param alue Pelialue, jonka tilanne piirretään
     * @param palikanKoko Palikan sivun koko pikseleinä
     */
    public Piirtoalusta(Pelialue alue, int palikanKoko) {
        this.alue = alue;
        this.palikanKoko = palikanKoko;
    }

    /**
     * Piirtää kaikki palikat näytölle.
     * 
     * @param g Grafiikka, johon piirretään
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (alue.getAktiivinenKokoelma() != null) {
            piirraKokoelma(g, alue.getAktiivinenKokoelma());
        }
        
        for (Palikkakokoelma kokoelma : alue.getKokoelmat()) {
            piirraKokoelma(g, kokoelma);
        }
    }

    protected void piirraKokoelma(Graphics g, Palikkakokoelma kokoelma) {
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
