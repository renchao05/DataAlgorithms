package com.renchao.recursion;


/**
 * 八皇后问题
 * 任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class EightQueensTest2 {
    public static int[] arr = {88, 88, 88, 88, 88, 88, 88, 88};//棋盘
    public static int num = 0;

    public static void main(String[] args) {
        //代表是第几个皇后
        place(0);
        System.out.println("总共：" + num);
    }


    //放皇后
    public static void place(int n) {
        if (n == 8) {
            num++;
            print();
            return;
        }
        for (int i = 0; i < 8; i++) {
            arr[n] = i;
            if (judge(n))
                place(n + 1);
        }
    }

    //打印
    public static void print() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    //判断冲突
    public static boolean judge(int n) {
        for (int k = 0; k < n; k++) {
            if (arr[n] == arr[k] || Math.abs(n - k) == Math.abs(arr[n] - arr[k]))
                return false;
        }
        return true;
    }
}
