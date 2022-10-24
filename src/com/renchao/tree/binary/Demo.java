package com.renchao.tree.binary;

/**
 * 二叉树演示
 */
public class Demo {
    public static void main(String[] args) {
        StudentTree studentTree = new StudentTree();
        studentTree.add(new Student(7,"aa"));
        studentTree.add(new Student(3,"bb"));
        studentTree.add(new Student(1,"cc"));
        studentTree.add(new Student(5,"dd"));
        studentTree.add(new Student(10,"ee"));
        studentTree.add(new Student(12,"ff"));
        studentTree.add(new Student(13,"gg"));
        studentTree.add(new Student(11,"hh"));
        studentTree.add(new Student(8,"kk"));
        studentTree.show(3);

        System.out.println("删除============");
        System.out.println(studentTree.delete(7));
        System.out.println(studentTree.delete(3));
        System.out.println(studentTree.delete(-100));

        System.out.println("遍历============");
        studentTree.show(1); //1前序遍历；2中序遍历；3后序遍历

        System.out.println("查找============");
        System.out.println(studentTree.lookup(13));
    }
}