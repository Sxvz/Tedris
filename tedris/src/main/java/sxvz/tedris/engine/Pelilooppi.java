package sxvz.tedris.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Luokka, joka huolehtii pelin pyörimisestä.
 * Perustuu Timer-luokkaan tasaisempaa syorituskykyä varten.
 * 
 * @see sxvz.tedris.engine.Paivitettava
 * @see sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija
 * @see sxvz.tedris.logic.TaysienRivienKasittelija
 * @see sxvz.tedris.gui.Piirtoalusta
 */
public class Pelilooppi extends Timer implements ActionListener {

    protected ArrayList<Paivitettava> paivitettavat;

    /**
     * Konstruktori, joka huolehtii alkuarvojen asettamisesta.
     */
    public Pelilooppi() {
        super(1000, null);

        paivitettavat = new ArrayList<>();

        addActionListener(this);
        setInitialDelay(2000);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (Paivitettava paivitettava : paivitettavat) {
            paivitettava.paivita();
        }
    }

    /**
     * Metodi päivitettävien lisäämistä varten.
     * 
     * @param p Päivitettävä-interfacen implementoiva luokka
     */
    public void lisaaPaivitettava(Paivitettava p) {
        paivitettavat.add(p);
    }
}
