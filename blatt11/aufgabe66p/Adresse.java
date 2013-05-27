package blatt11.aufgabe66p;

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
    public Adresse(String vorname, String nachname, String strasseUndHausnummer, String plzUndOrt, String handy) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasseUndHausnummer = strasseUndHausnummer;
        this.plzUndOrt = plzUndOrt;
        this.handy = handy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s %s\n%s\n%s\nHandy: %s\n", this.vorname, this.nachname, this.strasseUndHausnummer,
                this.plzUndOrt, this.handy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        Adresse obj;
        try {
            obj = (Adresse) o;
        } catch (ClassCastException cce) {
            return false;
        }
        return this.vorname.equals(obj.vorname) && this.nachname.equals(obj.nachname)
                && this.plzUndOrt.equals(obj.plzUndOrt) && this.strasseUndHausnummer.equals(obj.strasseUndHausnummer)
                && this.handy.equals(obj.handy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Adresse adr) {
        int comp = this.nachname.compareTo(adr.nachname);
        return comp != 0 ? comp : this.vorname.compareTo(adr.vorname);
    }
}
