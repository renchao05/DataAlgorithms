package com.renchao.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        SingleLinkedList2 sl = new SingleLinkedList2();
        for (int i = 0; i < 125; i++) {
            sl.add(new Child(i + 1));
        }

        sl.show();
        System.out.println("=========");
//        sl.delete(5);
//        sl.show();
        sl.jP(10, 20);
    }
}

class SingleLinkedList2 {
    public Child last;
    public int size = 0;

    //添加数据
    public void add(Child child) {
        if (last == null) {
            last = child;
            last.next = child;
            size++;
            return;
        }
        child.next = last.next;
        last.next = child;
        last = child;
        size++;
    }

    //打印
    public void show() {
        if (last == null)
            return;
        for (int i = 0; i < size; i++) {
            System.out.println(last.next);
            last = last.next;
        }
    }

    public void delete(int no) {
        if (last == null)
            return;
        if (last.next == last && last.no == no) {
            last = null;
            size = 0;
            return;
        }
        if (last.no == no) {
            for (int i = 0; i < size - 1; i++) {
                last = last.next;
            }
            last.next = last.next.next;
            size--;
        }
        Child temp = last;
        for (int i = 0; i < size; i++) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    //约瑟夫环问题，k开始的索引，m数到的数
    public void jP(int k, int m) {
        if (k < 1 || k > size)
            return;
        Child temp = last;
        for (int i = 0; i < k; i++) {
            temp = temp.next;
        }
        while (true) {
            if (last == null)
                break;
            for (int i = 0; i < m - 1; i++) {
                temp = temp.next;
            }
            System.out.print(temp + "\t");
            delete(temp.no);
            temp = temp.next;
        }

    }
}

class Child {
    public int no;
    public Child next;

    public Child(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return no + "";
    }
}