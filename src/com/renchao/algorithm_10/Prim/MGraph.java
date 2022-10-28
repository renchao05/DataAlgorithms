package com.renchao.algorithm_10.Prim;

import java.util.Arrays;

public class MGraph {
    public int vertex;  //节点个数
    public char[] data; //节点数据
    public int[][] weight;  //边的权值

    public MGraph(char[] data, int[][] weight) {
        this.vertex = data.length;
        this.data = Arrays.copyOf(data, data.length);
        this.weight = Arrays.copyOf(weight, weight.length);
    }
}
