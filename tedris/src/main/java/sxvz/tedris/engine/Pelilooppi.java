
package sxvz.tedris.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Suunta;

public class Pelilooppi extends Timer implements ActionListener {
    private final int pelialueenLeveys;
    private final int pelialueenKorkeus;
    private boolean peliKaynnissa;
    private Paivitettava paivitettava;
    private ArrayList<Palikkakokoelma> palikat;
    private Palikkakokoelma nykyinenPalikka;
    private int countteri;

    public Pelilooppi(int leveys, int korkeus) {
        super(100, null);

        this.pelialueenLeveys = leveys;
        this.pelialueenKorkeus = korkeus;
        this.peliKaynnissa = true;
        palikat = new ArrayList();
        nykyinenPalikka = null;
        countteri = 9;

        addActionListener(this);
        setInitialDelay(2000);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!peliKaynnissa) {
            return;
        }
        
        if (countteri == 9) {
            hallinnoiNykyistaPalikkaa();
            countteri = 0;
        }
        
        //tarkista täydet rivit

        paivitettava.paivita();
        countteri++;
    }

    private void hallinnoiNykyistaPalikkaa() {
        if (nykyinenPalikka == null) {
            nykyinenPalikka = new Debugkokoelma(new Palikka(pelialueenLeveys / 2, 0, this), new Palikka(pelialueenLeveys / 2 + 1, 0, this));
        } else {
            if (false == nykyinenPalikka.liiku(Suunta.ALAS)) {
                palikat.add(nykyinenPalikka);
                nykyinenPalikka = null;
            }
        }
    }
    
    public boolean onkoVapaa(int x, int y) {
        
        if (x <= -1 || x > pelialueenLeveys || y <= -1 || y > pelialueenKorkeus) {
            return false;
        }
        
        for (Palikkakokoelma palikkakokoelma : palikat) {
            for (Palikka palikka : palikkakokoelma.getPalikat()) {
                if (palikka.getX() == x && palikka.getY() == y) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public int getPelialueenLeveys() {
        return pelialueenLeveys;
    }

    public int getPelialueenKorkeus() {
        return pelialueenKorkeus;
    }
    
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean isPeliKaynnissa() {
        return peliKaynnissa;
    }
    
    public void lisaaPalikoita(Palikkakokoelma p) {
        palikat.add(p);
    }

    public void setNykyinenPalikka(Palikkakokoelma nykyinenPalikka) {
        this.nykyinenPalikka = nykyinenPalikka;
    }

    public Palikkakokoelma getNykyinenPalikka() {
        return nykyinenPalikka;
    }

    public ArrayList<Palikkakokoelma> getPalikat() {
        return palikat;
    }
    
    
}
