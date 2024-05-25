import java.util.*;

public class MyGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> verticesMap;

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
        this.verticesMap = new HashMap<>();
    }

    public void addVertex(V value) {
        if (!verticesMap.containsKey(value)) {
            verticesMap.put(value, new Vertex<>(value));
        }
    }

    public void addEdge(V source, V dest) {
        addEdge(source, dest, 1.0);
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

    public List<Vertex<V>> adjacencyList(V value) {
        if (!verticesMap.containsKey(value)) return Collections.emptyList();

        Vertex<V> vertex = verticesMap.get(value);
        return new ArrayList<>(vertex.getAdjacentVertices().keySet());
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

    public Vertex<V> getVertex(V value) {
        return verticesMap.get(value);
    }
}
