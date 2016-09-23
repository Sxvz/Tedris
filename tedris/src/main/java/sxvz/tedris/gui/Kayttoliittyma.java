package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sxvz.tedris.logic.Nappaimistonkuuntelija;
import sxvz.tedris.engine.Pelilooppi;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelilooppi peli;
    private int palikanKoko;
    private Piirtoalusta piirtoalusta;

    public Kayttoliittyma(Pelilooppi peli, int palikanKoko) {
        this.peli = peli;
        this.palikanKoko = palikanKoko;
    }

    @Override
    public void run() {
        frame = new JFrame("Tedris");
        int leveys = (peli.getPelialueenLeveys() + 1) * palikanKoko + 10;
        int korkeus = (peli.getPelialueenKorkeus() + 2) * palikanKoko + 10;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(peli, palikanKoko);
        container.add(piirtoalusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(peli));
    }


    public JFrame getFrame() {
        return frame;
    }
    
    public Paivitettava getPaivitettava() {
        return (Paivitettava) piirtoalusta;
    }
}
