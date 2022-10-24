package com.renchao.tree.arr;

/**
 * 线索化二叉树
 */
public class HeroTree {
    private Hero root;
    private Hero pre; // 记录前驱节点

    //线索化二叉树-中序
    public void threadedNodes() {
        threadedNodes(root);
        pre = null;// 清空pre
    }

    private void threadedNodes(Hero hero) {
        if (hero == null)
            return;
        threadedNodes(hero.left);

        // 设置当前节点的前驱节点。【注意：第一个节点是没有前驱节点的。】
        if (hero.left == null) {
            hero.left = pre;
            hero.leftType = 1;
        }
        // 设置前驱节点的后置节点为当前节点
        if (pre != null && pre.right == null) {
            pre.right = hero;
            pre.rightType = 1;
        }
        pre = hero; // 处理完当前节点，把当前节点置为下一个节点的前置节点。

        threadedNodes(hero.right);
    }


    //二叉树to数组
    public int[] treeToArray() {
        int[] arr = new int[7];
        arr[0] = root.id;
        root.treeToArray(arr, root, 0);
        return arr;
    }


    //数组转二叉树
    public void arrayToTree(int[] arr) {
        root = new Hero(arr[0], "aa");
        root.arrayToTree(arr, root, 0);
    }

    //添加数据，只能线索化之前添加，没有考虑线索化后的情况
    public void add(Hero hero) {
        if (this.root == null) {
            this.root = hero;
            return;
        }
        root.add(hero);
    }


    //中序遍历-必须序列化后的
    public void threadedShow() {
        Hero temp = root;
        while (temp != null) {
            // 因为是中序线索化的，一直向左找
            while (temp.leftType == 0) {
                temp = temp.left;
            }
            System.out.println(temp);
            // 后继节点
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.println(temp);
            }
            // 不是后继节点，就是右子节点，temp.right 为空时，表示遍历的最后一个节点。结束循环
            temp = temp.right;
        }
    }
}
