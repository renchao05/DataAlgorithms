package com.renchao.linkedlist;

/**
 * 单向链表练习
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        HeroNode h1 = new HeroNode(1, "任超", "爸爸");
        HeroNode h2 = new HeroNode(20, "王道月", "妈妈");
        HeroNode h3 = new HeroNode(30, "任子馨", "姐姐");
        HeroNode h4 = new HeroNode(40, "任传睿", "弟弟");
        sll.add(h3);
        sll.add(h1);
        sll.add(h1);
        sll.add(h4);
        sll.add(h2);
        sll.show2(sll.head);
        sll.show();
        System.out.println("反转后。。。。。");
        sll.reversal2(sll.head);
        sll.show();
        SingleLinkedList sll2 = new SingleLinkedList();
        HeroNode h5 = new HeroNode(6, "任超", "爸爸");
        HeroNode h6 = new HeroNode(25, "王道月", "妈妈");
        HeroNode h7 = new HeroNode(15, "任子馨", "姐姐");
        HeroNode h8 = new HeroNode(9, "任传睿", "弟弟");
        sll2.add(h8);
        sll2.add(h7);
        sll2.add(h6);
        sll2.add(h5);
        sll2.add(h4);
        System.out.println("=======sll===========");
        sll.show();
        System.out.println("========sll2==========");
        sll2.show();
        System.out.println("==========下面是合并后的========");
        sll.addAll(sll2);
        sll.show();

        System.out.println("=======下面是反向遍历，数组方法===========");
        sll.reverse();

        System.out.println("=======上面是反向遍历，数组方法===========");
        sll.reversal();

        sll.show();
        System.out.println("系统计数" + sll.size);
        System.out.println("方法：" + sll.size());
        int rec = 2;

        System.out.println(sll.getHead(sll.size - rec + 1));

        System.out.println("下面是使用倒数的方法=============");
        for (int i = 0; i < sll.size; i++) {
            System.out.println(sll.getHead(sll.size - i));
        }
    }
}

class SingleLinkedList {
    public final HeroNode head = new HeroNode(0, null, null);
    public int size = 0;

    //添加数据
    public void add(HeroNode hero) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null)
                break;
            if (temp.next.no == hero.no) {
                System.out.println("人物不能重复添加！");
                return;
            }
            if (temp.next.no > hero.no)
                break;
            temp = temp.next;
        }
        hero.next = temp.next;
        temp.next = hero;
        size++;
    }

    //链表合并
    public void addAll(SingleLinkedList list) {
        HeroNode temp = list.head.next;
        HeroNode temp2;
        while (true) {
            if (temp == null)
                return;
            temp2 = temp.next;
            add(temp);
            temp = temp2;
        }
    }

    //常规逆向打印
    public void reverse() {
        if (size == 0)
            return;
        HeroNode[] hns = new HeroNode[size];
        HeroNode temp = head.next;
        for (int i = 0; i < size; i++) {
            hns[i] = temp;
            temp = temp.next;
        }

        for (int i = size - 1; i >= 0; i--) {
            System.out.println(hns[i]);
        }
    }

    //递归逆向打印
    public void show2(HeroNode head) {
        if (head.next == null)
            return;
        show2(head.next);
        System.out.println(head.next);
    }

    //普通打印
    public void show() {
        HeroNode temp = head;
        if (temp.next == null)
            System.out.println("没有数据。。。。");
        while (true) {
            if (temp.next == null)
                break;
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    //修改节点name
    public void revise(int no, String name) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有查询到要修改的数据。");
                return;
            }
            temp = temp.next;
            if (temp.no == no) {
                temp.name = name;
                return;
            }
        }
    }

    //计算链表大小
    public int size() {
        HeroNode temp = head;
        int num = 0;
        while (true) {
            if (temp.next == null)
                return num;
            temp = temp.next;
            num++;
        }
    }

    //删除链表节点
    public void delete(int no) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有查询到要修改的数据。");
                return;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    //查询指定序号的节点
    public HeroNode getHead(int key) {
        if (key > size) {
            System.out.println("没有要查询的数据。。。");
            return null;
        }
        HeroNode temp = head;
        for (int i = 0; i < key; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //递归反转
    public HeroNode reversal2(HeroNode head) {
        //把第一个节点指针值为空
        if (this.head == head) {
            if (head.next == null)
                return null;
            return reversal2(head.next).next = null;
        }
        //到最后一个节点后，开始返回
        if (head.next == null) {
            this.head.next = head;
            return head;
        }
        return reversal2(head.next).next = head;
    }

    //常规反转
    public void reversal() {
        if (size == 1)
            return;
        HeroNode temp = head.next;
        HeroNode temp1 = temp.next;
        temp.next = null;
        HeroNode temp2 = temp1.next;
        while (true) {
            temp1.next = temp;
            temp = temp1;
            if (temp2 == null)
                break;
            temp1 = temp2;
            temp2 = temp2.next;
        }
        head.next = temp;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}