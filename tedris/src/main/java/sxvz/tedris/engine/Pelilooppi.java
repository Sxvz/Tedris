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
 */
public class Pelilooppi extends Timer implements ActionListener {

    protected ArrayList<Paivitettava> paivitettavat;

    /**
     * Konstruktori, joka huolehtii alkuarvojen asettamisesta.
     * 
     * @param viive Aika looppien välillä
     */
    public Pelilooppi(int viive) {
        super(viive, null);

        paivitettavat = new ArrayList<>();

        addActionListener(this);
        setInitialDelay(100);
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
