package com.renchao.Dijkstra;

public class DijkstraDemo {
    public static void main(String[] args) {
        int index = 3;  // 出发顶点
        Graph graph = new Graph();
        graph.show();
        graph.dsj(index);
        graph.print();
    }
}
