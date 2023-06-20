package Union;

import java.util.HashMap;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
    	size++;
        parent = new int[size];
        rank = new int[size];
        for (int i = 1; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public HashMap<Integer, Integer> getConnectedComponents() {
        HashMap<Integer, Integer> components = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            components.put(i, root);
        }
        return components;
    }
}
