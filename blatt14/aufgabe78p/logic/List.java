package blatt14.aufgabe78p.logic;

/**
 * Stellt das generische Interface für Listen bereit
 * 
 * @param <T>
 */
public interface List<T extends Comparable<T>> {
    /**
     * Gibt an ob die Liste leer ist.
     * 
     * @return isEmpty
     */
    boolean isEmpty();

    /**
     * Gibt die Länge der Liste zurück 0 = Empty
     * 
     * @return
     */
    int length();

    /**
     * Gibt an ob das Element x in der Liste ist
     * 
     * @param x
     * @return isInList
     */
    boolean isInList(T x);

    /**
     * Gibt das erste Element der Liste zurück
     * 
     * @return
     */
    T firstElement();

    /**
     * Fügt das Element x in die Liste ein
     * 
     * @param x
     * @return
     */
    List<T> insert(T x);

    /**
     * Löscht das Element X aus der Liste, wenn X nicht in der Liste ist, wird
     * nichts gemacht
     * 
     * @param x
     * @return
     */
    List<T> delete(T x);

    /**
     * Löscht das erste Element aus der Liste
     * 
     * @return
     */
    List<T> delete();

    /**
     * das nächste Element
     * 
     * @return
     */
    List<T> next();

}