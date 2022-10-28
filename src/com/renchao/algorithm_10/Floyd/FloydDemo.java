package com.renchao.algorithm_10.Floyd;


public class FloydDemo {
    public static void main(String[] args) {
        FloydGraph fg = new FloydGraph();
        fg.floyd();
        fg.show();
        fg.route('C','D');
    }
}
