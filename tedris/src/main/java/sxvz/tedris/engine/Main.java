
package sxvz.tedris.engine;

import javax.swing.SwingUtilities;
import sxvz.tedris.gui.Kayttoliittyma;

/**
 * Huolehtii pelin välttämättömyyksien luomisesta ja pelin käynistymisestä.
 * 
 * @see sxvz.tedris.engine.Pelilooppi
 */
public class Main {
    public static void main(String[] args) {
                
        Pelilooppi peli = new Pelilooppi(20, 30);

        Kayttoliittyma kali = new Kayttoliittyma(peli, 20);
        SwingUtilities.invokeLater(kali);

        while (kali.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole luotu");
            }
        }

        peli.setPaivitettava(kali.getPaivitettava());
        peli.start();
    }
}
