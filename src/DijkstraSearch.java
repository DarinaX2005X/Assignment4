import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<Vertex<V>> unsettledNodes;
    private final Map<Vertex<V>, Double> distances;
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra(graph.getVertex(source));
    }

    public void dijkstra(Vertex<V> source) {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex<V> currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode.getData());
            unsettledNodes.remove(currentNode);

            for (Vertex<V> neighbor : currentNode.getAdjacentVertices().keySet()) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor);

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor.getData(), currentNode.getData());
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private double getDistance(Vertex<V> node, Vertex<V> target) {
        return node.getAdjacentVertices().getOrDefault(target, Double.MAX_VALUE);
    }

    private Vertex<V> getVertexWithMinimumWeight(Set<Vertex<V>> vertices) {
        Vertex<V> minimum = null;
        for (Vertex<V> vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
                continue;
            }

            if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex<V> destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }
}
