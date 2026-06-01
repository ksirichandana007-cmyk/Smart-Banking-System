import java.util.*;

public class SmartBankingCO3 {

    static class Edge {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    static int V = 6;

    public static void BFS(List<List<Integer>> graph, int start) {
        boolean visited[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        System.out.print("BFS Traversal: ");

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print((char)(node + 'A') + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void DFS(List<List<Integer>> graph, int node,
                           boolean visited[]) {

        visited[node] = true;
        System.out.print((char)(node + 'A') + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor])
                DFS(graph, neighbor, visited);
        }
    }

    public static void primMST(int graph[][]) {

        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int min = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 &&
                    !mstSet[v] &&
                    graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        int totalCost = 0;

        System.out.println("\nPrim's MST:");

        for (int i = 1; i < V; i++) {
            System.out.println(
                (char)(parent[i] + 'A') + " - " +
                (char)(i + 'A') + " : " +
                graph[i][parent[i]]
            );
            totalCost += graph[i][parent[i]];
        }

        System.out.println("Total Cost = " + totalCost);
    }

    public static void main(String[] args) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        graph.get(0).add(1);
        graph.get(0).add(2);

        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(1).add(3);

        graph.get(2).add(0);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(4);

        graph.get(3).add(1);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(3).add(5);

        graph.get(4).add(2);
        graph.get(4).add(3);
        graph.get(4).add(5);

        graph.get(5).add(3);
        graph.get(5).add(4);

        BFS(graph, 0);

        System.out.print("DFS Traversal: ");
        DFS(graph, 0, new boolean[V]);
        System.out.println();

        int weightedGraph[][] = {
            {0,4,2,0,0,0},
            {4,0,1,5,0,0},
            {2,1,0,8,10,0},
            {0,5,8,0,2,6},
            {0,0,10,2,0,3},
            {0,0,0,6,3,0}
        };

        primMST(weightedGraph);
    }
}
