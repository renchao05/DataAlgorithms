package com.renchao.binarysorttree;

public class Node {
    public int value;
    public Node left;
    public Node right;

    //左节点高度
    public int leftHeight() {
        return left.height();
    }

    //右节点高度
    public int rightHeight() {
        return right.height();
    }

    //当前节点高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //右旋转
    public void turnRight() {

        Node node = new Node(this.value);
        node.right = this.right;
        node.left = this.left.right;
        this.right = node;
        this.value = this.left.value;
        this.left = this.left.left;
    }

    //左旋转
    public void turnLeft() {

        Node node = new Node(this.value);
        node.left = this.left;
        node.right = this.right.left;
        this.left = node;
        this.value = this.right.value;
        this.right = this.right.right;
    }

    //删除节点Node
    public boolean delNode(int value) {
        Node node = this.findNode(value);
        if (node == null)
            return false;
        if (node.left != null && node.left.value == value) {
            if (node.left.left == null && node.left.right == null) {
                node.left = null;   //简单处理，整个节点删除
                return true;
            }

            //this.left左边不空 右边空
            if (node.left.left != null && node.left.right == null) {
                node.left = node.left.left;
                return true;
            }

            //this.left左边空 右边不空
            if (node.left.left == null) {
                node.left = node.left.right;
                return true;
            }

            //this.left左右都不空
            Node temp = node.left.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            node.delNode(temp.value);
            temp.left = node.left.left;
            temp.right = node.left.right;
            this.left = temp;
            return true;
        }
        if (node.right != null && node.right.value == value) {
            if (node.right.left == null && node.right.right == null) {
                node.right = null;   //简单处理，整个节点删除
                return true;
            }

            //this.left左边不空 右边空
            if (node.right.left != null && node.right.right == null) {
                node.right = node.right.left;
                return true;
            }

            //this.left左边空 右边不空
            if (node.right.left == null) {
                node.right = node.right.right;
                return true;
            }

            //this.left左右都不空
            Node temp = node.right.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            this.delNode(temp.value);
            temp.left = node.right.left;
            temp.right = node.right.right;
            node.right = temp;
            return true;
        }
        return false;
    }

    //辅助上面，删除最小节点
    public Node delMinNode() {
        Node temp = this;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp;
    }

    //查找待删除的节点的父节点
    private Node findNode(int value) {
        if (this.left != null && this.value > value) {
            if (this.left.value == value)
                return this;
            return this.left.findNode(value);
        }
        if (this.right != null) {
            if (this.right.value == value)
                return this;
            return this.right.findNode(value);
        }
        return null;
    }


    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
                return;
            }
            this.left.add(node);
        } else {
            if (this.right == null) {
                this.right = node;
                return;
            }
            this.right.add(node);
        }

        //判断左右节点是否平衡，如果不平衡就旋转
        isBalance();
    }

    //判断当前节点是否平衡
    public void isBalance() {
        if (left == null && right != null && rightHeight() > 1) {
            if (this.right.right == null || this.right.rightHeight() < this.right.leftHeight())
                this.right.turnRight();
            turnLeft();
        }
        if (right == null && left != null && leftHeight() > 1) {
            if (this.left.left == null || this.left.leftHeight() < this.left.rightHeight())
                this.left.turnLeft();
            turnRight();
        }
        if (left != null && right != null) {
            if (rightHeight() - leftHeight() > 1) {
                if (this.right.right == null || this.right.rightHeight() < this.right.leftHeight())
                    this.right.turnRight();
                turnLeft();
            }
            if (leftHeight() - rightHeight() > 1) {
                if (this.left.left == null || this.left.leftHeight() < this.left.rightHeight())
                    this.left.turnLeft();
                turnRight();
            }
        }
    }

    public void inOrder() {

        if (this.left != null)
            this.left.inOrder();
        System.out.print(this);
        if (this.right != null)
            this.right.inOrder();
    }

    public void preOrder() {
        System.out.print(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }


    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " ";
    }

}
