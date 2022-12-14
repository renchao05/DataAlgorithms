package com.renchao.graph;

public class Demo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] str = {"A", "B", "C", "D", "E"};
        for (String s : str) {
            graph.insertVertex(s);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(1,2,1);

        graph.showGraph();
//        graph.dfs(); // 深度优先遍历
        graph.bfs();    // 广度优先遍历
    }
}
