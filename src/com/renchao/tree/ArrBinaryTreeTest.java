package com.renchao.tree;

import java.util.Arrays;

public class ArrBinaryTreeTest {
    public static void main(String[] args) {
////        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        int[] arr = {8, 2, 7, 7, 1, 9, 6, 3, -1, -9, 5, 12, 10, 11, -2, 4};
//        sort2(arr);
//        System.out.println(Arrays.toString(arr));

//        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        System.out.println("================");
//        arrBinaryTree.infixOrder();
//        System.out.println("================");
//        arrBinaryTree.postOrder();

        long l1;
        long l2;
        int[] ints = new int[80000000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 800000);
        }

        l1 = System.currentTimeMillis();
        sort2(ints);
        l2 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(ints));
        System.out.println("堆排序耗时:" + (l2 - l1));

    }

    public static void sort(int[] arr) {
        int k;
        int temp;
        for (int i = arr.length; i >= 2; i--) {
            for (int j = 1; (k = i / 2 - j) >= 0; j++) {
                if (k * 2 + 2 > i - 1 || arr[k * 2 + 1] > arr[k * 2 + 2]) {
                    if (arr[k * 2 + 1] > arr[k]) {
                        temp = arr[k];
                        arr[k] = arr[k * 2 + 1];
                        arr[k * 2 + 1] = temp;
                    }
                } else {
                    if (arr[k * 2 + 2] > arr[k]) {
                        temp = arr[k];
                        arr[k] = arr[k * 2 + 2];
                        arr[k * 2 + 2] = temp;
                    }
                }
            }
            temp = arr[0];
            arr[0] = arr[i - 1];
            arr[i - 1] = temp;
        }
//        if (arr[0] > arr[1]) {
//            temp = arr[0];
//            arr[0] = arr[1];
//            arr[1] = temp;
//        }
    }

    public static void sort2(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //len 数组长度减1是让最后一个数组不进行处理，因为最后一个数组需要跟最大数进行交换的
            adjustHeap(arr, i, arr.length - 1);
        }

        for (int i = arr.length - 1; i >=0 ; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    public static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        //传过来的len 是减1的 数组长度减1是让最后一个数组不进行处理，因为最后一个数组需要跟最大数进行交换的
        for (int k = i * 2 + 1; k < len; k = i * 2 + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1])
                k++;
            if (arr[k] > temp) {//如果下面的节点受到影响了，还要循环处理
                arr[i] = arr[k];
                i = k;//跳到下一个节点
            } else {
                break;
            }
            arr[k] = temp;
        }
    }
}


class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历
    public void preOrder(int i) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        System.out.println(arr[i]);
        if (2 * i + 1 < arr.length)
            preOrder(2 * i + 1);
        if (2 * i + 2 < arr.length)
            preOrder(2 * i + 2);
    }

    public void preOrder() {
        preOrder(0);
    }

    //中序遍历
    public void infixOrder(int i) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        if (2 * i + 1 < arr.length)
            infixOrder(2 * i + 1);
        System.out.println(arr[i]);
        if (2 * i + 2 < arr.length)
            infixOrder(2 * i + 2);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    //后序遍历
    public void postOrder(int i) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        if (2 * i + 1 < arr.length)
            postOrder(2 * i + 1);
        if (2 * i + 2 < arr.length)
            postOrder(2 * i + 2);
        System.out.println(arr[i]);
    }

    public void postOrder() {
        postOrder(0);
    }


}