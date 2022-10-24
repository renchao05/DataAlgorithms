package com.renchao.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.order();
    }

    /**
     * 创建霍夫曼树
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr) {
        // 把数列放入集合
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        // 循环处理，直到所有的数据都被处理
        while (nodes.size() > 1) {
            // 加入了新的根节点权值后，需要重新排序
            Collections.sort(nodes);
            // 取出两个最小权值
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 建新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            Node node = new Node(leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;

            // 删除已处理的两个，然后把根节点加入到集合
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(node);
        }
        // 当每个循环介绍后，集合里只剩一个元素的时候，则这个元素就是树的根节点。
        return nodes.get(0);
    }
}
