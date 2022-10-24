package com.renchao.tree.arr;

/**
 * 数组二叉树遍历
 */
public class ArrBinaryTree {
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历
    public void preorder() {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        preorder(0);
    }

    private void preorder(int i) {
        System.out.println(arr[i]);
        if (2 * i + 1 < arr.length)
            preorder(2 * i + 1);
        if (2 * i + 2 < arr.length)
            preorder(2 * i + 2);
    }


    //中序遍历
    public void middleOrder() {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        this.middleOrder(0);
    }

    private void middleOrder(int i) {
        if (2 * i + 1 < arr.length)
            this.middleOrder(2 * i + 1);
        System.out.println(arr[i]);
        if (2 * i + 2 < arr.length)
            this.middleOrder(2 * i + 2);
    }


    //后序遍历
    public void postorder() {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空。");
            return;
        }
        postorder(0);
    }

    private void postorder(int i) {
        if (2 * i + 1 < arr.length)
            postorder(2 * i + 1);
        if (2 * i + 2 < arr.length)
            postorder(2 * i + 2);
        System.out.println(arr[i]);
    }
}
