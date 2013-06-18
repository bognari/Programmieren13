package blatt12.aufgabe70p;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Diese Klasse repräsentiert einen Tree,
 * die Kreisfreiheit wird durch Breitensuche realisiert
 * @author Stephan
 */
public class Tree extends Graph {

    /**
     * Erzeugt einen leeren Baum
     */
    public Tree() {
    }

    /**
     * Klont den "alten" Baum
     * @param old
     */
    public Tree(Tree old) {
        super(old);
    }
    
    /* (non-Javadoc)
     * @see blatt12.aufgabe70p.Graph#addEdge(blatt12.aufgabe70p.Edge)
     */
    @Override
    public boolean addEdge(Edge insert) {
        Graph testTree = new Graph(this);
        testTree.addEdge(insert);
        
        Set<Vertex> visited = new HashSet<>(testTree.vertexes.size());
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addFirst(insert.getVertexLower());
        Set<Edge> usedEdges = new HashSet<>(testTree.edges.size());
        
        while(!queue.isEmpty()) {
            Vertex test = queue.pollFirst();
            if (!visited.add(test))
                return false;            
            for (Edge edge : testTree.getEdgesFrom(test)) {
                if (usedEdges.add(edge))
                    queue.addLast(edge.getToVertex(test));
            }
        }
        return super.addEdge(insert);
    }
}
