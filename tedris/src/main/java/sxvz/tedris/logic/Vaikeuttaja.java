
package sxvz.tedris.logic;

import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.engine.Pelilooppi;

/**
 * Pelinaikaisesta vaikeutumisesta huolehtiva luokka.
 * Vaikeuttaa peliä aina, kun kutsutaan.
 * 
 * @see sxvz.tedris.engine.Paivitettava
 * @see sxvz.tedris.engine.Pelilooppi
 * @see sxvz.tedris.logic.TaysienRivienKasittelija
 */
public class Vaikeuttaja implements Paivitettava {

    private Pelilooppi looppi;
    private double vaikeutuskerroin;

    /**
     * Luo uuden Vaikeuttajan annetulla kertoimella.
     * 
     * @param looppi Looppi, jonka viivettä manipuloidaan
     * @param vaikeutuskerroin Kerroin, jolla viivettä manipuloidaan
     */
    public Vaikeuttaja(Pelilooppi looppi, double vaikeutuskerroin) {
        this.looppi = looppi;
        this.vaikeutuskerroin = vaikeutuskerroin;
    }
    
    /**
     * Kertoo loopin viiveen vaikeutuskertoimella.
     */
    @Override
    public void paivita() {
        looppi.setDelay((int) (looppi.getDelay() * vaikeutuskerroin));
    }

}
