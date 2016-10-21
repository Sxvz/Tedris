
package sxvz.tedris.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import sxvz.tedris.logic.Huipputulokset;

/**
 * Kuuntelija, joka valvoo huipputulokset resetoivaa nappia.
 */
public class ResetnapinKuuntelija implements ActionListener {

    private Frame frame;
    private Huipputulokset tulokset;
    private Tulostaulu tulostaulu;

    /**
     * Luo tarvittavat yhteydet.
     * 
     * @param frame Frame, johon varmistusikkuna liittyy
     * @param tulokset Luokka, joka hallinnoi huipputuloksia
     * @param tulostaulu Tulostaulu, joka päivitetään
     */
    public ResetnapinKuuntelija(Frame frame, Huipputulokset tulokset, Tulostaulu tulostaulu) {
        this.frame = frame;
        this.tulokset = tulokset;
        this.tulostaulu = tulostaulu;
    }
    
    /**
     * Poistaa vanhat huipputulokset, kun nappia painetaan, jos käyttäjä
     * vahvistaa toiminnon.
     * Kun reset-nappia painetaan, kysyy ponnahdusikkunalla varmistusta.
     * Päivittää tulostaulun, jos se resetoidaan.
     * 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        varmistaJaResetoi();
    }

    private void varmistaJaResetoi() {
        int vastaus = JOptionPane.showConfirmDialog(frame,
                "Haluatko varmasti\n"
                + "resetoida Huipputulokset?",
                "Varoitus",
                JOptionPane.OK_CANCEL_OPTION,
                2);

        if (vastaus == 0) {
            tulokset.resetoi();
            tulostaulu.paivita();
        }
    }

}
