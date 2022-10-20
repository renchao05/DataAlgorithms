package com.renchao.tree;

import java.util.Arrays;

public class TreeTest {
    public static void main(String[] args) {
        HeroTree heroTree = new HeroTree();
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        arrOrder(arr,0);
//        heroTree.arrayToTree(arr);
//
//        int[] ints = heroTree.treeToArray();
//        System.out.println(Arrays.toString(ints));

        int[] arr = {7,3,1,5,10,12,13,11,8,9};
        for (int i : arr) {
            heroTree.add(new Hero(i,"A" + i));
        }

//        heroTree.threadedNodes();
//        System.out.println(heroTree.delete(12));
//        System.out.println(heroTree.delete(13));
//        System.out.println(heroTree.delete(11));
//        System.out.println(heroTree.delete(8));
//        System.out.println(heroTree.delete(9));
//        System.out.println(heroTree.delete(7));
//        System.out.println(heroTree.delete(3));
//        System.out.println(heroTree.delete(5));
//        System.out.println(heroTree.delete(1));
//        System.out.println(heroTree.delete(10));

        System.out.println(heroTree.lookup(1));
//
//        heroTree.order();

//        System.out.println("删除后================");
//        System.out.println(heroTree.delete(7));
//        heroTree.order();
    }

    public static void arrOrder(int[] arr,int i) {
        if (i >= arr.length)
            return;

        arrOrder(arr, 2 * i + 1);
        System.out.println(arr[i]);
        arrOrder(arr, 2 * i + 2);
    }
}