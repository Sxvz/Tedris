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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import sxvz.tedris.domain.Pelialue;
import sxvz.tedris.gui.NimikirjaintenKysyja;
import sxvz.tedris.gui.LuovutaNapinKuuntelija;
import sxvz.tedris.gui.Nappaimistonkuuntelija;
import sxvz.tedris.gui.Piirtoalusta;
import sxvz.tedris.gui.Pistekentta;
import sxvz.tedris.gui.ResetnapinKuuntelija;
import sxvz.tedris.gui.SeuraavanPiirtaja;
import sxvz.tedris.gui.TulosnapinKuuntelija;
import sxvz.tedris.gui.Tulostaulu;
import sxvz.tedris.logic.AktiivisenKokoelmanHallinnoija;
import sxvz.tedris.logic.Huipputulokset;
import sxvz.tedris.logic.PelitilanHallinnoija;
import sxvz.tedris.logic.Pisteenlaskenta;
import sxvz.tedris.logic.TaysienRivienKasittelija;
import sxvz.tedris.logic.Vaikeuttaja;
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
        
        //vanha initialisaatio
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
                Huipputulokset tulokset = new Huipputulokset("huipputulokset.dat");
                double vaikeutuskerroin = 0.95;

                //GUI
                int palikanKoko = 20;
                JFrame frame = new JFrame("Tedris");
                int paaAlustanLeveys = alue.getLeveys() * palikanKoko;
                int paaAlustanKorkeus = alue.getKorkeus() * palikanKoko;
                int sivupaneelinLeveys = 200;
                int menunErottajanLeveys = 3;
                int reunatila = 2;

                //vielä vähän logiikkaa
                PelitilanHallinnoija pelitilanHallinnoija = new PelitilanHallinnoija(looppi, alue, laskenta, tulokset, new NimikirjaintenKysyja(frame));

                //jatketaan GUIta
                frame.getContentPane().setPreferredSize(new Dimension(paaAlustanLeveys + menunErottajanLeveys + sivupaneelinLeveys + reunatila, paaAlustanKorkeus));
                frame.getContentPane().setMinimumSize(frame.getContentPane().getPreferredSize());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setResizable(false);

                Container container = frame.getContentPane();
                container.setLayout(null);

                Piirtoalusta piirtoalusta = new Piirtoalusta(alue, palikanKoko);
                piirtoalusta.setBounds(0, 0, paaAlustanLeveys, paaAlustanKorkeus);
                piirtoalusta.setBackground(Color.WHITE);
                container.add(piirtoalusta);

                Tulostaulu tulostaulu = new Tulostaulu(tulokset);
                tulostaulu.setBounds(piirtoalusta.getBounds());
                tulostaulu.setVisible(false);
                container.add(tulostaulu);

                JSeparator menunErottaja = new JSeparator(SwingConstants.VERTICAL);
                menunErottaja.setBounds(paaAlustanLeveys, 0, menunErottajanLeveys, paaAlustanKorkeus);
                container.add(menunErottaja);

                SeuraavanPiirtaja seuraavanPiirtaja = new SeuraavanPiirtaja(palikanKoko, alue);
                seuraavanPiirtaja.setBounds(paaAlustanLeveys + menunErottajanLeveys, 0, sivupaneelinLeveys, sivupaneelinLeveys - reunatila);
                seuraavanPiirtaja.setBackground(Color.WHITE);
                container.add(seuraavanPiirtaja);

                //Nostetaan sivupaneelia 2 pikseliä piirtäjän päälle sen sijaan,
                //että lyhennettäisiin sivupaneelin korkeutta samalla määrällä,
                //sillä alareuna ei jostakin syystä suostu tekemään näin pientä
                //muutosta vaan näyttää n. 5 pikselin muutoksen.
                JPanel sivupaneeli = new JPanel();
                sivupaneeli.setBounds(paaAlustanLeveys + menunErottajanLeveys, sivupaneelinLeveys - reunatila, sivupaneelinLeveys, paaAlustanKorkeus - sivupaneelinLeveys);
                sivupaneeli.setLayout(new GridLayout(6, 1));
                container.add(sivupaneeli);

                Pistekentta kokonaispistekentta = new Pistekentta(laskenta, 0);
                sivupaneeli.add(kokonaispistekentta);

                Pistekentta lisattyjenPisteidenKentta = new Pistekentta(laskenta, 1);
                sivupaneeli.add(lisattyjenPisteidenKentta);

                JButton tulosnappi = new JButton("Huipputulokset");
                tulosnappi.setFocusable(false);
                sivupaneeli.add(tulosnappi);

                JButton resetnappi = new JButton("Resetoi huipputulokset");
                resetnappi.setFocusable(false);
                resetnappi.setEnabled(false);
                ResetnapinKuuntelija resetnapinKuuntelija = new ResetnapinKuuntelija(frame, tulokset, tulostaulu);
                resetnappi.addActionListener(resetnapinKuuntelija);
                sivupaneeli.add(resetnappi);

                String[] vaikeusasteet = {"helppo", "normaali", "vaikea"};
                JComboBox vaikeusasteenValinta = new JComboBox(vaikeusasteet);
                vaikeusasteenValinta.setSelectedIndex(1);
                vaikeusasteenValinta.setEditable(false);
                vaikeusasteenValinta.setFocusable(false);
                sivupaneeli.add(vaikeusasteenValinta);

                JButton aloitaLuovutaNappi = new JButton("Aloita");
                aloitaLuovutaNappi.setFocusable(false);
                LuovutaNapinKuuntelija aloitaLuovutaNapinKuuntelija = new LuovutaNapinKuuntelija(aloitaLuovutaNappi, tulosnappi, vaikeusasteenValinta, pelitilanHallinnoija, laskenta);
                aloitaLuovutaNappi.addActionListener(aloitaLuovutaNapinKuuntelija);
                sivupaneeli.add(aloitaLuovutaNappi);

                //viimeistellään kuuntelijat
                TulosnapinKuuntelija tulosnapinKuuntelija = new TulosnapinKuuntelija(tulosnappi, resetnappi , aloitaLuovutaNappi, piirtoalusta, tulostaulu);
                tulosnappi.addActionListener(tulosnapinKuuntelija);

                Nappaimistonkuuntelija nappaimistonKuuntelija = new Nappaimistonkuuntelija(alue, pelitilanHallinnoija, tarkastaja, piirtoalusta);
                frame.addKeyListener(nappaimistonKuuntelija);

                //asetetaan peliloopille päivitettävät
                looppi.lisaaPaivitettava(new AktiivisenKokoelmanHallinnoija(alue, tarkastaja, aloitaLuovutaNapinKuuntelija, new Random()));
                looppi.lisaaPaivitettava(new TaysienRivienKasittelija(alue, tarkastaja, laskenta, new Vaikeuttaja(looppi, vaikeutuskerroin)));
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
