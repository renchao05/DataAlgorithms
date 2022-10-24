package com.renchao.hashtab;

public class HashTabTest {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        hashTab.add(new Emp(100,"任超"));
        hashTab.add(new Emp(107,"任超22"));
        hashTab.add(new Emp(101,"王道月"));
        hashTab.list();
        System.out.println("==========下面是查找的=========");
        System.out.println(hashTab.find(100));
        System.out.println(hashTab.find(107));
        System.out.println(hashTab.find(109));
        hashTab.delete(101);
        hashTab.delete(107);

        System.out.println("==========删除以后的=========");
        hashTab.list();

    }
}

class HashTab {
    public final EmpLinkedList[] linkedListArr;
    private final int size;

    // 构造
    public HashTab(int size) {
        this.linkedListArr = new EmpLinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            linkedListArr[i] = new EmpLinkedList();
        }
    }


    // 查找
    public Emp find(int id) {
        return linkedListArr[hashFun(id)].find(id);
    }

    // 删除
    public boolean delete(int id) {
        return linkedListArr[hashFun(id)].delete(id);
    }

    // 添加
    public void add(Emp emp) {
        linkedListArr[hashFun(emp.id)].add(emp);
    }

    // 打印输出
    public void list() {
        for (int i = 0; i < size; i++) {
            linkedListArr[i].list(i + 1);
        }
    }

    // 根据id获取所在数组的索引
    public int hashFun(int id) {
        return id % size;
    }
}

class EmpLinkedList {
    public Emp head = null;

    // 查找
    public Emp find(int id) {
        if (head == null)
            return null;
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                return temp;
            }
            if (head.next == null)
                return null;
            temp = temp.next;
        }
    }

    // 删除
    public boolean delete(int id) {
        if (head == null)
            return false;
        if (head.id == id) {
            head = head.next;
            return true;
        }
        Emp temp = head;
        while (true) {
            if (head.next == null)
                return false;
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
    }

    // 添加
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = emp;
    }

    // 打印输出
    public void list(int n) {
        if (head == null) {
            System.out.println(n + " 链表为空");
            return;
        }
        System.out.print(n + " 链表为:");
        Emp temp = head;
        while (true) {
            System.out.print(temp + "\t");
            if (temp.next == null) {
                System.out.println();
                return;
            }
            temp = temp.next;
        }
    }


}


class Emp {
    public int id;
    public String name;
    public Emp next;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}