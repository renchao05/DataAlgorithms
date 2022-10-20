package com.renchao.queue;

public class QueueTest2 {
    private int rear;
    private int front;
    private Integer[] ints;
    private int max;

    public QueueTest2(int max) {
        this.max = max;
        this.ints = new Integer[max];
        this.rear = 0;
        this.front = 0;
    }

    public boolean add(int i) {
        if (((rear + 1) % max) == front) {
            System.out.println("序列已满。。");
            return false;
        }
        if (rear == max) {
            rear = 0;
            ints[rear++] = i;
            return true;
        }
        ints[rear++] = i;
        return true;
    }

    public Integer out() {
        if (front == rear)
            return null;
        if (front == max - 1) {
            front = 0;
            return ints[front++];
        }
        return ints[front++];
    }

    public void show() {
        for (int i = front; i != rear ; i = (i + 1) % max) {
            System.out.println(ints[i]);
        }
    }
}
