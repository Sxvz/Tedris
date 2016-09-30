package sxvz.tedris.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.logic.AktiivisenPalikanHallinnoija;
import sxvz.tedris.logic.TaysienRivienKasittelija;
import sxvz.tedris.logic.Vapaustarkastaja;

public class Pelilooppi extends Timer implements ActionListener {

    private final int pelialueenLeveys;
    private final int pelialueenKorkeus;
    private boolean peliKaynnissa;
    private Paivitettava paivitettava;
    private ArrayList<Palikkakokoelma> palikat;
    private Palikkakokoelma aktiivinenPalikka;
    private int countteri;
    private Vapaustarkastaja tarkastaja;
    private TaysienRivienKasittelija rivienKasittelija;
    private AktiivisenPalikanHallinnoija aktiivisenHallinnoija;

    public Pelilooppi(int leveys, int korkeus) {
        super(100, null);

        this.pelialueenLeveys = leveys;
        this.pelialueenKorkeus = korkeus;
        this.peliKaynnissa = true;
        palikat = new ArrayList();
        aktiivinenPalikka = null;
        countteri = 9;

        addActionListener(this);
        setInitialDelay(2000);
        
        tarkastaja = new Vapaustarkastaja(this);
        rivienKasittelija = new TaysienRivienKasittelija(this);
        aktiivisenHallinnoija = new AktiivisenPalikanHallinnoija(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!peliKaynnissa) {
            stop();
            return;
        }

        if (countteri == 9) {
            aktiivisenHallinnoija.hallinnoiAktiivistaPalikkaa(aktiivinenPalikka);
            rivienKasittelija.kasitteleTaydetRivit();

            countteri = 0;
        }

        paivitettava.paivita();
        countteri++;
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

    public void setAktiivinenPalikka(Palikkakokoelma aktiivinenPalikka) {
        this.aktiivinenPalikka = aktiivinenPalikka;
    }

    public Palikkakokoelma getAktiivinenPalikka() {
        return aktiivinenPalikka;
    }

    public ArrayList<Palikkakokoelma> getPalikat() {
        return palikat;
    }

    public Vapaustarkastaja getVapaustarkastaja() {
        return tarkastaja;
    }

    public void setPeliKaynnissa(boolean peliKaynnissa) {
        this.peliKaynnissa = peliKaynnissa;
    }

    protected int getCountteri() {
        return countteri;
    }

    protected void setCountteri(int countteri) {
        this.countteri = countteri;
    }

    public void setPalikat(ArrayList<Palikkakokoelma> palikat) {
        this.palikat = palikat;
    }

    public TaysienRivienKasittelija getTaysienRivienKasittelija() {
        return rivienKasittelija;
    }

    public AktiivisenPalikanHallinnoija getAktiivisenPalikanHallinnoija() {
        return aktiivisenHallinnoija;
    }
    
    
}
