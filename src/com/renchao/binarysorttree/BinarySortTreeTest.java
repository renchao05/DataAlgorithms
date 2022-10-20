package com.renchao.binarysorttree;

import java.util.Arrays;

public class BinarySortTreeTest {
    public static void main(String[] args) {
//        int[] arr = {7,3,1,5,10,12,13,11,8,9};
        int[] arr = {4,3,6,5,7,8};
        BinarySortTree bst = new BinarySortTree();
        for (int i : arr) {
            bst.add(new Node(i));
        }

    }
}
