package com.renchao.tree;

public class Hero {
    public int id;
    private String name;
    public Hero left;
    public Hero right;

    //序列化二叉树
    public int leftType;
    public int rightType;


    //二叉树to数组
    public void treeToArray(int[] arr, Hero hero, int i) {
        if (i * 2 + 1 >= arr.length)
            return;
        arr[2 * i + 1] = hero.left.id;
        arr[2 * i + 2] = hero.right.id;
        treeToArray(arr, hero.left, 2 * i + 1);
        treeToArray(arr, hero.right, 2 * i + 2);
    }

    //数组转二叉树
    public void arrayToTree(int[] arr, Hero hero, int i) {
        if (i * 2 + 1 >= arr.length)
            return;
        hero.left = new Hero(arr[2 * i + 1], "AA");
        arrayToTree(arr, hero.left, 2 * i + 1);
        hero.right = new Hero(arr[2 * i + 2], "AA");
        arrayToTree(arr, hero.right, 2 * i + 2);

    }

    //删除数据
    public boolean delete(int id) {
        Hero temp;
        if (id < this.id) {
            if (this.left == null)
                return false;
            if (this.left.id == id) {
                //开始删除节点======================
                //this.left左右都为空
                if (this.left.left == null && this.left.right == null) {
                    this.left = null;   //简单处理，整个节点删除
                    return true;
                }

                //this.left左边不空 右边空
                if (this.left.left != null && this.left.right == null) {
                    this.left = this.left.left;
                    return true;
                }

                //this.left左边空 右边不空
                if (this.left.left == null) {
                    this.left = this.left.right;
                    return true;
                }

                //this.left左右都不空
                temp = this.left.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                this.delete(temp.id);
                temp.left = this.left.left;
                temp.right = this.left.right;
                this.left = temp;
                return true;
                //=========================

            } else {
                return this.left.delete(id);
            }
        } else {
            if (this.right == null)
                return false;
            if (this.right.id == id) {

                //开始删除节点======================
                //this.left左右都为空
                if (this.right.left == null && this.right.right == null) {
                    this.right = null;   //简单处理，整个节点删除
                    return true;
                }

                //this.left左边不空 右边空
                if (this.right.left != null && this.right.right == null) {
                    this.right = this.right.left;
                    return true;
                }

                //this.left左边空 右边不空
                if (this.right.left == null) {
                    this.right = this.right.right;
                    return true;
                }

                //this.left左右都不空
                temp = this.right.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                this.delete(temp.id);
                temp.left = this.right.left;
                temp.right = this.right.right;
                this.right = temp;
                return true;
                //=========================
            } else {
                return this.right.delete(id);
            }
        }
    }

    //添加数据
    public void add(Hero hero) {
        if (hero.id < this.id) {
            if (this.left == null) {
                this.left = hero;
                return;
            }
            this.left.add(hero);
        } else {
            if (this.right == null) {
                this.right = hero;
                return;
            }
            this.right.add(hero);
        }
    }


    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.preOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.preOrder();
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
