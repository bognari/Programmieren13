package blatt12.aufgabe70p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Diese Klasse dient als Ausführer des Programms.
 * 
 * @author Stephan
 */
public class App {
    /**
     * Main wie immer.
     * 
     * @param agrs
     */
    public static void main(String[] agrs) {
        Graph graph = readCSV("kanten.csv");

        System.out.println("########Tree##########");
        Graph mst = new Tree();
        graph.mstKruskal(mst);
        System.out.println("Kruskal:");
        System.out.println(mst.getStats());
        System.out.println(mst);
        System.out.println("##################");

        mst = new Tree();
        graph.mstPrim(mst);
        System.out.println("Prim:");
        System.out.println(mst.getStats());
        System.out.println(mst);

        System.out.println("########Circ##########");
        mst = new GraphCircFree();
        graph.mstKruskal(mst);
        System.out.println("Kruskal:");
        System.out.println(mst.getStats());
        System.out.println(mst);
        System.out.println("##################");

        mst = new GraphCircFree();
        graph.mstPrim(mst);
        System.out.println("Prim:");
        System.out.println(mst.getStats());
        System.out.println(mst);
    }

    /**
     * Liest einen Graphen aus einer angegebenen CSV Datei ein.
     * 
     * @param path
     *            zur Datei
     * @return der Graph
     */
    private static Graph readCSV(String path) {
        Graph graph = new Graph();
        try {
            Scanner fileSc = new Scanner(new File(path));
            Pattern p = Pattern.compile("^([^;]*);([^;]*);(-?\\d+\\.?\\d*)$");
            String line;
            while (fileSc.hasNextLine()) {
                line = fileSc.nextLine();
                try {
                    graph.addEdge(line, p);
                } catch (IllegalArgumentException iae) {
                    System.out.printf("\"%s\" Exception %s\n", line, iae.getMessage());
                }
            }
            fileSc.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
            fnfe.printStackTrace();
        }
        return graph;
    }
}
