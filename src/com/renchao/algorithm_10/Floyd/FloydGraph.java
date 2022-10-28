package com.renchao.algorithm_10.Floyd;

import java.util.Arrays;

public class FloydGraph {
    private static final int N = 999;
    private final char[] vertex;  //顶点数组
    private final int[][] m1;   //记录顶点间距离数据
    private final int[][] m2;   //路由矩阵，根据中间中转顶点，记录路径
    private final int len;

    //构造器
    public FloydGraph() {
        this.vertex = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        len = this.vertex.length;
        m1 = new int[][] {
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}};
        // 自己到自己置为0
        for (int i = 0; i < len; i++) {
            m1[i][i] = 0;
        }
        m2 = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(m2[i], i);
        }

    }

    /**
     * 打印输出两点的路径和距离
     *
     * @param v1 出发点
     * @param v2 终点
     */
    public void route(char v1, char v2) {
        int h1 = getV(v1);
        int h2 = getV(v2);
        route(h1, h2);
        System.out.println(v2 + " :" + m1[h1][h2]);
    }

    /**
     * 通过递归打印路径
     *
     * @param h1 起始顶点
     * @param h2 结束顶点
     */
    private void route(int h1, int h2) {
        int h = m2[h1][h2];
        if (h != h1)
            route(h1, h);
        System.out.print(vertex[h] + ">");
    }

    /**
     * 显示图
     */
    public void show() {
        for (int[] ints : m1) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("================");
        for (int[] ints : m2) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 获取顶点下标
     *
     * @param v 顶点
     * @return 返回下标
     */
    public int getV(char v) {
        for (int i = 0; i < len; i++) {
            if (vertex[i] == v)
                return i;
        }
        return -1;
    }

    public void floyd() {
        int dis = 0;
        // 遍历中间顶点,i是中转顶点
        for (int i = 0; i < len; i++) {
            // 遍历所有以i为中间节点的情况，j出发顶点，k是目的顶点
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len && j != i; k++) {
                    dis = m1[i][j] + m1[i][k];
                    if (dis < m1[j][k]) {
                        m1[j][k] = dis; // j -> k 的距离
                        //j -> k 经过 i -> k ,所以设置 i -> k 的中转给m2[j][k]
                        m2[j][k] = m2[i][k];
                    }
                }
            }
        }
    }
}
