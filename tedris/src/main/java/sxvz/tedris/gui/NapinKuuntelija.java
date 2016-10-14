package sxvz.tedris.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import sxvz.tedris.logic.PelitilanHallinnoija;
import sxvz.tedris.logic.Pisteenlaskenta;

/**
 * Aloitus/Lopetus -napin toiminnot aikaansaava luokka.
 * 
 * @see sxvz.tedris.logic.PelitilanHallinnoija
 * @see sxvz.tedris.logic.Pisteenlaskenta
 */
public class NapinKuuntelija implements ActionListener {
    
    private JButton nappi;
    private JComboBox vaikeusasteenValinta;
    private PelitilanHallinnoija pelitilanHallinnoija;
    private Pisteenlaskenta laskenta;
    
    /**
     * Konstruktori kuuntelijan luomiseen.
     * 
     * @param nappi Kuunneltava nappi
     * @param vaikeusasteenValinta JComboBox, jota luetaan valittu vaikeusaste
     * @param pelitilanHallinnoija Luokka, joka aloittaa/lopettaa pelin käskettäessä
     * @param laskenta Pisteenlaskennan hoitava luokka, jolle vaikeusaste välitetään
     */
    public NapinKuuntelija(JButton nappi, JComboBox vaikeusasteenValinta, PelitilanHallinnoija pelitilanHallinnoija, Pisteenlaskenta laskenta) {
        this.nappi = nappi;
        this.vaikeusasteenValinta = vaikeusasteenValinta;
        this.pelitilanHallinnoija = pelitilanHallinnoija;
        this.laskenta = laskenta;
    }
    
    /**
     * Aloittaa tai lopettaa pelin kontekstin mukaan.
     * Huolehtii, että napin teksti on tilanteen mukainen ja
     * disabloi tai enabloi vaikeusaste valikon tarvittaessa.
     * 
     * @param ae Napinpainallus
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (nappi.getText().equals("Luovuta")) {
            luovuta();
        } else {
            aloita();
        }
    }

    private void aloita() {
        int vaikeusaste = vaikeusasteenValinta.getSelectedIndex();
        
        laskenta.setVaikeusaste(vaikeusaste);
        
        int viive = 500;
        if (vaikeusaste == 0) {
            viive = 1000;
        } else if (vaikeusaste == 2) {
            viive = 200;
        }
        
        pelitilanHallinnoija.aloitaPeli(viive);
        nappi.setText("Luovuta");
        vaikeusasteenValinta.setEnabled(false);
    }

    private void luovuta() {
        pelitilanHallinnoija.lopetaPeli();
        nappi.setText("Aloita");
        vaikeusasteenValinta.setEnabled(true);
    }
    
}
