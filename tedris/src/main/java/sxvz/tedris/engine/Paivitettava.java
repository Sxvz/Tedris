package sxvz.tedris.engine;

/**
 * Interface, joka kertoo mitä luokan, jota pelilooppi päivittää on tehtävä.
 * 
 * @see sxvz.tedris.gui.Piirtoalusta
 * @see sxvz.tedris.engine.Pelilooppi
 */
public interface Paivitettava {
    void paivita();
}
