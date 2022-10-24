package com.renchao.tree.huffmancode;

public class Node implements Comparable<Node>{
    public Byte data;
    public int weight;
    public Node left;
    public Node right;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }


    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

}
