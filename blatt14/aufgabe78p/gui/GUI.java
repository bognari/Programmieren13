package blatt14.aufgabe78p.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import blatt14.aufgabe78p.logic.Adresse;
import blatt14.aufgabe78p.logic.App;

@SuppressWarnings("serial")
public class GUI extends JFrame {

    private JLabel     lblVN;
    private JLabel     lblNN;
    private JLabel     lblStHN;
    private JLabel     lblPLZOrt;
    private JLabel     lblHN;

    private JLabel     lblListStatus;

    private JButton    btnStart;
    private JButton    btnNext;

    private JTextField tfSuche;
    private JCheckBox  cbSuche;

    private final App  app;

    /**
     * Erstellt eine GUI ohne Suchfeld und Menubar
     * 
     * @param app
     */
    public GUI(final App app) {
        this(app, false);
    }

    /**
     * Erstellt eine GUI
     * 
     * @param app
     * @param extras
     */
    public GUI(final App app, final boolean extras) {
        this.app = app;

        initGUI();

        adressPanel();
        controllPanel();

        if (extras) {
            menuBar();
            searchPanel();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        showAdress();
    }

    /**
     * Erstellt die GUI
     */
    private void initGUI() {
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (final Exception e) {
        }

        setBounds(100, 100, 450, 300);

        final JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);
    }

    /**
     * Erstellt das Panel zum Anzeigen der Adresse
     */
    private void adressPanel() {
        lblVN = new JLabel();
        lblNN = new JLabel();
        lblStHN = new JLabel();
        lblPLZOrt = new JLabel();
        lblHN = new JLabel();

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        panel.add(new JLabel("Vorname:  ", SwingConstants.RIGHT));
        panel.add(lblVN);
        panel.add(new JLabel("Nachname:  ", SwingConstants.RIGHT));
        panel.add(lblNN);
        panel.add(new JLabel("Straße und Hausnummer:  ", SwingConstants.RIGHT));
        panel.add(lblStHN);
        panel.add(new JLabel("PLZ und Ort:  ", SwingConstants.RIGHT));
        panel.add(lblPLZOrt);
        panel.add(new JLabel("Handynummer:  ", SwingConstants.RIGHT));
        panel.add(lblHN);
        getContentPane().add(panel);
    }

    /**
     * Erstellt das Panel zum Navigieren in den Adressen
     */
    private void controllPanel() {
        final BTNActionListener btnal = new BTNActionListener();

        lblListStatus = new JLabel("");

        btnStart = new JButton("Anfang");
        btnStart.setActionCommand("start");
        btnStart.addActionListener(btnal);

        btnNext = new JButton("Weiter");
        btnNext.setActionCommand("next");
        btnNext.addActionListener(btnal);

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        panel.add(lblListStatus);
        panel.add(btnStart);
        panel.add(btnNext);

        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    /**
     * Erstellt das Suchpanel
     */
    private void searchPanel() {
        final TFListener tfal = new TFListener();

        tfSuche = new JTextField();
        tfSuche.setHorizontalAlignment(SwingConstants.CENTER);
        tfSuche.setText(tfal.getDefaultText());
        tfSuche.setColumns(10);
        tfSuche.setActionCommand("searchFild");
        tfSuche.addFocusListener(tfal);
        tfSuche.addKeyListener(tfal);

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        cbSuche = new JCheckBox("exakt");
        panel.add(cbSuche);
        panel.add(tfSuche);

        getContentPane().add(panel, BorderLayout.NORTH);
    }

    /**
     * Erstellt die Menubar
     */
    private void menuBar() {
        final BTNActionListener btnal = new BTNActionListener();

        final JMenuItem mntmFileOpen = new JMenuItem("Datei öffnen");
        mntmFileOpen.setActionCommand("openFile");
        mntmFileOpen.addActionListener(btnal);

        final JMenuItem mntmAppExit = new JMenuItem("Beenden");
        mntmAppExit.setActionCommand("exitApp");
        mntmAppExit.addActionListener(btnal);

        final JMenu mnFileMenu = new JMenu("Datei");
        mnFileMenu.add(mntmFileOpen);
        mnFileMenu.add(mntmAppExit);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(mnFileMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Setzt die adress als momentan angezeigte Adresse
     * 
     * @param adress
     */
    private void showAdress() {
        final Adresse adress = app.getCurAdresse();

        if (adress != null) {
            lblVN.setText(adress.getVorname());
            lblNN.setText(adress.getNachname());
            lblStHN.setText(adress.getStrasseUndHausnummer());
            lblPLZOrt.setText(adress.getPlzUndOrt());
            lblHN.setText(adress.getHandy());

            updateListStatus();
        }
    }

    /**
     * Setzt den Status im Controllpanel
     * 
     * @param cur
     * @param max
     */
    private void updateListStatus() {
        final int cur = app.getCurIndex();
        final int max = app.getMaxIndex();

        lblListStatus.setText(String.format("%d / %d", cur, max));

        if (cur == max) {
            btnNext.setEnabled(false);
        } else {
            if (!btnNext.isEnabled()) {
                btnNext.setEnabled(true);
            }
        }
    }

    /**
     * ActionListener für alle Buttons
     * 
     */
    class BTNActionListener implements ActionListener {

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
         * )
         */
        @Override
        public void actionPerformed(final ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "next":
                    app.nextAdress();
                    showAdress();
                    break;
                case "start":
                    app.firstAdress();
                    showAdress();
                    break;
                case "openFile": {
                    final JFileChooser openFile = new JFileChooser();
                    if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(GUI.this)) {
                        app.adressenCSV(openFile.getSelectedFile().getAbsolutePath());
                        app.firstAdress();
                        showAdress();
                    }
                }
                    break;
                case "exitApp":
                    System.exit(Frame.NORMAL);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * ActionListener für das Suchfeld
     */
    class TFListener extends KeyAdapter implements FocusListener {

        /**
         * Standard Anzeige für die Suche
         */
        private final String defaultText = "Suche";

        /**
         * @return
         */
        public String getDefaultText() {
            return defaultText;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
         */
        @Override
        public void keyTyped(final KeyEvent e) {
            if (e.getKeyChar() == '\n') {
                try {
                    final App searchApp = app.searchAdress(tfSuche.getText(), cbSuche.isSelected());
                    final GUI searchGUI = new GUI(searchApp, false);
                    final Point point = searchGUI.getLocation();
                    point.x = point.x + 50;
                    point.y = point.y + 50;
                    searchGUI.setLocation(point);
                    searchGUI.setVisible(true);
                } catch (final IllegalStateException ise) {
                    JOptionPane.showMessageDialog(GUI.this, ise.getMessage(), "Suche", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
         */
        @Override
        public void focusGained(final FocusEvent e) {
            if (e.getComponent() == tfSuche) {
                if (tfSuche.getText().equals(defaultText)) {
                    tfSuche.setText("");
                }
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
         */
        @Override
        public void focusLost(final FocusEvent e) {
            if (e.getComponent() == tfSuche) {
                if (tfSuche.getText().equals("")) {
                    tfSuche.setText(defaultText);
                }
            }
        }
    }
}
