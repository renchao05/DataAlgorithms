package com.renchao.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private final ArrayList<String> vertexList;
    private final int[][] edges;
    private int numOfEdges;
    private final boolean[] state;

    //广度优先遍历
    public void bfs(int v) {
        LinkedList<Integer> queue = new LinkedList<>();
        int w, u;
        System.out.print(vertexList.get(v) + "->");
        state[v] = true;
        queue.addLast(v);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = firstAdjacent(u);
            while (w != -1) {
                if (!state[w]) {
                    System.out.print(vertexList.get(w) + "->");
                    state[w] = true;
                    queue.addLast(w);
                }
                w = nextAdjacent(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!state[i])
                bfs(i);
        }
    }

    //深度优先遍历
    private void dfs(int v) {
        System.out.print(vertexList.get(v) + "->");
        state[v] = true;
        int w = firstAdjacent(v);
        while (w != -1) {
            if (!state[w])
                dfs(w);
            w = nextAdjacent(v, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!state[i])
                dfs(i);
        }
    }


    //获取第一个邻接节点的下标
    private int firstAdjacent(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    //获取下一个邻接节点的下标
    private int nextAdjacent(int index, int v) {
        for (int i = v + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] ints : edges) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.state = new boolean[n];
    }

    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
