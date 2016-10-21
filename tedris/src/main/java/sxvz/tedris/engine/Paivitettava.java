package sxvz.tedris.engine;

/**
 * Interface, joka kertoo luokan olevan päivitettävissä.
 * 
 * @see sxvz.tedris.engine.Pelilooppi
 * @see sxvz.tedris.gui.Nappaimistonkuuntelija
 * @see sxvz.tedris.gui.Piirtoalusta
 * @see sxvz.tedris.gui.SeuraavanPiirtaja
 * @see sxvz.tedris.gui.Pistekentta
 * @see sxvz.tedris.gui.Tulostaulu
 * @see sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija
 * @see sxvz.tedris.logic.TaysienRivienKasittelija
 * @see sxvz.tedris.logic.Vaikeuttaja
 */
public interface Paivitettava {
    /**
     * Interfacen toteuttavien luokkien päivittämiseen käytettävä metodi.
     */
    void paivita();
}
