package com.renchao.tree;

public class TreeTest2 {
    public static void main(String[] args) {
        HeroTree2 heroTree = new HeroTree2();
        heroTree.add(new Hero2(7,"aa"));
        heroTree.add(new Hero2(3,"bb"));
        heroTree.add(new Hero2(1,"cc"));
        heroTree.add(new Hero2(5,"dd"));
        heroTree.add(new Hero2(10,"ee"));
        heroTree.add(new Hero2(12,"ff"));
        heroTree.add(new Hero2(13,"gg"));
        heroTree.add(new Hero2(11,"hh"));
        heroTree.add(new Hero2(8,"kk"));
        heroTree.show();

//        System.out.println("删除后============");
//        System.out.println(heroTree.delete(7));
//        System.out.println(heroTree.delete(10));
//        System.out.println(heroTree.delete(-100));
//        heroTree.show();
        System.out.println("下面是查找============");
        System.out.println(heroTree.lookup(13));
    }
}


class HeroTree2 {
    public Hero2 head;

    public Hero2 lookup(int id) {
        return head.preLookup(id);
    }

    public boolean delete(int id) {
        if (id < head.id) {
            if (head.left == null)
                return false;
            return deletes(head, head.left, id);
        }
        if (id > head.id) {
            if (head.right == null)
                return false;
            return deletes(head, head.right, id);
        }
        if (head.right == null) {
            if (head.left == null) {
                head = null;
                return true;
            }
            head = head.left;
            return true;
        }
            Hero2 temp = head.right;
            while (temp.left != null)
                temp = temp.left;
            temp.left = head.left;
            head = head.right;
            return true;
    }

    private boolean deletes(Hero2 headHero2, Hero2 hero2, int id) {
        if (id < hero2.id) {
            if (hero2.left == null)
                return false;
            return deletes(hero2, hero2.left, id);
        }
        if (id > hero2.id) {
            if (hero2.right == null)
                return false;
            return deletes(hero2, hero2.right, id);
        }
        if (hero2.right == null) {
            if (hero2.id < headHero2.id) {
                headHero2.left = hero2.left;
                return true;
            }
            headHero2.right = hero2.left;
            return true;
        }
        Hero2 temp = hero2.right;
        while (temp.left != null)
            temp = temp.left;
        temp.left = hero2.left;
        if (hero2.id < headHero2.id) {
            headHero2.left = hero2.left;
            return true;
        }
        headHero2.right = hero2.left;
        return true;
    }

    //自己写的添加
    public void add(Hero2 hero2) {
        if (this.head == null) {
            this.head = hero2;
            return;
        }
        Hero2 temp = head;
        while (true) {
            if (hero2.id < temp.id) {
                if (temp.left == null) {
                    temp.left = hero2;
                    return;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = hero2;
                    return;
                }
                temp = temp.right;
            }
        }
    }

    //自己写的遍历
    public void show() {
        if (head == null) {
            System.out.println("树是空的。。。");
            return;
        }
        shows(head);
    }
    private void shows(Hero2 hero2) {
        System.out.println(hero2);
        if (hero2.left != null)
            shows(hero2.left);
        if (hero2.right != null)
            shows(hero2.right);
    }
}

class Hero2 {
    public int id;
    private String name;
    public Hero2 left;
    public Hero2 right;

    //前序查找
    public Hero2 preLookup(int id) {
        System.out.println("调用。。。");

        if (this.left != null) {
            Hero2 hero2 = this.left.preLookup(id);
            if (hero2 != null)
                return hero2;
        }



        if (this.right != null) {
            Hero2 hero2 = this.right.preLookup(id);
            if (hero2 != null)
                return hero2;
        }

        if (this.id == id)
            return this;

        return null;
    }

//    public Hero2 preLookup(int id) {
//        System.out.println("调用。。。");
//        if (this.id > id && this.left != null)
//            return this.left.preLookup(id);
//        if (this.id < id && this.right != null)
//            return this.right.preLookup(id);
//        if (this.id == id)
//            return this;
//        return null;
//    }


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

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Hero2(int id, String name) {
        this.id = id;
        this.name = name;
    }
}