package sxvz.tedris.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import sxvz.tedris.domain.NelioPalikka;
import sxvz.tedris.domain.Palikkakokoelma;
import sxvz.tedris.domain.PitkaPalikka;
import sxvz.tedris.domain.Suunta;
import sxvz.tedris.engine.Pelilooppi;

public class AktiivisenPalikanHallinnoija {

    private Pelilooppi peli;
    private Random random;
    private ArrayList<Color> varit;

    public AktiivisenPalikanHallinnoija(Pelilooppi peli) {
        this.peli = peli;
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

    public void hallinnoiAktiivistaPalikkaa(Palikkakokoelma aktiivinen) {
        if (aktiivinen == null) {
            peli.setAktiivinenPalikka(luoUusiPalikka());
        } else {
            boolean liikkuminenOnnistuu = peli.getVapaustarkastaja().voikoKokoelmaLiikkua(aktiivinen, Suunta.ALAS);

            if (liikkuminenOnnistuu == false) {
                peli.lisaaPalikoita(aktiivinen);
                peli.setAktiivinenPalikka(null);
            } else {
                aktiivinen.liiku(Suunta.ALAS);
            }
        }
    }

    private Palikkakokoelma luoUusiPalikka() {
        Color vari = varit.get(random.nextInt(varit.size()));
        int x = peli.getPelialueenLeveys() / 2;
        int i = random.nextInt(2);

        switch (i) {
            case 0:
                return new PitkaPalikka(vari, x, 0);
            case 1:
                return new NelioPalikka(vari, x, 0);
        }
        
        return null;
    }
}
