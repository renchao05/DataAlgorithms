package com.renchao.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 */
public class Graph {
    private final ArrayList<String> vertexList; // 存储顶点集合
    private final int[][] edges;    // 存储图对应的邻结矩阵
    private int numOfEdges; // 边的数目
    private final boolean[] state;

    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.state = new boolean[n];
    }


    //广度优先遍历
    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!state[i])
                bfs(i);
        }
    }

    public void bfs(int v) {
        // 遍历过节点以后，把该节点记入该队列，供后面接着遍历其相邻的节点
        LinkedList<Integer> queue = new LinkedList<>();
        // w 相邻节点，u当前节点
        int w, u;
        System.out.print(vertexList.get(v) + "->");
        state[v] = true;
        queue.addLast(v);
        // 全部遍历完成结束
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = firstAdjacent(u);
            // 当前节点的相邻节点遍历完结束
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

    //深度优先遍历
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!state[i])
                dfs(i);
        }
    }

    private void dfs(int v) {
        System.out.print(vertexList.get(v) + "->");
        state[v] = true;
        int w = firstAdjacent(v); //获取第一个邻接节点的下标
        while (w != -1) {
            if (!state[w])
                dfs(w);
            w = nextAdjacent(v, w); //获取下一个邻接节点的下标
        }
    }


    // 插入节点
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
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

    // 返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回节点i(下标)对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1,v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] ints : edges) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
