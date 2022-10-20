package com.renchao.binarysorttree;

import com.renchao.tree.Hero;

public class BinarySortTree {
    private Node root;

    public void sssss() {
        System.out.println(root.left.height());
        System.out.println(root.right.height());
    }


    public boolean delete(int value) {
        if (root == null) {
            System.out.println("空树，没有数据");
            return false;
        }

        //开始删除根节点======================
        if (root.value == value) {
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
            Node temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.delNode(temp.value);
            temp.left = root.left;
            temp.right = root.right;
            root = temp;
            return true;
        }
        //======================

        return root.delNode(value);
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);

    }


    public void preOrder() {
        if (root == null) {
            System.out.println("树是空的。。");
            return;
        }
        root.preOrder();
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("树是空的。。");
            return;
        }
        root.inOrder();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
