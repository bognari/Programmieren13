package blatt10.aufgabe61p;

/**
 * Ist das Interface für unsere Listen Implementierung
 */
public interface List {
    /**
     * prüft on die Liste leer ist
     * 
     * @return true wenn die Liste leer ist
     */
    public boolean isEmpty();

    /**
     * berechnet die Länge der Liste
     * 
     * @return Länge der Liste
     */
    public int length();

    /**
     * prüft ob die Adresse in der Liste ist
     * 
     * @param x
     * @return true wenn die Adresse in der Liste ist
     */
    public boolean isInList(Adresse x);

    /**
     * gibt die erste Adresse zurück
     * 
     * @return Adresse
     */
    public Adresse firstAdresse();

    /**
     * fügt die Adresse sortiert in die Liste ein
     * 
     * @param x
     *            Adresse
     * @return this
     */
    public List insert(Adresse x);

    /**
     * löscht das erste Auffinden der Adresse in der Liste, wenn die Adresse
     * nicht enhalten ist, wird nichts gemacht
     * 
     * @param x
     *            Adresse
     * @return this
     */
    public List delete(Adresse x);

    /**
     * löscht die Adresse im Aktuellen Listenelement
     * 
     * @return this
     */
    public List delete();
}
