package blatt12.aufgabe70p;

/**
 * Diese Klasse repräsentiert die Knoten für unseren Graphen
 * @author Stephan
 *
 */
public class Vertex implements Comparable<Vertex> {
    /**
     * Bezeichnung des Knoten, muss eineindeutig sein.
     */
    private final String name;
    
    /**
     * Erstellt einen neuen Knoten
     * @param name
     */
    public Vertex(final String name) {
        if (name == null)
            throw new IllegalArgumentException("name is null");
        this.name = name;
    }

    /**
     * Gibt den Namen des Knoten zurück
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Vertex obj) {
        return this.name.compareTo(obj.name);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }
}
