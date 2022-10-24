package com.renchao.tree.arr;

import org.junit.Test;

public class ArrDemo {

    /**
     * 测试数组二叉树遍历
     */
    @Test
    public void testArrBinaryTree() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("========前序遍历========");
        arrBinaryTree.preorder();
        System.out.println("========中序遍历========");
        arrBinaryTree.middleOrder();
        System.out.println("========后序遍历========");
        arrBinaryTree.postorder();
    }

    /**
     * 测试线索化，遍历线索化二叉树
     */
    @Test
    public void testThreaded() {
        HeroTree heroTree = new HeroTree();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        heroTree.arrayToTree(arr);// 数组转二叉树
        heroTree.threadedNodes();// 二叉树线索化
        heroTree.threadedShow();// 遍历线索化二叉树
    }



    /**
     * 数组二叉树中序遍历
     */
    public static void arrShowTree(int[] arr, int i) {
        if (i >= arr.length)
            return;
        arrShowTree(arr, 2 * i + 1);
        System.out.println(arr[i]);
        arrShowTree(arr, 2 * i + 2);
    }
}
