package sxvz.tedris.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import sxvz.tedris.domain.Debugkokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.logic.TaysienRivienTunnistaja;
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
    private TaysienRivienTunnistaja rivienTunnistaja;

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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!peliKaynnissa) {
            stop();
            return;
        }

        if (countteri == 9) {
            hallinnoiNykyistaPalikkaa();
            kasitteleTaydetRivit();
            
            countteri = 0;
        }

        paivitettava.paivita();
        countteri++;
    }

    private void kasitteleTaydetRivit() {
        ArrayList<Integer> taydetRivit = rivienTunnistaja.etsiTaydetRivit();
        if (!taydetRivit.isEmpty()) {
            //tuhoa palikat joiden y == listan numero
            return;
        }
    }

    private void hallinnoiNykyistaPalikkaa() {
        if (aktiivinenPalikka == null) {
            //placeholder
            aktiivinenPalikka = new Debugkokoelma(new Palikka(pelialueenLeveys / 2, 0), new Palikka(pelialueenLeveys / 2 + 1, 0));
        } else {
            boolean liikkuminenOnnistuu = tarkastaja.voikoKokoelmaLiikkua(aktiivinenPalikka, Suunta.ALAS);

            if (liikkuminenOnnistuu == false) {
                palikat.add(aktiivinenPalikka);
                aktiivinenPalikka = null;
            } else {
                aktiivinenPalikka.liiku(Suunta.ALAS);
            }
        }
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

    public void setVapaustarkastaja(Vapaustarkastaja tarkastaja) {
        this.tarkastaja = tarkastaja;
    }

    public Vapaustarkastaja getVapaustarkastaja() {
        return tarkastaja;
    }

    public void setTaysienRivienTunnistaja(TaysienRivienTunnistaja rivienTunnistaja) {
        this.rivienTunnistaja = rivienTunnistaja;
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

    
}
