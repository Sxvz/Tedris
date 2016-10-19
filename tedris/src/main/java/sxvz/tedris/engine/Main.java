package sxvz.tedris.engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
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
import sxvz.tedris.logic.Tiedostotyokalut;
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
                Pelialue alue = new Pelialue(12, 25);
                Vapaustarkastaja tarkastaja = new Vapaustarkastaja(alue);
                Pisteenlaskenta laskenta = new Pisteenlaskenta(alue.getLeveys());
                PelitilanHallinnoija pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta);

                //GUI
                int palikanKoko = 20;
                JFrame frame = new JFrame("Tedris");
                Insets insets = frame.getInsets();
                int paaAlustanLeveys = alue.getLeveys() * palikanKoko;
                int paaAlustanKorkeus = alue.getKorkeus() * palikanKoko;
                int sivuPaneelinLeveys = 200;
                int menunErottajanLeveys = 3;

                frame.setPreferredSize(new Dimension(paaAlustanLeveys + menunErottajanLeveys + sivuPaneelinLeveys + insets.left + insets.right + 2, paaAlustanKorkeus + insets.top + insets.bottom));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setResizable(false);

                Nappaimistonkuuntelija nappaimistonKuuntelija = new Nappaimistonkuuntelija(alue, pelitilanHallinnoija, tarkastaja);
                frame.addKeyListener(nappaimistonKuuntelija);

                Container container = frame.getContentPane();
                container.setLayout(null);

                Piirtoalusta piirtoalusta = new Piirtoalusta(alue, palikanKoko);
                piirtoalusta.setBounds(insets.left, insets.top, paaAlustanLeveys, paaAlustanKorkeus);
                piirtoalusta.setBackground(Color.WHITE);
                container.add(piirtoalusta);

                nappaimistonKuuntelija.setPaivitettava(piirtoalusta);

                JSeparator menunErottaja = new JSeparator(SwingConstants.VERTICAL);
                menunErottaja.setBounds(paaAlustanLeveys + insets.left, insets.top, menunErottajanLeveys, paaAlustanKorkeus);
                container.add(menunErottaja);

                SeuraavanPiirtaja seuraavanPiirtaja = new SeuraavanPiirtaja(palikanKoko, alue);
                seuraavanPiirtaja.setBounds(paaAlustanLeveys + menunErottajanLeveys + insets.left, insets.top, sivuPaneelinLeveys, sivuPaneelinLeveys);
                seuraavanPiirtaja.setBackground(Color.WHITE);
                container.add(seuraavanPiirtaja);

                //Nostetaan sivupaneelia 2 pikseliä piirtäjän päälle sen sijaan,
                //että lyhennettäisiin sivupaneelin korkeutta samalla määrällä,
                //sillä alareuna ei jostakin syystä suostu tekemään näin pientä
                //muutosta vaan näyttää n. 5 pikselin muutoksen.
                JPanel sivuPaneeli = new JPanel();
                sivuPaneeli.setBounds(paaAlustanLeveys + menunErottajanLeveys + insets.left, insets.top + sivuPaneelinLeveys - 2, sivuPaneelinLeveys, paaAlustanKorkeus - sivuPaneelinLeveys);
                sivuPaneeli.setLayout(new GridLayout(6, 1));
                container.add(sivuPaneeli);

                Pistekentta kokonaispistekentta = new Pistekentta(laskenta, 0);
                sivuPaneeli.add(kokonaispistekentta);

                Pistekentta lisattyjenPisteidenKentta = new Pistekentta(laskenta, 1);
                sivuPaneeli.add(lisattyjenPisteidenKentta);

                Color taustavari = UIManager.getColor("Panel.background");

                JTextArea kontrollit1 = new JTextArea();
//                kontrollit1.setText("\nA - liiku vasemmalle"
//                        + "\nS - liiku alas"
//                        + "\nD - liiku oikealle");
                kontrollit1.setBackground(taustavari);
                kontrollit1.setFocusable(false);
                kontrollit1.setEditable(false);
                sivuPaneeli.add(kontrollit1);

                JTextArea kontrollit2 = new JTextArea();
//                kontrollit2.setText("Q - käännä vastapäivään"
//                        + "\nE - käännä myötäpäivään"
//                        + "\n\nW - pause");
                kontrollit2.setBackground(taustavari);
                kontrollit2.setFocusable(false);
                kontrollit2.setEditable(false);
                sivuPaneeli.add(kontrollit2);
                
                //separaattori
//                JSeparator menunErottaja = new JSeparator(SwingConstants.VERTICAL);
//                menunErottaja.setBounds(paaAlustanLeveys, insets.top, 5, paaAlustanKorkeus);
//                container.add(menunErottaja);
                
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
