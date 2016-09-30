
package sxvz.tedris.domain;

import sxvz.tedris.engine.Paivitettava;

/**
 * Piirtoalustan peliloopissa korvaava luokka, jos ohjelmaa halutaan testata ilman GUI:ta.
 * 
 * @see sxvz.tedris.engine.Paivitettava
 * @see sxvz.tedris.gui.Piirtoalusta
 * @see sxvz.tedris.engine.Pelilooppi
 */
public class DummyPaivitettava implements Paivitettava {

    /**
     * Tyhjä metodi GUI:tonta testausta varten.
     */
    @Override
    public void paivita() {
    }

}
