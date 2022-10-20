package com.renchao.queue;

public class QueueTest {
    private int rear = -1;
    private int front = -1;
    private Integer[] ints;
    private boolean position = true;//true 代表上，false代表下

    public QueueTest(int len) {
        this.ints = new Integer[len];
    }

    public boolean add(int i) {
        if (position) {
            if (rear == ints.length - 1) {
                if (front == -1) {
                    System.out.println("队列已满。");
                    return false;
                }
                rear = 0;
                position = !position;
                ints[rear] = i;
                return true;
            }
            ints[++rear] = i;
            return true;
        }
        if (front == rear) {
            System.out.println("队列已满。");
            return false;
        }
        if (rear == ints.length - 1) {
            rear = 0;
            position = !position;
            ints[++rear] = i;
            return true;
        }
        ints[++rear] = i;
        return true;
    }

    public Integer out() {
        if (position) {
            if (front == rear)
                return null;
            return ints[++front];
        }
        if (front == ints.length - 1){
            position = !position;
            front = 0;
            return ints[front];
        }
        return ints[++front];
    }
}
