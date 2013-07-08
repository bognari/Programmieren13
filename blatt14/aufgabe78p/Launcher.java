package blatt14.aufgabe78p;

import blatt14.aufgabe78p.gui.GUI;
import blatt14.aufgabe78p.logic.App;

public class Launcher {

    /**
     * Main zum starten des Programms
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final App app = new App();

        app.adressenCSV("adressen.csv");

        final GUI gui = new GUI(app, true);
        gui.setVisible(true);
    }
}
