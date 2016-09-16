package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import sxvz.tedris.logic.Tedris;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Tedris peli;
    private int palikanKoko;

    public Piirtoalusta(Tedris peli, int palikanKoko) {
        this.peli = peli;
        this.palikanKoko = palikanKoko;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        
    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
