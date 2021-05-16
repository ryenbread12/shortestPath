import java.util.*;

/**
 * My implementations of dijkstra's shortest path algorithm. Test cases are my neighborhood.
 *
 * @author Ryan McNeil
 * @version 1.0
 */

public class DijkstraAlgorithm {

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (assuming non-negative edge
     * weights).
     *
     * Two termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("You cannot pass in an illegal start or graph. Can't be traversed!");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("You cannot traverse a graph without a start!");
        }

        Map<Vertex<T>, Integer> shortestPath = new HashMap<>();
        HashSet<Vertex<T>> visitedSet = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        for (Vertex<T> vertex : graph.getVertices()) {
            shortestPath.put(vertex, Integer.MAX_VALUE);
        }
        pq.add(new VertexDistance<T>(start, 0));

        while (!(pq.isEmpty()) && visitedSet.size() != graph.getVertices().size()) {
            VertexDistance<T> dist = pq.remove();
            if (!visitedSet.contains(dist.getVertex())) {
                visitedSet.add(dist.getVertex());
                shortestPath.put(dist.getVertex(), dist.getDistance());
                List<VertexDistance<T>> adj = adjList.get(dist.getVertex());
                for (VertexDistance<T> vertexDistances : adj) {
                    pq.add(new VertexDistance<T>(vertexDistances.getVertex(),
                            vertexDistances.getDistance() + dist.getDistance()));
                }
            }
        }
        return shortestPath;
    }
}
