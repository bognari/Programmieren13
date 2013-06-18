package blatt12.aufgabe70p;

/**
 * Diese Klasse repräsentiert Kanten in einem ungerichteten Graphen
 * @author Stephan
 */
public class Edge implements Comparable<Edge> {
    /**
     * lower Vertex
     */
    private final Vertex vertex1;
    
    /**
     * higher Vertex
     */
    private final Vertex vertex2;
    
    /**
     * Wert der Kante
     */
    private final double weight;
    
    /**
     * Erstellt eine Kannte mit gegebenen Werten und fügt die Vertexe sortiert ein.
     * @param vertex1
     * @param vertex2
     * @param weight
     */
    public Edge(Vertex vertex1, Vertex vertex2, double weight) {
        if (vertex1 == null || vertex2 == null || vertex1.equals(vertex2)) 
            throw new IllegalArgumentException("illegal nodes");
        if (vertex1.compareTo(vertex2) <= 0) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        } else {
            this.vertex1 = vertex2;
            this.vertex2 = vertex1;
        }
        this.weight = weight;
    }
    
    /**
     * Gibt den "kleinen" Knoten zurück
     * @return
     */
    public Vertex getVertexLower() {
        return vertex1;
    }

    /**
     * Gibt den "großen" Knoten zurück
     * @return
     */
    public Vertex getVertexHigher() {
        return vertex2;
    }

    /**
     * Gibt das Gesicht der Kante zurück
     * @return
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Prüft ob der Knoten zur Kante gehört
     * @param vertex
     * @return
     */
    public boolean isPart(Vertex vertex) {
        return this.vertex1.equals(vertex) || this.vertex2.equals(vertex);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (vertex1.hashCode());
        result = prime * result + (vertex2.hashCode());
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Edge other = (Edge) obj;
        
        if (!vertex1.equals(other.vertex1))
            return false;
        if (!vertex2.equals(other.vertex2))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Edge o) {
        int compWeigth = Double.compare(this.weight, o.weight);
        if (compWeigth != 0)
            return compWeigth;
        
        int compNode1 = this.vertex1.compareTo(o.vertex1);
        if (compNode1 != 0)
            return compNode1;
        
        return this.vertex2.compareTo(o.vertex2);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s;%s;%.2f",this.vertex1, this.vertex2, this.weight);
    }
    
    /**
     * Gibt den "anderen" Knoten zurück
     * @param from
     * @throws IllegalArgumentException wenn der angegebene Knoten nicht zur Kante gehört
     * @return
     */
    public Vertex getToVertex(Vertex from) {
        if (this.vertex1.equals(from)) {
            return this.vertex2;
        }
        if (this.vertex2.equals(from)) {
            return this.vertex1;
        }
        throw new IllegalArgumentException("not a part from edge");
    }
}
