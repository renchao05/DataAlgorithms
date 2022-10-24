package com.renchao.tree.binary;

/**
 * 顺序二叉树
 */
public class StudentTree {
    public Student root;

    // 查找
    public Student lookup(int id) {
        if (root == null) {
            return null;
        }
        return root.quickFind(id);
    }

    public boolean delete(int id) {
        if (root == null) {
            return false;
        }
        return deletes(null, root, id);
    }

    // 删除 Node
    private boolean deletes(Student parent, Student stu, int id) {
        if (id == stu.id) {
            // 先右后左，
            if (stu.right != null) {
                // 找到右节点的最小叶子节点，然后把左节点挂上去
                Student temp = stu.right;
                while (temp.left != null)
                    temp = temp.left;
                temp.left = stu.left;
                // 右节点 放到父节点
                if (parent == null) { // parent 是空，说明是 head
                    root = stu.right;
                } else if (stu.id < parent.id) {
                    parent.left = stu.right;
                } else {
                    parent.right = stu.right;
                }
            } else { // 如果右节点为空，左节点直接放到父节点
                if (parent == null) { // parent 是空，说明是 head
                    root = stu.left;
                } else if (stu.id < parent.id) {
                    parent.left = stu.left;
                } else {
                    parent.right = stu.left;
                }
            }
            return true;
        }
        // 向左查找
        if (id < stu.id && stu.left != null) {
            return deletes(stu, stu.left, id);
        }
        // 向右查找
        if (id > stu.id && stu.right != null) {
            return deletes(stu, stu.right, id);
        }
        return false;
    }

    // 添加
    public void add(Student stu) {
        if (this.root == null) {
            this.root = stu;
            return;
        }
        Student temp = root;
        while (true) {
            if (stu.id < temp.id) {
                if (temp.left == null) {
                    temp.left = stu;
                    return;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = stu;
                    return;
                }
                temp = temp.right;
            }
        }
    }

    /**
     * 遍历
     *
     * @param mode 1前序遍历；2中序遍历；3后序遍历
     */
    public void show(int mode) {
        if (root == null) {
            System.out.println("树是空的。。。");
            return;
        }
        switch (mode) {
            case 1:
                root.preorder();
                break;
            case 2:
                root.middleOrder();
                break;
            case 3:
                root.postorder();
                break;
            default:
                System.out.println("输入的遍历方式不对！（1，2，3）");
        }
    }
}
