package com.renchao.recursion;


/**
 * 八皇后问题，回溯算法
 * 任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class EightQueensRecall {
    public static int[] arr = {88, 88, 88, 88, 88, 88, 88, 88};//棋盘
    public static int num = 0;

    public static void main(String[] args) {
        //参数n代表是第几个皇后
        place(0);
        System.out.println("总共：" + num);
    }


    //放皇后
    public static void place(int n) {
        // n等于8，说明8个皇后已经放置成功，然后进行回溯
        if (n == 8) {
            num++;
            print();
            return;
        }
        // 循环当前行的每一列，循环结束回溯到上一个皇后
        for (int i = 0; i < 8; i++) {
            arr[n] = i; // 当前皇后试放每一个位置
            if (judge(n))   // 判断当前位置是否有冲突，没有冲突，则试放下一个皇后
                place(n + 1);
        }
    }

    //打印输出
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
