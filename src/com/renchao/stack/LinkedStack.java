package com.renchao.stack;

public class LinkedStack<T> {
    public StackNode<T> head = new StackNode<>(null);

    public boolean isNull() {
        return head.next == null;
    }

    public T peek() {
        return head.next.value;
    }

    public void push(T value) {
        StackNode<T> sn = new StackNode<>(value);
        sn.next = head.next;
        head.next = sn;
    }

    public T pop() {
        if (head.next == null) {
//            System.out.println("栈空。。");
            return null;
        }
        T value = head.next.value;
        head.next = head.next.next;
        return value;
    }

    public void show() {
        StackNode<T> temp = head;
        while (true) {
            if (temp.next == null) {
                return;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
}
