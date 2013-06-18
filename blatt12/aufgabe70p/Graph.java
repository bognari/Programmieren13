package blatt12.aufgabe70p;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse repräsentiert einen ungerichteten Graphen mit V (Knoten Menge) und E (Kanten Menge)
 * @author Stephan
 *
 */
public class Graph {
    /**
     * Knoten Menge
     */
    protected final Set<Vertex> vertexes;
    /**
     * Kanten Menge
     */
    protected final Set<Edge> edges;
    
    /**
     * Erzeugt einen leeren Graph
     */
    public Graph() {
        this.vertexes = new HashSet<>();
        this.edges = new TreeSet<>();
    }
    
    /**
     * Klont den "alten" Graphen
     * @param old
     */
    public Graph(Graph old) {
        this.vertexes = new HashSet<>(old.vertexes);
        this.edges = new TreeSet<>(old.edges);
    }
    
    /**
     * Fügt die Kante in den Graphen ein
     * @param edge
     * @return TRUE, wenn die Kante noch nicht im Graphen existierte sonst FALSE
     */
    public boolean addEdge(Edge edge) {
        this.vertexes.add(edge.getVertexLower());
        this.vertexes.add(edge.getVertexHigher());
        return this.edges.add(edge);
    }
    
    /**
     * Erzeugte eine Kante aus dem gegebenen String, der mit p geparst wird und fügt diese in den Graphen ein
     * @param args für die Kante
     * @param p der Parser
     * @return TRUE, wenn die Kante noch nicht im Graphen existierte sonst FALSE
     * @throws IllegalArgumentException wenn args nicht mit p geparst werden konnte
     */
    public boolean addEdge(String args, Pattern p) throws IllegalArgumentException{
        Matcher m = p.matcher(args);
        if (!m.matches())
            throw new IllegalArgumentException("input is not matchable");
        
        return this.addEdge(new Edge(new Vertex(m.group(1)), new Vertex(m.group(2)), Double.parseDouble(m.group(3))));
    }
    
    /**
     * Erzeugt einen MST mit Krustkal
     * @param mst Platzhalter für den MST
     * @throws IllegalStateException wenn der Graph leer ist
     * @throws IllegalArgumentException wenn der übergebene Platzhalter für den MST nicht leer oder null ist
     */
    public void mstKruskal(final Graph mst) throws IllegalStateException, IllegalArgumentException{
        /*
         * Dient nur zur übersicht und ist nicht genau, da es die gesamt System Uhr ist.
         */
        long time = System.nanoTime();
        if (this.isEmpty())
            throw new IllegalStateException("Graph is empty");
        if (mst == null)
            throw new IllegalArgumentException("MST is null");
        if (!mst.isEmpty())
            throw new IllegalArgumentException("MST is not empty");
        
        for (Edge edge : this.edges) {
            mst.addEdge(edge);
            /*
             * Teste ob wir bei n - 1 Kanten sind
             */
            if (mst.edges.size() == this.vertexes.size() -1) 
                break;
        }
        /*
         * Dient nur zur übersicht und ist nicht genau, da es die gesamt System Uhr ist.
         */
        System.out.println((System.nanoTime() - time) / 1000);
    }
    
    /**
     * Erzeugt einen MST mit Prim (nur wegen dem Spaß)
     * @param mst Platzhalter für den MST
     * @throws IllegalStateException wenn der Graph leer ist
     * @throws IllegalArgumentException wenn der übergebene Platzhalter für den MST nicht leer oder null ist
     */
    public void mstPrim(final Graph mst) throws IllegalStateException, IllegalArgumentException{
        /*
         * Dient nur zur übersicht und ist nicht genau, da es die gesamt System Uhr ist.
         */
        long time = System.nanoTime();
        if (this.isEmpty())
            throw new IllegalStateException("Graph is empty");
        if (mst == null)
            throw new IllegalArgumentException("MST is null");
        if (!mst.isEmpty())
            throw new IllegalArgumentException("MST is not empty");
        
        Vertex root = this.vertexes.iterator().next();
        
        Set<Edge> edges = new TreeSet<>();
        edges.addAll(this.getEdgesFrom(root));
        
        if (edges.isEmpty()) 
            throw new IllegalStateException("Graph is not connected");

        Set<Vertex> notConnected = new HashSet<>(this.vertexes);
        notConnected.remove(root);
        
        while (!notConnected.isEmpty()) {
            if (mst.edges.size() == this.vertexes.size() - 1)
                break;
            Iterator<Edge> iterator = edges.iterator();
            Edge edge;
            insertEdge : while (iterator.hasNext()) {
                edge = iterator.next();
                if (mst.addEdge(edge)) {
                    if (notConnected.remove(edge.getVertexLower())) {
                        edges.addAll(this.getEdgesFrom(edge.getVertexLower()));
                    }
                    if (notConnected.remove(edge.getVertexHigher())) {
                        edges.addAll(this.getEdgesFrom(edge.getVertexHigher()));
                    }
                    edges.remove(edge);
                    break insertEdge;
                }
                iterator.remove();
            }
        }
        
        /*
         * Dient nur zur übersicht und ist nicht genau, da es die gesamt System Uhr ist.
         */
        System.out.println((System.nanoTime() - time) / 1000);
    }


    /**
     * Gibt an ob der Graph leer ist
     * @return TRUE wenn keine Knoten existieren sonst FALSE
     */
    public boolean isEmpty() {
        return this.vertexes.isEmpty();
    }
    
    /**
     * Gibt alle inzidenten Kanten zu vertex an
     * @param vertex
     * @return Kanten Menge
     */
    public TreeSet<Edge> getEdgesFrom(Vertex vertex) {
        TreeSet<Edge> edges = new TreeSet<>();
        
        Iterator<Edge> iterator = this.edges.iterator();
        
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.isPart(vertex)) {
                edges.add(edge);
            }
        }
        
        return edges;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Iterator<Edge> iterator = this.edges.iterator();
        while(iterator.hasNext()) {
            ret.append(iterator.next());
            ret.append("\n");
        }
        return ret.toString();
    }
    
    public String getStats() {
        return String.format("Vertex: %d\nEdges: %d", this.vertexes.size(), this.edges.size());
    }   
}
