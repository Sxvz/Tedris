package sxvz.tedris.gui;

import sxvz.tedris.engine.Paivitettava;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import sxvz.tedris.domain.Pelialue;
/**
 * Graafisen käyttöliittymän pohjaluokka, joka toimii kaiken perustana ja
 * luo tarvittavat osat.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelialue alue;
    private Nappaimistonkuuntelija kuuntelija;
    private int palikanKoko;
    private Piirtoalusta piirtoalusta;
    private SeuraavanPiirtaja seuraavanPiirtaja;
    
    /**
     * Konstruktori, jolla määritetään pelissä olevien palikoiden koko pikseleinä.
     * 
     * @param alue Pelialue, joka piirretään
     * @param kuuntelija Näppämistönkuuntelija, joka liitetään frameen;
     * @param palikanKoko Piirrettävän palikan koko pikseleinä
     */
    public Kayttoliittyma(Pelialue alue, Nappaimistonkuuntelija kuuntelija, int palikanKoko) {
        this.alue = alue;
        this.kuuntelija = kuuntelija;
        this.palikanKoko = palikanKoko;
    }

    /**
     * Luo ikkunan ja sen komponentit pelille.
     */
    @Override
    public void run() {
        frame = new JFrame("Tedris");
        Insets insets = frame.getInsets();
        int paaAlustanLeveys = alue.getLeveys() * palikanKoko + insets.left + insets.right;
        int paaAlustanKorkeus = alue.getKorkeus() * palikanKoko + insets.top + insets.bottom;

        frame.setPreferredSize(new Dimension(paaAlustanLeveys + 200, paaAlustanKorkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        luoKomponentit(frame.getContentPane(), paaAlustanLeveys, paaAlustanKorkeus, insets);

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container, int paaAlustanLeveys, int paaAlustanKorkeus, Insets insets) {
        container.setLayout(null);
        piirtoalusta = new Piirtoalusta(alue, palikanKoko);
        piirtoalusta.setBounds(insets.left, insets.top, alue.getLeveys() * palikanKoko, alue.getKorkeus() * palikanKoko);
        container.add(piirtoalusta);
        
        JSeparator menunErottaja = new JSeparator(SwingConstants.VERTICAL);
        menunErottaja.setBounds(paaAlustanLeveys, insets.top, 5, paaAlustanKorkeus);
        container.add(menunErottaja);
        
        seuraavanPiirtaja = new SeuraavanPiirtaja(palikanKoko);
        seuraavanPiirtaja.setBounds(paaAlustanLeveys + 5, insets.top, 190, 200);
        container.add(seuraavanPiirtaja);
        
        JPanel sivuPaneeli = new JPanel();
        sivuPaneeli.setBounds(paaAlustanLeveys + 5, insets.top + 200, 192 - insets.right, paaAlustanKorkeus - 200);
        sivuPaneeli.setLayout(new GridLayout(6,1));
        container.add(sivuPaneeli);
        
        JTextField kokonaispisteKentta = new JTextField("00000000000000000000000");
        JTextField lisatytPisteetKentta = new JTextField("+0000000000000000000000");
        kokonaispisteKentta.setEditable(false);
        kokonaispisteKentta.setFocusable(false);
        lisatytPisteetKentta.setEditable(false);
        lisatytPisteetKentta.setFocusable(false);
        sivuPaneeli.add(kokonaispisteKentta);
        sivuPaneeli.add(lisatytPisteetKentta);
        
        sivuPaneeli.add(new JSeparator(SwingConstants.HORIZONTAL));
        
        String[] vaikeusasteet = {"helppo", "normaali", "vaikea"};
        JComboBox vaikeusasteenValinta = new JComboBox(vaikeusasteet);
        vaikeusasteenValinta.setSelectedIndex(1);
        vaikeusasteenValinta.setEditable(false);
        vaikeusasteenValinta.setFocusable(false);
        vaikeusasteenValinta.setEnabled(false);
        sivuPaneeli.add(vaikeusasteenValinta);
        
        sivuPaneeli.add(new JSeparator(SwingConstants.HORIZONTAL));
        
        JButton aloitaLuovutaNappi = new JButton("Luovuta");
        sivuPaneeli.add(aloitaLuovutaNappi);
        aloitaLuovutaNappi.setFocusable(false);
        frame.addKeyListener(kuuntelija);
    }


    public JFrame getFrame() {
        return frame;
    }
    
    public Paivitettava getPaivitettava() {
        return (Paivitettava) piirtoalusta;
    }

    public SeuraavanPiirtaja getSeuraavanPiirtaja() {
        return seuraavanPiirtaja;
    }
    
    
}
