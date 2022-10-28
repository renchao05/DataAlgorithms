package com.renchao.Horse;

import java.awt.*;

public class PNode implements Comparable<PNode> {
    private final Point point;
    private final int size;

    public Point getPoint() {
        return point;
    }

    public int getSize() {
        return size;
    }

    public PNode(Point point, int size) {
        this.point = point;
        this.size = size;
    }

    @Override
    public String toString() {
        return "PNode{" +
                "size=" + size +
                '}';
    }

    @Override
    public int compareTo(PNode o) {
        return this.size - o.size;
    }
}
