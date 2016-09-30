package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sxvz.tedris.engine.Pelilooppi;

/**
 * Graafisen käyttöliittymän pohjaluokka, joka toimii kaiken perustana ja
 * luo tarvittavat osat.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelilooppi peli;
    private int palikanKoko;
    private Piirtoalusta piirtoalusta;
    
    /**
     * Konstruktori, jolla määritetään pelissä olevien palikoiden koko pikseleinä.
     * 
     * @param peli Pelilooppi piirtoalustaa varten
     * @param palikanKoko Piirrettävän palikan koko pikseleinä
     */
    public Kayttoliittyma(Pelilooppi peli, int palikanKoko) {
        this.peli = peli;
        this.palikanKoko = palikanKoko;
    }

    /**
     * Luo ikkunan ja sen komponentit pelille.
     */
    @Override
    public void run() {
        frame = new JFrame("Tedris");
        int leveys = peli.getPelialueenLeveys() * palikanKoko + 10;
        int korkeus = peli.getPelialueenKorkeus() * palikanKoko + 30;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
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
