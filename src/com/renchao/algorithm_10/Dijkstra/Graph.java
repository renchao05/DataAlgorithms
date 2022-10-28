package com.renchao.algorithm_10.Dijkstra;

import java.util.Arrays;

public class Graph {
    private static final int N = 999;
    private final char[] vertex;  //顶点数组
    private final int[][] matrix; //邻接矩阵
    private VisitedVertex vv;

    public Graph() {
        this.vertex = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        this.matrix = new int[][] {
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}};
    }

    public char[] getVertex() {
        return vertex;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    //显示图
    public void show() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法主体
     *
     * @param index 出发顶点
     */
    public void dsj(int index) {
        this.vv = new VisitedVertex(vertex.length, index);
        // 更新与出发节点相邻的节点
        update(index);
        // 遍历每个节点，每次选取到出发节点最近的一个
        for (int i = 1; i < vertex.length; i++) {
            update(this.vv.getNext());
        }
    }

    /**
     * 显示路线情况
     */
    public void print() {
        System.out.println("各点距离分别为：");
        for (int i = 0; i < vertex.length; i++) {
            route(i);
            System.out.println(vertex[i] + " :" + vv.getDis(i));
        }
    }

    /**
     * 打印路径
     *
     * @param v     当前顶点
     */
    public void route(int v) {
        int i = vv.getPre(v);
        if (i == -1)
            return;
        route(i);
        System.out.print(vertex[i] + ">");
    }

    /**
     * 更新与当前顶点相邻的顶点到出发点的距离和前驱顶点
     *
     * @param index 当前的顶点
     */
    public void update(int index) {
        int len;
        // 遍历与当前顶点相邻的顶点
        for (int i = 0; i < vertex.length; i++) {
            // 当前节点到起始点的距离 + 当前节点到i的距离
            len = vv.getDis(index) + matrix[index][i];
            // 如果顶点i没有被访问过，并且i节点到起始点的距离小于之前记录的距离，则更新
            if (!vv.in(i) &&  len < vv.getDis(i)) {
                vv.updateDis(i, len);
                vv.updatePre(i, index);
            }
        }
    }
}

















