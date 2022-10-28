package com.renchao.Floyd;

import com.renchao.Dijkstra.Graph;

import java.util.Arrays;

public class FloydDemo {
    public static void main(String[] args) {
        FloydGraph fg = new FloydGraph();
        fg.floyd();
        fg.show();
        fg.route('C','D');
    }
}
