import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test class to examine algorithm.
 */

public class ShortestPathTests {

    private Graph<Character> undirectedGraph;
    public static final int TIMEOUT = 200;

    @Before
    public void init() {
        undirectedGraph = createUndirectedGraph();
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the attached file.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 90; i++) {
            if (vertices.add(new Vertex<Character>((char) i)) != false) { // not workin for some reason
                vertices.add(new Vertex<Character>((char) i));
            }
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('A'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('H'), 2));

        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('B'), 15));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('H'), 15));

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('R'), 11));
        edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('A'), 11));

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('R'), 11));
        edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('A'), 11));

        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 1));

        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));

        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 3));

        edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('Q'), 7));
        edges.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('R'), 7));

        edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('P'), 9));
        edges.add(new Edge<>(new Vertex<>('P'), new Vertex<>('R'), 9));

        edges.add(new Edge<>(new Vertex<>('P'), new Vertex<>('Q'), 1));
        edges.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('P'), 1));

        edges.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('X'), 5));
        edges.add(new Edge<>(new Vertex<>('X'), new Vertex<>('Q'), 5));

        edges.add(new Edge<>(new Vertex<>('P'), new Vertex<>('X'), 2));
        edges.add(new Edge<>(new Vertex<>('X'), new Vertex<>('P'), 2));

        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('X'), 6));
        edges.add(new Edge<>(new Vertex<>('X'), new Vertex<>('D'), 6));

        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('X'), 10));
        edges.add(new Edge<>(new Vertex<>('X'), new Vertex<>('C'), 10));

        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('X'), 4));
        edges.add(new Edge<>(new Vertex<>('X'), new Vertex<>('E'), 4));


        return new Graph<Character>(vertices, edges);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras() {
        Map<Vertex<Character>, Integer> dijkActual = DijkstraAlgorithm.dijkstras(
                new Vertex<Character>('D'), undirectedGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('X'), 23);
        dijkExpected.put(new Vertex<>('A'), 2);
        dijkExpected.put(new Vertex<>('B'), 15);
        dijkExpected.put(new Vertex<>('C'), 16);
        dijkExpected.put(new Vertex<>('D'), 18);
        dijkExpected.put(new Vertex<>('E'), 21);
        dijkExpected.put(new Vertex<>('R'), 13);
        dijkExpected.put(new Vertex<>('Q'), 20);
        dijkExpected.put(new Vertex<>('B'), 15);
        dijkExpected.put(new Vertex<>('P'), 21);

        assertEquals(dijkExpected, dijkActual);
    }

}