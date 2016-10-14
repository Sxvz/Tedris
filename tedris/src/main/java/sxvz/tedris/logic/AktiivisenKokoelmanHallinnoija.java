package sxvz.tedris.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import sxvz.tedris.domain.KaanteinenLKokoelma;
import sxvz.tedris.domain.KaanteinenPorrasKokoelma;
import sxvz.tedris.domain.LKokoelma;
import sxvz.tedris.domain.NelioKokoelma;
import sxvz.tedris.domain.Palikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.domain.PitkaKokoelma;
import sxvz.tedris.domain.PorrasKokoelma;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.domain.TKokoelma;
import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.gui.NapinKuuntelija;

/**
 * Luokka, joka huolehtii aktiivisesta kokoelmasta.
 * Lopettaa pelin painamalla luovutusnappia,
 * jos uudelle palikalle ei ole tilaa.
 *
 * @see sxvz.tedris.domain.Pelialue
 * @see sxvz.tedris.logic.Vapaustarkastaja
 * @see sxvz.tedris.gui.NapinKuuntelija
 * @see sxvz.tedris.engine.Paivitettava
 */
public class AktiivisenKokoelmanHallinnoija implements Paivitettava {

    private Pelialue alue;
    private Vapaustarkastaja tarkastaja;
    private NapinKuuntelija napinKuuntelija;
    private Random random;
    private ArrayList<Color> varit;

    /**
     * Alustaa tarvittavat muuttujat ja määrittää mahdolliset palikan värit.
     *
     * @param alue Pelialue
     * @param tarkastaja Luokka, joka hoitaa törmäyksenvalvonnan
     * @param napinKuuntelija Luovuttamisnapin kuuntelija
     * @param random arpoja
     */
    public AktiivisenKokoelmanHallinnoija(Pelialue alue, Vapaustarkastaja tarkastaja, NapinKuuntelija napinKuuntelija, Random random) {
        this.alue = alue;
        this.tarkastaja = tarkastaja;
        this.napinKuuntelija = napinKuuntelija;
        this.random = random;
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

        if (alue.getSeuraavaKokoelma() == null) {
            luoSeuraavaKokoelma();
        }

        if (aktiivinen == null) {
            if (!onkoTilaaUudelleAktiiviselle()) {
                return;
            }
            
            alue.setAktiivinenKokoelma(alue.getSeuraavaKokoelma());
            luoSeuraavaKokoelma();
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

    private boolean onkoTilaaUudelleAktiiviselle() {
        for (Palikka p : alue.getSeuraavaKokoelma().getPalikat()) {
            if (!tarkastaja.voikoLiikkua(alue.getSeuraavaKokoelma(), p.getX(), p.getY())) {
                napinKuuntelija.actionPerformed(null);
                return false;
            }
        }
        return true;
    }

    private void luoSeuraavaKokoelma() {
        alue.setSeuraavaKokoelma(luoUusiKokoelma());
    }

    private Palikkakokoelma luoUusiKokoelma() {
        Color vari = varit.get(random.nextInt(varit.size()));
        int x = alue.getLeveys() / 2;
        int i = random.nextInt(7);

        switch (i) {
            case 0:
                return new PitkaKokoelma(vari, x, 0);
            case 1:
                return new NelioKokoelma(vari, x, 0);
            case 2:
                return new LKokoelma(vari, x, 1);
            case 3:
                return new KaanteinenLKokoelma(vari, x, 0);
            case 4:
                return new PorrasKokoelma(vari, x, 1);
            case 5:
                return new KaanteinenPorrasKokoelma(vari, x, 1);
            case 6:
                return new TKokoelma(vari, x, 0);
        }

        return null;
    }

    /**
     * Huolehtii aktiivisen kokoelman putoamisesta ja arpoo uuden, kun vanha
     * liimautuu lattiaan. Jos uudelle kokoelmalle ei ole tilaa painetaan
     * luovuttamisnappia.
     */
    @Override
    public void paivita() {
        hallinnoiAktiivistaKokoelmaa();
    }
}
