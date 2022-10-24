package com.renchao.tree.binary;

public class Student {
    public int id;
    private String name;
    public Student left;
    public Student right;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 快速查找,必须有序
     */
    public Student quickFind(int id) {
        if (id == this.id) {
            return this;
        }
        if (id < this.id && this.left != null) {
            return this.left.quickFind(id);
        }
        if (id > this.id && this.right != null) {
            return this.right.quickFind(id);
        }
        return null;
    }

    //前序查找
    public Student preLookup(int id) {
        if (this.id == id)
            return this;
        if (this.left != null) {
            Student stu = this.left.preLookup(id);
            if (stu != null)
                return stu;
        }
        if (this.right != null) {
            return this.right.preLookup(id);
        }
        return null;
    }

    //前序遍历
    public void preorder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preorder();
        if (this.right != null)
            this.right.preorder();
    }

    //中序遍历 中序
    public void middleOrder() {
        if (this.left != null)
            this.left.middleOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.middleOrder();
    }

    //后序遍历
    public void postorder() {
        if (this.left != null)
            this.left.postorder();
        if (this.right != null)
            this.right.postorder();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
