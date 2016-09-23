
package sxvz.tedris.engine;

import javax.swing.SwingUtilities;
import sxvz.tedris.gui.Kayttoliittyma;
import sxvz.tedris.logic.TaysienRivienTunnistaja;
import sxvz.tedris.logic.Vapaustarkastaja;

public class Main {
    public static void main(String[] args) {
                
        Pelilooppi peli = new Pelilooppi(20,30);
        peli.setVapaustarkastaja(new Vapaustarkastaja(peli));
        peli.setTaysienRivienTunnistaja(new TaysienRivienTunnistaja(peli));

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
