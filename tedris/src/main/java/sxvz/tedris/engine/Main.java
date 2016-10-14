package sxvz.tedris.engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.gui.NapinKuuntelija;
import sxvz.tedris.gui.Nappaimistonkuuntelija;
import sxvz.tedris.gui.Piirtoalusta;
import sxvz.tedris.gui.Pistekentta;
import sxvz.tedris.gui.SeuraavanPiirtaja;
import sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija;
import sxvz.tedris.logic.PelitilanHallinnoija;
import sxvz.tedris.logic.Pisteenlaskenta;
import sxvz.tedris.logic.TaysienRivienKasittelija;
import sxvz.tedris.logic.Vapaustarkastaja;

/**
 * Huolehtii pelin välttämättömyyksien luomisesta.
 */
public class Main {

    /**
     * Main-metodi, joka luo kaiken tarpeellisen pelin käynnistämistä varten.
     *
     * @param args Ei merkitystä tässä ohjelmassa
     */
    public static void main(String[] args) {

//        Pelilooppi looppi = new Pelilooppi(1000);
//        Pelialue alue = new Pelialue(20, 30);
//        Vapaustarkastaja tarkastaja = new Vapaustarkastaja(alue);
//        Nappaimistonkuuntelija nappaimistonKuuntelija =  new Nappaimistonkuuntelija(alue, looppi, tarkastaja);
//        
//        Kayttoliittyma kali = new Kayttoliittyma(alue, nappaimistonKuuntelija, 20);
//        SwingUtilities.invokeLater(kali);
//
//        while (kali.getPaivitettava() == null) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//            }
//        }
//
//        looppi.lisaaPaivitettava(new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, kali.getSeuraavanPiirtaja()));
//        looppi.lisaaPaivitettava(new TaysienRivienKasittelija(alue, tarkastaja));
//        looppi.lisaaPaivitettava(kali.getPaivitettava());
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //logiikka
                Pelilooppi looppi = new Pelilooppi(1000);
                Pelialue alue = new Pelialue(20, 30);
                Vapaustarkastaja tarkastaja = new Vapaustarkastaja(alue);
                Pisteenlaskenta laskenta = new Pisteenlaskenta(alue.getLeveys());
                PelitilanHallinnoija pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta);
                
                //GUI
                int palikanKoko = 20;
                JFrame frame = new JFrame("Tedris");
                Insets insets = frame.getInsets();
                int paaAlustanLeveys = alue.getLeveys() * palikanKoko + insets.left + insets.right;
                int paaAlustanKorkeus = alue.getKorkeus() * palikanKoko + insets.top + insets.bottom;
                int sivuPaneelinLeveys = 200;

                frame.setPreferredSize(new Dimension(paaAlustanLeveys + sivuPaneelinLeveys, paaAlustanKorkeus));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setResizable(false);

                Nappaimistonkuuntelija nappaimistonKuuntelija = new Nappaimistonkuuntelija(alue, pelitilanHallinnoija, tarkastaja);
                frame.addKeyListener(nappaimistonKuuntelija);

                Container container = frame.getContentPane();
                container.setLayout(null);

                Piirtoalusta piirtoalusta = new Piirtoalusta(alue, palikanKoko);
                piirtoalusta.setBounds(insets.left, insets.top, alue.getLeveys() * palikanKoko, alue.getKorkeus() * palikanKoko);
                piirtoalusta.setBackground(Color.WHITE);
                container.add(piirtoalusta);

                nappaimistonKuuntelija.setPaivitettava(piirtoalusta);

                JSeparator menunErottaja = new JSeparator(SwingConstants.VERTICAL);
                int menunErottajanLeveys = 5;
                menunErottaja.setBounds(paaAlustanLeveys, insets.top, menunErottajanLeveys, paaAlustanKorkeus);
                container.add(menunErottaja);

                SeuraavanPiirtaja seuraavanPiirtaja = new SeuraavanPiirtaja(palikanKoko, alue);
                seuraavanPiirtaja.setBounds(paaAlustanLeveys + menunErottajanLeveys, insets.top, sivuPaneelinLeveys - menunErottajanLeveys - 3 - insets.right, sivuPaneelinLeveys);
                seuraavanPiirtaja.setBackground(Color.WHITE);
                container.add(seuraavanPiirtaja);

                JPanel sivuPaneeli = new JPanel();
                sivuPaneeli.setBounds(paaAlustanLeveys + menunErottajanLeveys, insets.top + sivuPaneelinLeveys, sivuPaneelinLeveys - 8 - insets.right, paaAlustanKorkeus - sivuPaneelinLeveys);
                sivuPaneeli.setLayout(new GridLayout(6, 1));
                container.add(sivuPaneeli);

                Pistekentta kokonaispistekentta = new Pistekentta(laskenta, 0);
                sivuPaneeli.add(kokonaispistekentta);

                Pistekentta lisattyjenPisteidenKentta = new Pistekentta(laskenta, 1);
                sivuPaneeli.add(lisattyjenPisteidenKentta);

                Color taustavari = UIManager.getColor("Panel.background");

                JTextArea kontrollit1 = new JTextArea();
                kontrollit1.setText("\nA - liiku vasemmalle"
                        + "\nS - liiku alas"
                        + "\nD - liiku oikealle");
                kontrollit1.setBackground(taustavari);
                kontrollit1.setFocusable(false);
                kontrollit1.setEditable(false);
                sivuPaneeli.add(kontrollit1);

                JTextArea kontrollit2 = new JTextArea();
                kontrollit2.setText("Q - käännä vastapäivään"
                        + "\nE - käännä myötäpäivään"
                        + "\n\nW - pause");
                kontrollit2.setBackground(taustavari);
                kontrollit2.setFocusable(false);
                kontrollit2.setEditable(false);
                sivuPaneeli.add(kontrollit2);

                String[] vaikeusasteet = {"helppo", "normaali", "vaikea"};
                JComboBox vaikeusasteenValinta = new JComboBox(vaikeusasteet);
                vaikeusasteenValinta.setSelectedIndex(1);
                vaikeusasteenValinta.setEditable(false);
                vaikeusasteenValinta.setFocusable(false);
                vaikeusasteenValinta.setEnabled(true);
                sivuPaneeli.add(vaikeusasteenValinta);

                JButton aloitaLuovutaNappi = new JButton("Aloita");
                aloitaLuovutaNappi.setFocusable(false);
                NapinKuuntelija napinKuuntelija = new NapinKuuntelija(aloitaLuovutaNappi, vaikeusasteenValinta, pelitilanHallinnoija, laskenta);
                aloitaLuovutaNappi.addActionListener(napinKuuntelija);
                sivuPaneeli.add(aloitaLuovutaNappi);
                
                //asetetaan peliloopille päivitettävät
                looppi.lisaaPaivitettava(new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, napinKuuntelija, new Random()));
                looppi.lisaaPaivitettava(new TaysienRivienKasittelija(alue, tarkastaja, laskenta));
                looppi.lisaaPaivitettava(piirtoalusta);
                looppi.lisaaPaivitettava(seuraavanPiirtaja);
                looppi.lisaaPaivitettava(kokonaispistekentta);
                looppi.lisaaPaivitettava(lisattyjenPisteidenKentta);
                
                //viimeistellään GUI
                frame.pack();
                frame.setVisible(true);
            }

        });
    }
}
