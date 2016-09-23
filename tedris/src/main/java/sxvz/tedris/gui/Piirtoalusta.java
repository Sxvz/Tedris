package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Pelilooppi;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Pelilooppi peli;
    private int palikanKoko;

    public Piirtoalusta(Pelilooppi peli, int palikanKoko) {
        this.peli = peli;
        this.palikanKoko = palikanKoko;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (peli.getNykyinenPalikka() != null) {
            piirraKokoelma(g, peli.getNykyinenPalikka());
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
