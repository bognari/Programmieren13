package blatt14.aufgabe78p.logic;

/**
 * das Adressen Objekt. alle Werte werden als Strings gespeichert. es existieren
 * keine Getter und Setter
 */
public class Adresse implements Comparable<Adresse> {
    private final String vorname;
    private final String nachname;
    private final String strasseUndHausnummer;
    private final String plzUndOrt;
    private final String handy;

    /**
     * Erstellt ein Adressen Obj
     * 
     * @param vorname
     * @param nachname
     * @param strasseUndHausnummer
     * @param plzUndOrt
     * @param handy
     */
    public Adresse(final String vorname, final String nachname, final String strasseUndHausnummer,
            final String plzUndOrt, final String handy) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasseUndHausnummer = strasseUndHausnummer;
        this.plzUndOrt = plzUndOrt;
        this.handy = handy;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getStrasseUndHausnummer() {
        return strasseUndHausnummer;
    }

    public String getPlzUndOrt() {
        return plzUndOrt;
    }

    public String getHandy() {
        return handy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s %s\n%s\n%s\nHandy: %s\n", vorname, nachname, strasseUndHausnummer, plzUndOrt, handy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        Adresse obj;
        try {
            obj = (Adresse) o;
        } catch (final ClassCastException cce) {
            return false;
        }
        return vorname.equals(obj.vorname) && nachname.equals(obj.nachname) && plzUndOrt.equals(obj.plzUndOrt)
                && strasseUndHausnummer.equals(obj.strasseUndHausnummer) && handy.equals(obj.handy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Adresse adr) {
        final int comp = nachname.compareTo(adr.nachname);
        return comp != 0 ? comp : vorname.compareTo(adr.vorname);
    }
}
