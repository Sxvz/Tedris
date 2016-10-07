
package sxvz.tedris.engine;

import javax.swing.SwingUtilities;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.gui.Kayttoliittyma;
import sxvz.tedris.gui.Nappaimistonkuuntelija;
import sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija;
import sxvz.tedris.logic.TaysienRivienKasittelija;
import sxvz.tedris.logic.Vapaustarkastaja;

/**
 * Huolehtii pelin välttämättömyyksien luomisesta ja peliloopin käynnistämisestä.
 * 
 * @see sxvz.tedris.engine.Pelilooppi
 */
public class Main {
    /**
     * Main-metodi, joka luo kaiken tarpeellisen pelin käynnistämistä varten.
     * 
     * @param args 
     */
    public static void main(String[] args) {
                
        Pelilooppi looppi = new Pelilooppi();
        Pelialue alue = new Pelialue(20, 30);
        Vapaustarkastaja tarkastaja = new Vapaustarkastaja(alue);
        Nappaimistonkuuntelija kuuntelija =  new Nappaimistonkuuntelija(alue, looppi, tarkastaja);
        
        Kayttoliittyma kali = new Kayttoliittyma(alue, kuuntelija, 20);
        SwingUtilities.invokeLater(kali);

        while (kali.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole luotu");
            }
        }

        kuuntelija.setPaivitettava(kali.getPaivitettava());
        looppi.lisaaPaivitettava(new AktiivisenKokoelmanHallinnoija(alue, tarkastaja));
        looppi.lisaaPaivitettava(new TaysienRivienKasittelija(alue, tarkastaja));
        looppi.lisaaPaivitettava(kali.getPaivitettava());
        looppi.start();
    }
}
