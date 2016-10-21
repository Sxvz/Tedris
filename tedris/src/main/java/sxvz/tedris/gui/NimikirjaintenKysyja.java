package sxvz.tedris.gui;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 * Luokka, jolla käyttäjältä voidaan kysyä nimikirjaimia ponnahdusikkunan avulla.
 */
public class NimikirjaintenKysyja {

    private Frame frame;
    private String paateksti;
    private String palauteteksti;
    private int maksimipituus;

    /**
     * Luo yhteyden Frameen ja määrittää ponnahdusikkunan tarvitsemat muuttujat.
     * 
     * @param frame Frame, johon ponnahdusikkuna liittyy
     */
    public NimikirjaintenKysyja(Frame frame) {
        this.frame = frame;
        paateksti = "Tuloksesi pääsee top 10 sijoituksiin!\n"
                + "Anna nimimerkkisi alla.\n"
                + "(max 3 merkkiä, ei pelkkiä välilyöntejä)";
        palauteteksti = "Nimimerkki voi olla enintään 3 merkkiä pitkä\n"
                + "eikä pelkkien välilyöntien antaminen ole sallittuja";
        maksimipituus = 3;
    }

    private String kysy(String teksti) {
        String syote = (String) JOptionPane.showInputDialog(frame,
                teksti,
                null,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        if (syote == null) {
            return null;
        }
        
        syote = varmistaSyotteenOikeellisuus(syote);

        return syote;
    }

    private String varmistaSyotteenOikeellisuus(String syote) {
        if (syote == null) {
            return null;
        }
        
        if (syote.length() > maksimipituus || syote.isEmpty() || syote.equals(" ") || syote.equals("  ") || syote.equals("   ")) {
            syote = kysy(palauteteksti);
        }
        
        if (syote == null) {
            return null;
        }
        
        while (syote.length() < maksimipituus) {
            syote += " ";
        }
        
        return syote;
    }
    
    /**
     * Kysyy käyttäjältä max 3 merkin pituista inputtia ponnahdusikkunalla.
     * Varmistaa syötteen oikeellisuuden. Jos annettu syöte virhellinen
     * kysyy uudestaan.
     * 
     * @return Annettu syöte
     */
    public String kysyNimikirjaimia() {
        return kysy(paateksti);
    }
}
