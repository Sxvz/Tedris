
package sxvz.tedris.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Kuuntelija, joka kuuntelee huipputulokset näyttävää nappia.
 */
public class TulosnapinKuuntelija implements ActionListener {
    
    private JButton tulosnappi;
    private JButton resetnappi;
    private JButton aloitusnappi;
    private Piirtoalusta piirtoalusta;
    private Tulostaulu tulostaulu;

    /**
     * Luo tarvittavat yhteydet.
     * 
     * @param tulosnappi Nappi, jota kuunnellaan
     * @param resetnappi Nappi, jonka käytettävyyttä muutetaan
     * @param aloitusnappi Nappi, jonka käytettävyyttä muutetaan
     * @param piirtoalusta Piirtoalusta, jonka paikalla tulokste näytetään
     * @param tulostaulu Tulostaulu, joka näytetään
     */
    public TulosnapinKuuntelija(JButton tulosnappi, JButton resetnappi, JButton aloitusnappi, Piirtoalusta piirtoalusta, Tulostaulu tulostaulu) {
        this.tulosnappi = tulosnappi;
        this.resetnappi = resetnappi;
        this.aloitusnappi = aloitusnappi;
        this.piirtoalusta = piirtoalusta;
        this.tulostaulu = tulostaulu;
    }

    /**
     * Vaihtelee kompinenttien näkyvyyttä tulosten näyttämiseksi.
     * 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (piirtoalusta.isVisible()) {
            aloitusnappi.setEnabled(false);
            resetnappi.setEnabled(true);
            tulosnappi.setText("Palaa");
            piirtoalusta.setVisible(false);
            tulostaulu.paivita();
            tulostaulu.setVisible(true);
        } else {
            tulostaulu.setVisible(false);
            piirtoalusta.setVisible(true);
            resetnappi.setEnabled(false);
            aloitusnappi.setEnabled(true);
            tulosnappi.setText("Huipputulokset");
        }
    }

    
}
