package sxvz.tedris.gui;

import javax.swing.JTextField;
import sxvz.tedris.engine.Paivitettava;
import sxvz.tedris.logic.Pisteenlaskenta;

/**
 * Pisteiden näyttämistä varten modattu JTextField.
 *
 * @see sxvz.tedris.engine.Paivitettava
 */
public class Pistekentta extends JTextField implements Paivitettava {

    private Pisteenlaskenta laskenta;
    private int toiminto;

    /**
     * Konstruktori, jossa muodostetaan yhteys pisteitä laskevaan luokkaan.
     *
     * @param laskenta Pisteitä laskeva luokka
     * @param toiminto Kertoo näytetäänkö kokonaispisteet vai juuri lisätyt
     */
    public Pistekentta(Pisteenlaskenta laskenta, int toiminto) {
        this.laskenta = laskenta;
        this.toiminto = toiminto;
        setHorizontalAlignment(JTextField.TRAILING);
        setEditable(false);
        setFocusable(false);
    }

    private String muodostaPisteString() {
        String etumerkki = "   ";
        String pisteet = "";
        String loppuosa = "";

        if (toiminto == 0) {
            loppuosa = Integer.toString(laskenta.getPisteet());
        } else if (toiminto == 1) {
            etumerkki = "+";
            loppuosa = Integer.toString(laskenta.getLisattavatPisteet());
        }

        pisteet += etumerkki + loppuosa;

        return pisteet;
    }

    /**
     * Päivittää kentän sisällön.
     */
    @Override
    public void paivita() {
        setText(muodostaPisteString());
    }

}
