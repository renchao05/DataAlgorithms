package com.renchao.tree.binarysorttree;

public class BinarySortTree {
    private Node root;

    public boolean delete(int value) {
        if (root == null) {
            System.out.println("空树，没有数据");
            return false;
        }
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
        root.preorder();
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("树是空的。。");
            return;
        }
        root.inorder();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
