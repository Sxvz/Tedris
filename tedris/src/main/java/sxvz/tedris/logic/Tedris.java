
package sxvz.tedris.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.engine.Suunta;

public class Tedris extends Timer implements ActionListener {
    private final int pelialueenLeveys;
    private final int pelialueenKorkeus;
    private boolean peliKaynnissa;
    private Paivitettava paivitettava;
    private ArrayList<Palikkakokoelma> palikat;
    private Palikkakokoelma nykyinenPalikka;

    public Tedris(int leveys, int korkeus) {
        super(1000, null);

        this.pelialueenLeveys = leveys;
        this.pelialueenKorkeus = korkeus;
        this.peliKaynnissa = true;
        palikat = new ArrayList();
        nykyinenPalikka = null;

        addActionListener(this);
        setInitialDelay(2000);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!peliKaynnissa) {
            return;
        }
        
        hallinnoiNykyistaPalikkaa();
        
        //tarkista täydet rivit

        paivitettava.paivita();
    }

    private void hallinnoiNykyistaPalikkaa() {
        if (nykyinenPalikka == null) {
            //luodaan uusi palikkaKokoelma
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
    
    
}

