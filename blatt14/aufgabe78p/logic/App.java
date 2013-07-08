package blatt14.aufgabe78p.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    /**
     * Zeigt auf den Anfang der ganzen Adressliste
     */
    private List<Adresse> firstAdresse;
    /**
     * Anzahl der Elemente
     */
    private int           maxIndex;

    /**
     * momentane Position in der Liste
     */
    private List<Adresse> curAdresse;
    /**
     * Index der momentanen Adresse
     */
    private int           curIndex;

    /**
     * zurück zum Anfang der Liste
     */
    public void firstAdress() {
        curAdresse = firstAdresse;
        curIndex = 1;

        System.out.printf("first Adresse: %s", curAdresse.firstElement());
    }

    /**
     * nächste Adresse
     */
    public void nextAdress() {
        curAdresse = curAdresse.next();
        curIndex++;

        System.out.printf("next Adresse: %s", curAdresse.firstElement());
    }

    /**
     * Fügt alle Adressen aus der gegebenen CSV in eine Liste ein
     * 
     * @param path
     *            zur CSV Datei der Adressen
     * @return sorterite Liste von Adressen
     */
    public void adressenCSV(final String path) {
        if (path == null)
            throw new IllegalArgumentException("keine Path Angabe zur CSV");

        final File file = new File(path);
        final List<Adresse> list = new LinkedList<Adresse>();

        try {
            final Scanner scanner = new Scanner(file);
            String line;
            final Pattern pattern = Pattern.compile("^([^;]*);([^;]*);([^;]*);([^;]*);([^;]*)$");
            // vn; nn; sh; po; hn
            // 1 2 3 4 5
            Matcher m;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                m = pattern.matcher(line);
                if (m.matches()) {
                    list.insert(new Adresse(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5)));
                } else {
                    System.err.printf("Eintrag in CSV fehlerhaft: %s", line);
                }
            }
            scanner.close();
        } catch (final FileNotFoundException e) {
            System.err.printf("Konnte die CSV unter %s nicht öffnen\n", path);
            e.printStackTrace();
        }

        setContent(list);
    }

    /**
     * Sucht den Nachnamen in der Adresseliste und erstellt eine neue App mit
     * dem Resultat als Inhalt
     * 
     * @param nn
     * @param exakt
     * @return
     * @throws IllegalStateException
     */
    public App searchAdress(final String nn, final boolean exakt) throws IllegalStateException {
        if (firstAdresse.isEmpty())
            throw new IllegalStateException("Adress Liste ist leer");

        final List<Adresse> searchList = new LinkedList<>();

        List<Adresse> cur = firstAdresse;

        while (cur.next() != null) {
            if (exakt) {
                if (cur.firstElement().getNachname().equals(nn)) {
                    searchList.insert(cur.firstElement());
                    System.out.printf("find %s with %s", cur.firstElement().getNachname(), nn);
                }
            } else {
                if (cur.firstElement().getNachname().toLowerCase().startsWith(nn.toLowerCase())) {
                    searchList.insert(cur.firstElement());
                    System.out.printf("find %s with %s", cur.firstElement().getNachname(), nn);
                }
            }
            cur = cur.next();
        }

        if (searchList.isEmpty())
            throw new IllegalStateException("keine Adresse gefunden");

        final App searchApp = new App();
        searchApp.setContent(searchList);
        searchApp.firstAdress();

        return searchApp;
    }

    /**
     * Setzt list als neuen Inhalt
     * 
     * @param list
     */
    private void setContent(final List<Adresse> list) {
        firstAdresse = list;
        curAdresse = list;
        curIndex = 1;
        maxIndex = list.length();
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public Adresse getCurAdresse() {
        return curAdresse.firstElement();
    }

    public int getCurIndex() {
        return curIndex;
    }
}
