package com.renchao.huffmantree;

import com.renchao.tree.Hero;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.order();
    }

    public static Node createHuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node node = new Node(leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(node);
        }
        return nodes.get(0);
    }
}
