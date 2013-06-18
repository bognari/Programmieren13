package blatt12.aufgabe70p;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse ist ein Graph ohne Kreise
 * 
 * @author Stephan
 *
 */
public class GraphCircFree extends Graph {

    /**
     * Zusammenhangskomponenten
     */
    private final Map<Vertex, Connectivity> connectivities;
    
    /**
     * Erstellt einen leeren kreisfreien Graphen
     */
    public GraphCircFree() {
        super();
        this.connectivities = new HashMap<>();
    }
    
    /**
     * Klont den "alten" kreisfreien Graphen
     * @param old
     */
    public GraphCircFree(GraphCircFree old) {
        super(old);
        this.connectivities = new HashMap<>(old.connectivities);
    }
    
    /* (non-Javadoc)
     * @see blatt12.aufgabe70p.Graph#addEdge(blatt12.aufgabe70p.Edge)
     */
    @Override
    public boolean addEdge(Edge edge) {
        Connectivity zusammenhang1 = this.connectivities.get(edge.getVertexLower());
        Connectivity zusammenhang2 = this.connectivities.get(edge.getVertexHigher());
        /*
         * Beide Knoten sind noch nicht im Graphen enthalten
         */
        if (zusammenhang1 == null && zusammenhang2 == null) {
            Connectivity zusammenhang = new Connectivity();
            zusammenhang.add(edge.getVertexLower());
            zusammenhang.add(edge.getVertexHigher());
            this.connectivities.put(edge.getVertexLower(), zusammenhang);
            this.connectivities.put(edge.getVertexHigher(), zusammenhang);
            return super.addEdge(edge);
        }
        /*
         * Der erste Knoten ist nicht im Graphen enthalten
         */
        if (zusammenhang1 == null) {
            zusammenhang2.add(edge.getVertexLower());
            this.connectivities.put(edge.getVertexLower(), zusammenhang2);
            return super.addEdge(edge);
        }
        /*
         * Der zweite Knoten ist nicht im Graphen enthalten 
         */
        if (zusammenhang2 == null) {
            zusammenhang1.add(edge.getVertexHigher());
            this.connectivities.put(edge.getVertexHigher(), zusammenhang1);
            return super.addEdge(edge);
        }
        /*
         * Beide Knoten sind im Graphen enthalten und teste ob die Zusammenhangskomponenten verschieden sind
         * der Test auf die Referenz reicht
         */
        if (zusammenhang1 != zusammenhang2) { 
            /*
             * Merge beide Komponenten
             */
            for (Vertex vertex : zusammenhang2) {
                this.connectivities.put(vertex, zusammenhang1);
                zusammenhang1.add(vertex);
            }
          return super.addEdge(edge);
        }
        return false;
    }
}
