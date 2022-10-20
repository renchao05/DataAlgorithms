package com.renchao.binarysorttree;

public class AVLTreeTest {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {1,2,3,4,5,6,7,8,9};
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr = {10, 11, 7, 6, 8, 9};
        BinarySortTree bst = new BinarySortTree();
        for (int i : arr) {
            bst.add(new Node(i));
        }
        bst.preOrder();
        System.out.println();
        System.out.println("左高度：" + bst.getRoot().leftHeight());
        System.out.println("右高度：" + bst.getRoot().rightHeight());
    }
}
