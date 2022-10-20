package com.renchao.tree;

public class HeroTree {
    private Hero root;

    private Hero pre;

    public Hero lookup(int value) {
        Hero temp = root;
        while (true) {
            if (temp == null)
                return null;
            if (value < temp.id) {
                temp = temp.left;
            } else if (value > temp.id) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
    }

    //线索化二叉树-中序
    public void threadedNodes(Hero hero) {
        if (hero == null)
            return;
        threadedNodes(hero.left);
        if (hero.left == null) {
            hero.left = pre;
            hero.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = hero;
            pre.rightType = 1;
        }
        pre = hero;

        threadedNodes(hero.right);
    }

    public void threadedNodes() {
        threadedNodes(root);
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

    //删除数据
    public boolean delete(int id) {
        if (root == null) {
            System.out.println("空树，没有数据");
            return false;
        }

        //开始删除根节点======================
        if (root.id == id) {
            if (root.left == null && root.right == null) {
                root = null;    //简单处理，整个节点删除
                return true;
            }
            if (root.left != null && root.right == null) {
                root = root.left;
                return true;
            }
            if (root.left == null) {
                root = root.right;
                return true;
            }
            Hero temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            this.delete(temp.id);
            temp.left = root.left;
            temp.right = root.right;
            root = temp;
            return true;
        }
        //======================

        return root.delete(id);
    }

    //添加数据
    public void add(Hero hero) {
        if (this.root == null) {
            this.root = hero;
            return;
        }
        root.add(hero);
    }

    //遍历数据
    public void order() {
        if (root == null) {
            System.out.println("是空树。。。");
            return;
        }
        root.preOrder();
    }

    //前序遍历-序列化后的
    public void order2() {
//        if (root == null) {
//            System.out.println("树是空的。。。");
//            return;
//        }
        Hero temp = root;
        while (temp != null) {
            while (temp.leftType == 0) {
                temp = temp.left;
            }
            System.out.println(temp);
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }
}
