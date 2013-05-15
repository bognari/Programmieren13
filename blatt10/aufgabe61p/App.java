package blatt10.aufgabe61p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Testklasse für die LinkedList 
 */
public class App {
    
    /**
     * Fügt alle Adressen aus der gegebenen CSV in eine Liste ein
     * 
     * @param path
     *            zur CSV Datei der Adressen
     * @return sorterite Liste von Adressen
     */
    private static List adressenCSV(String path) {
        if (path == null)
            throw new IllegalArgumentException("keine Path Angabe zur CSV");

        File file = new File(path);
        List list = new LinkedList();

        try {
            Scanner scanner = new Scanner(file);
            String line;
            Pattern pattern = Pattern.compile("^([^;]*);([^;]*);([^;]*);([^;]*);([^;]*)$");
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
        } catch (FileNotFoundException e) {
            System.err.printf("Konnte die CSV unter %s nicht öffnen\n", path);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Main für die Testklasse
     * 
     * @param args
     */
    public static void main(String[] args) {
        List adressen = adressenCSV("adressen.csv");
        System.out.println("Adressen:\n");
        System.out.println(adressen);
    }
}
