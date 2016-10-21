package sxvz.tedris.logic;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Luokka, joka tarjoaa työkalut tiedostojen rivipohjaiseen lukemiseen ja
 * kirjoittamiseen.
 */
public class Tiedostotyokalut {

    private File tiedosto;

    /**
     * Konstruktori, jolle kerrotaan käsiteltävä tiedosto.
     * Jos tiedostoa ei ole olemassa, se luodaan.
     * 
     * @param tiedosto Käsiteltävä tiedosto
     */
    public Tiedostotyokalut(File tiedosto) {
        this.tiedosto = tiedosto;

        luoTiedostoJosEiOlemassa();
    }

    /**
     * Luo käsiteltävän tiedoston, jos sitä ei ole olemassa.
     */
    public void luoTiedostoJosEiOlemassa() {
        try {
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
            }
        } catch (Exception e) {
        }
    }

    /**
     * Metodi, joka lukee koko tiedoston rivi riviltä.
     * 
     * @return Luetut rivit
     */
    public ArrayList<String> lue() {
        ArrayList<String> luetut = new ArrayList<>();

        try {
            BufferedReader lukija = new BufferedReader(new FileReader(tiedosto));

            String rivi = lukija.readLine();
            while (rivi != null) {
                luetut.add(rivi);
                rivi = lukija.readLine();
            }
            
            lukija.close();
        } catch (Exception e) {
        }

        return luetut;
    }

    /**
     * Kirjoittaa, jokaisen arraylistin indeksin omalle rivilleen.
     * Kirjoittaa olemassa olevan tiedoston päälle.
     * 
     * @param kirjoitettavat Kirjoitettavat rivit
     */
    public void kirjoita(ArrayList<String> kirjoitettavat) {
        try {
            BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(tiedosto));

            for (String rivi : kirjoitettavat) {
                kirjoittaja.write(rivi);
                kirjoittaja.newLine();
            }
            
            kirjoittaja.flush();
            kirjoittaja.close();
        } catch (Exception e) {
        }
    }
    
    /**
     * Poistaa käsiteltävän tiedoston.
     */
    public void poista() {
        tiedosto.delete();
    }
}
