public class DepthFirstSearch<V> extends Search<V> {
    public DepthFirstSearch(MyGraph<V> graph, V source) {
        super(source);
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V current) {
        marked.add(current);

        for (Vertex<V> vertex : graph.adjacencyList(current)) {
            V v = vertex.getData();
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                dfs(graph, v);
            }
        }
    }
}
