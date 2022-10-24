package com.renchao.tree.arr;

public class Hero {
    public int id;
    private String name;
    public Hero left;
    public Hero right;

    // 序列化二叉树
    public int leftType;
    public int rightType;


    // 二叉树to数组
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


    //添加数据，只能线索化之前添加
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
