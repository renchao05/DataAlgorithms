package com.renchao.huffmantree;


public class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    //遍历
    public void order() {
        System.out.print(this + " ");
        if (this.left != null)
            this.left.order();
        if (this.right != null)
            this.right.order();
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
