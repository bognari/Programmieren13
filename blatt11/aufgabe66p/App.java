package blatt11.aufgabe66p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import blatt11.aufgabe66p.Team.League;

/**
 * Testklasse für die generische LinkedList 
 */
public class App {
    
    /**
     * Fügt alle Adressen aus der gegebenen CSV in eine Liste ein
     * 
     * @param path zur CSV Datei der Adressen
     * @return sorterite Liste von Adressen
     */
    private static List<Adresse> adressenCSV(String path) {
        if (path == null)
            throw new IllegalArgumentException("keine Path Angabe zur CSV");

        File file = new File(path);
        List<Adresse> list = new LinkedList<Adresse>();

        try {
            Scanner scanner = new Scanner(file);
            String line;
            Pattern pattern = Pattern.compile("^([^;]*);([^;]*);([^;]*);([^;]*);([^;]*)$");
            // vn; nn; sh; po; hn
            // 1   2   3   4   5
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
     * Erstellt eine Bundesliga Tabelle als Liste der 1., 2. und 3. Liga
     * 
     * @param mannschaftenPath
     * @param spielePath
     * @return Bundesligaliste
     */
    private static List<Team> blCSV(String mannschaftenPath, String spielePath) {
        if (mannschaftenPath == null || spielePath == null)
            throw new IllegalArgumentException("keine Path Angabe zur CSV");

        List<Team> bl = new LinkedList<>();

        try {
            File mannschaftenCSV = new File(mannschaftenPath);
            
            Scanner mannschaftenSc = new Scanner(mannschaftenCSV);
            String line;
            Pattern p = Pattern.compile("^(\\d+);([^;]*);(\\d+)$");
            // V_ID;Name;Liga
            // 1    2    3
            Matcher m;
            int mannschaftenAnzahl = 0;
            while (mannschaftenSc.hasNextLine()) { // erst anzahl aller
                                                  // mannschaften bestimmen
                line = mannschaftenSc.nextLine();
                m = p.matcher(line);
                if (m.matches())
                    mannschaftenAnzahl = mannschaftenAnzahl < Integer.parseInt(m.group(1)) ? Integer.parseInt(m.group(1)) : mannschaftenAnzahl;
            }
            mannschaftenSc.close();

            Team[] mannschaften = new Team[mannschaftenAnzahl];

            mannschaftenSc = new Scanner(mannschaftenCSV); // Scanner "zurücksetzen"

            line = mannschaftenSc.nextLine(); // kopf überspringen

            while (mannschaftenSc.hasNextLine()) { // mannschaften eintragen
                line = mannschaftenSc.nextLine();
                m = p.matcher(line);
                if (m.matches()) {
                    mannschaften[Integer.parseInt(m.group(1)) - 1] = new Team(
                            League.values()[Integer.valueOf(m.group(3)) - 1], m.group(2));
                } else {
                    System.err.printf("matching fail mannschaften.csv: %s\n", line);
                }
            }
            mannschaftenSc.close();

            File spieleCSV = new File(spielePath);
            Scanner spieleSc = new Scanner(spieleCSV);
            p = Pattern.compile("^(\\d+);(\\d+);([^;]*);([^;]*);(\\d+);([^;]*);(\\d+);([^;]*);(\\d+);(\\d+)$");
            // Liga;Spieltag;Datum;Uhrzeit;Heim_ID;Heim;Gast_ID;Gast;Tore_Heim;Tore_Gast
            // 1    2        3     4       5       6    7       8    9         10
            line = spieleSc.nextLine();

            while (spieleSc.hasNextLine()) { // spiele eintragen
                line = spieleSc.nextLine();
                m = p.matcher(line);
                if (m.matches()) {
                    mannschaften[Integer.parseInt(m.group(5)) - 1].game(Integer.parseInt(m.group(9)),
                            Integer.parseInt(m.group(10)));
                    mannschaften[Integer.parseInt(m.group(7)) - 1].game(Integer.parseInt(m.group(10)),
                            Integer.parseInt(m.group(9)));
                } else {
                    System.err.printf("matching fail spiel.csv: %s\n", line);
                }
            }
            spieleSc.close();

            for (Team mw : mannschaften) {
                bl.insert(mw);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bl;
    }

    public static void main(String[] args) {
        List<Adresse> adressen = adressenCSV("adressen.csv");
        System.out.println("Adressen:\n");
        System.out.println(adressen);
        List<Team> wertungen = blCSV("mannschaften.csv", "spiele.csv");
        System.out.println("Bundesligen:\n");
        System.out.printf("%6s | %25s | %5s | %3s | %3s | %3s | %5s | %3s | %3s\n", "Liga", "Mannschaft", "Spiel", "S",
                "U", "N", "T", "TD", "P");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(wertungen);
    }
}
