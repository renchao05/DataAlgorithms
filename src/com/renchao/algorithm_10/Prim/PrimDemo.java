package com.renchao.algorithm_10.Prim;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 普利姆算法,求最小生成树
 */
public class PrimDemo {
    public static final int M = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // 顶点
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 各顶点之间的连接和权值
        int[][] weight = {
                {M, 5, 7, M, M, M, 2},
                {5, M, M, 9, M, M, 3},
                {7, M, M, M, 8, M, M},
                {M, 9, M, M, M, 4, M},
                {M, M, 8, M, M, 5, 4},
                {M, M, M, 4, 5, M, 6},
                {2, 3, M, M, 4, 6, M}};
        // 创建图
        MGraph mGraph = new MGraph(data, weight);
        showGraph(mGraph);
        prim(mGraph, 'A');
        prim(mGraph);
    }

    public static void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 普利姆算法
     *
     * @param graph 图
     * @param v     开始的顶点
     */
    public static void prim(MGraph graph, char v) {
        // 标记节点是否已经修通在连通网上
        int[] gs = new int[graph.vertex];
        // 查找开始节点
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.data[i] == v) {
                gs[i] = 1;
                break;
            }
        }
        // h1,h2记录准备连通的两个顶点的下标。minWeight记录两个顶点的权值（距离）
        int h1, h2, minWeight;
        // 循环连接每一条边，边数 = 顶点数-1
        for (int k = 1; k < graph.vertex; k++) {
            minWeight = 1000;
            h1 = -1;
            h2 = -1;
            // 遍历每个已经在连通网上的顶点
            for (int i = 0; i < graph.vertex; i++) {
                // 遍历每个与连通网还没有连接的顶点
                for (int j = 0; j < graph.vertex; j++) {
                    // 找出最短路径,gs[i] == 1已经在连通网上的顶点。gs[j] == 0还没有加入连通网的
                    if (gs[i] == 1 && gs[j] == 0 && graph.weight[i][j] < minWeight) {
                        h1 = i;
                        h2 = j;
                        minWeight = graph.weight[i][j];
                    }
                }
            }
            // 打印连通的顶点，以及两个顶点的权值
            System.out.println(graph.data[h1] + ">" + graph.data[h2] + " :" + minWeight);
            gs[h2] = 1; // 加入最小生成树
        }
    }

    /**
     * 普利姆算法,另外一直思路
     * @param graph
     */
    public static void prim(MGraph graph) {
        int totalWeight = 0; // 记录最小生成数路径的总权值
        // h1,h2记录准备连通的两个顶点的下标。minWeight记录两个顶点的权值（距离）
        int h1, h2, minWeight;
        // 标记已经连通路径
        int[][] gs = new int[graph.vertex][graph.vertex];
        // 已经连通顶点集合
        ArrayList<Integer> vi = new ArrayList<>();
        vi.add(0);// 开始顶点
        // 未连通顶点集合
        ArrayList<Integer> vj = new ArrayList<>();
        for (int i = 1; i < graph.vertex; i++) {
            vj.add(i);
        }

        while (vi.size() != graph.vertex) {
            minWeight = 1000;
            h1 = -1;
            h2 = -1;
            // 遍历每个已经在最小生成树上的顶点
            for (int i : vi) {
                // 遍历每个与最小生成数还没有连接的顶点
                for (int j : vj) {
                    // 找出最短路径
                    if (graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            vi.add(h2);
            vj.remove((Integer) h2);
            totalWeight += minWeight;
            gs[h1][h2] = 1;
        }
        System.out.println(totalWeight);
        for (int[] ints : gs) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
