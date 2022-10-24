package com.renchao.stack;

public class StackNode<T> {
    public T value;
    public StackNode<T> next;

    @Override
    public String toString() {
        return "" + value;
    }

    public StackNode(T value) {
        this.value = value;
    }
}
