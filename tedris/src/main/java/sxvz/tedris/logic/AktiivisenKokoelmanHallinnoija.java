package sxvz.tedris.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import sxvz.tedris.domain.NelioKokoelma;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.PitkaKokoelma;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Paivitettava;

/**
 * Luokka, joka huolehtii aktiivisesta kokoelmasta.
 *
 * @see sxvz.tedris.domain.Pelialue
 */
public class AktiivisenKokoelmanHallinnoija implements Paivitettava {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private Random random;
    private ArrayList<Color> varit;

    /**
     * Alustaa tarvittavat muuttujat ja määrittää mahdolliset palikan värit.
     *
     * @param alue Pelialue
     * @param tarkastaja Luokka, joka hoitaa törmäyksenvalvonnan
     */
    public AktiivisenKokoelmanHallinnoija(Pelialue alue, Vapaustarkastaja tarkastaja) {
        this.alue = alue;
        this.tarkastaja = tarkastaja;
        random = new Random();
        varit = new ArrayList<>();
        varit.add(Color.BLUE);
        varit.add(Color.CYAN);
        varit.add(Color.GREEN);
        varit.add(Color.MAGENTA);
        varit.add(Color.ORANGE);
        varit.add(Color.RED);
        varit.add(Color.YELLOW);
    }

    private void hallinnoiAktiivistaKokoelmaa() {
        Palikkakokoelma aktiivinen = alue.getAktiivinenKokoelma();

        if (aktiivinen == null) {
            alue.setAktiivinenKokoelma(luoUusiPalikka());
        } else {
            boolean liikkuminenOnnistuu = tarkastaja.voikoKokoelmaLiikkua(aktiivinen, Suunta.ALAS);

            if (liikkuminenOnnistuu == false) {
                alue.lisaaKokoelma(aktiivinen);
                alue.setAktiivinenKokoelma(null);
            } else {
                aktiivinen.liiku(Suunta.ALAS);
            }
        }
    }

    private Palikkakokoelma luoUusiPalikka() {
        Color vari = varit.get(random.nextInt(varit.size()));
        int x = alue.getLeveys() / 2;
        int i = random.nextInt(2);

        switch (i) {
            case 0:
                return new PitkaKokoelma(vari, x, 0);
            case 1:
                return new NelioKokoelma(vari, x, 0);
        }

        return null;
    }

    /**
     * Huolehtii aktiivisen kokoelman putoamisesta ja arpoo uuden, kun vanha
     * liimautuu lattiaan.
     */
    @Override
    public void paivita() {
        hallinnoiAktiivistaKokoelmaa();
    }
}
