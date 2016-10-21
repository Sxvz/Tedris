package sxvz.tedris.gui;

import java.awt.Font;
import javax.swing.JTextArea;
import sxvz.tedris.domain.Tulos;
import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.logic.Huipputulokset;

/**
 * Luokka huipputulosten näyttämiseen.
 * 
 * @see sxvz.tedris.engine.Paivitettava
 */
public class Tulostaulu extends JTextArea implements Paivitettava {

    private Huipputulokset huipputulokset;

    /**
     * Luo yhteyden huipputuloksia hallinnoivaan luokkaan.
     * Huolehtii siitä, että käytetyssä fontissa kirjaimet ovat
     * saman kokoisia.
     * 
     * @param huipputulokset Huipputuloksia hallinnoiva luokka
     */
    public Tulostaulu(Huipputulokset huipputulokset) {
        this.huipputulokset = huipputulokset;
        setFont(new Font("monospaced", Font.PLAIN, 12));
    }

    /**
     * Pyytää huipputulokset ja näyttää ne.
     */
    @Override
    public void paivita() {
        int sijoitus = 1;
        String naytettava = "";

        for (Tulos tulos : huipputulokset.lueHuipputulokset()) {
            naytettava += sijoitus + ".";
            if (tulos == null) {
                naytettava += "\n";
            } else if (sijoitus == 10) {
                naytettava += " " + tulos + "\n";
            } else {
                naytettava += "  " + tulos + "\n";
            }
            sijoitus++;
        }

        setText(naytettava);
    }

}
