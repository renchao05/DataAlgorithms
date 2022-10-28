package com.renchao.Kruskal;

import java.util.Arrays;

public class KruskalCase {
    public int edgeNum; //边数量
    private final char[] vertex; //顶点'A','B'....
    private final int[][] matrix;   //地图数据
    //待修的路,从小到大排序，二维数组，每行代表一条路，每行的第1，2列代表两个顶点，第三列代表权值。
    private final int[][] edge;
    private final int[][] graph;    //临时地图，记录已经连通的路径

    public static final int INF = 999;

    /**
     * 构造器
     *
     * @param vertex //顶点'A','B'....
     * @param matrix //地图数据
     */
    public KruskalCase(char[] vertex, int[][] matrix) {
        int vLen = vertex.length;
        this.vertex = new char[vLen];
        this.matrix = new int[vLen][vLen];
        System.arraycopy(vertex, 0, this.vertex, 0, vLen);
        for (int i = 0; i < vLen; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vLen);
        }
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
        this.edge = new int[edgeNum][3];
        this.graph = new int[vLen][vLen];
    }

    /**
     * 根据课程重新写的，代码简洁
     */
    public void Kruskal2() {
        getEdge();
        // 并查集表，当前值是下一个值的下标。严格来说，这里初始值应该是都指向自己的，如：【0，1，2，3，4，5，6】
        int[] edges = new int[vertex.length];
        // v1,v2是准备连接的两个顶点，m,n分别是
        int v1, v2, m, n;
        for (int[] e : edge) {
            v1 = e[0];
            v2 = e[1];
            m = getEnd(edges, v1);  //终点
            n = getEnd(edges, v2);  //终点
            if (m != n) {
                edges[m] = n;   //并查集
                System.out.println(vertex[v1] + ">" + vertex[v2] + " :" + e[2]);
            }
        }
    }

    /**
     * 获取顶点的终点，根据课程重新写的，代码简洁
     * 通过并查集【当前值是下一个值的下标】
     *
     * @param edges 记录边的终点情况，并查集，有点像链表，
     * @param v     顶点
     * @return 返回终点
     */
    public int getEnd(int[] edges, int v) {
        int i = v;
        while (edges[i] != 0) { // 如果初始化的表是指向自己的，这里应该是 edges[i] != i
            i = edges[i];
        }
        return i;
    }


    /**
     * 按照自己的思路写的
     */
    public void Kruskal() {
        getEdge();
        for (int[] e : edge) {
            if (!isLoop(e[0], e[1])) {
                // 没有构成回路，则把该边连通
                graph[e[0]][e[1]] = 1;
                graph[e[1]][e[0]] = 1;
                System.out.println(vertex[e[0]] + ">" + vertex[e[1]] + " :" + e[2]);
            }
        }
    }

    /**
     * //按照自己的思路写的，递归直接判断是否形成回路。
     *
     * @param h1   顶点1
     * @param h2   顶点2
     * @param data 记录访问过的点
     * @return 形成回路true, 否则返回false
     */
    public boolean isLoop(int h1, int h2, int[] data) {
        data[h1] = 1;
        // 通过深度优先方式，把已经连接的顶点都走一遍，从h1如果可以走到h2,说明会构成回路
        for (int i = 0; i < graph.length; i++) {
            // graph[h1][i]表示h1到i的连通状态。
            if (graph[h1][i] == 1 && data[i] == 0) {
                if (i == h2)    // i与h2相等表示走到h2,构成回路
                    return true;
                return isLoop(i, h2, data); // 继续向前走
            }
        }
        return false;
    }

    /**
     * //按照自己的思路写的，递归直接判断是否形成回路。
     *
     * @param h1 顶点1
     * @param h2 顶点2
     * @return 形成回路true, 否则返回false
     */
    public boolean isLoop(int h1, int h2) {
        int[] data = new int[graph.length];
        return isLoop(h1, h2, data);
    }

    /**
     * 获取待修路的边
     * 是排序后的
     */
    public void getEdge() {
        int k = 0;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] < 999) {
                    edge[k][0] = i; // 第一列顶点
                    edge[k][1] = j; // 第二列顶点
                    edge[k++][2] = matrix[i][j];    // 第三列权值
                }
            }
        }
        sort();
        for (int[] ints : edge) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 对边按照从小到大进行排序
     */
    public void sort() {
        int[] temp = new int[3];
        for (int i = 0; i < edgeNum - 1; i++) {
            for (int j = 0; j < edgeNum - i - 1; j++) {
                if (edge[j][2] > edge[j + 1][2]) {
                    System.arraycopy(edge[j], 0, temp, 0, edge[j].length);
                    System.arraycopy(edge[j + 1], 0, edge[j], 0, edge[j].length);
                    System.arraycopy(temp, 0, edge[j + 1], 0, edge[j].length);
                }
            }
        }
    }

    /**
     * 打印显示地图
     */
    public void print() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%3d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
