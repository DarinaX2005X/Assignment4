import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> verticesMap;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.verticesMap = new HashMap<>();
    }

    public void addVertex(V value) {
        if (!verticesMap.containsKey(value)) {
            verticesMap.put(value, new Vertex<>(value));
        }
    }

    public void addEdge(V source, V dest, double weight) {
        addVertex(source);
        addVertex(dest);

        Vertex<V> vSource = verticesMap.get(source);
        Vertex<V> vDest = verticesMap.get(dest);

        vSource.addAdjacentVertex(vDest, weight);

        if (undirected) {
            vDest.addAdjacentVertex(vSource, weight);
        }
    }

    public int getVerticesCount() {
        return verticesMap.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : verticesMap.values()) {
            count += v.getAdjacentVertices().size();
        }

        if (undirected) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(V value) {
        return verticesMap.containsKey(value);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source) || !hasVertex(dest)) return false;

        Vertex<V> vSource = verticesMap.get(source);
        Vertex<V> vDest = verticesMap.get(dest);

        return vSource.getAdjacentVertices().containsKey(vDest);
    }

    public List<Vertex<V>> adjacencyList(V value) {
        if (!hasVertex(value)) return null;

        List<Vertex<V>> vertices = new ArrayList<>();
        Vertex<V> vertex = verticesMap.get(value);
        vertices.addAll(vertex.getAdjacentVertices().keySet());

        return vertices;
    }

    public Iterable<Vertex<V>> getEdges(V value) {
        if (!hasVertex(value)) return null;

        return verticesMap.get(value).getAdjacentVertices().keySet();
    }

    public Vertex<V> getVertex(V value) {
        return verticesMap.get(value);
    }
}
