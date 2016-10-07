package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sxvz.tedris.domain.Pelialue;
/**
 * Graafisen käyttöliittymän pohjaluokka, joka toimii kaiken perustana ja
 * luo tarvittavat osat.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelialue alue;
    private Nappaimistonkuuntelija kuuntelija;
    private int palikanKoko;
    private Piirtoalusta piirtoalusta;
    
    /**
     * Konstruktori, jolla määritetään pelissä olevien palikoiden koko pikseleinä.
     * 
     * @param alue Pelialue, joka piirretään
     * @param kuuntelija Näppämistönkuuntelija, joka liitetään frameen;
     * @param palikanKoko Piirrettävän palikan koko pikseleinä
     */
    public Kayttoliittyma(Pelialue alue, Nappaimistonkuuntelija kuuntelija, int palikanKoko) {
        this.alue = alue;
        this.kuuntelija = kuuntelija;
        this.palikanKoko = palikanKoko;
    }

    /**
     * Luo ikkunan ja sen komponentit pelille.
     */
    @Override
    public void run() {
        frame = new JFrame("Tedris");
        int leveys = alue.getLeveys() * palikanKoko + 10;
        int korkeus = alue.getKorkeus() * palikanKoko + 30;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(alue, palikanKoko);
        container.add(piirtoalusta);
        frame.addKeyListener(kuuntelija);
    }


    public JFrame getFrame() {
        return frame;
    }
    
    public Paivitettava getPaivitettava() {
        return (Paivitettava) piirtoalusta;
    }
}
