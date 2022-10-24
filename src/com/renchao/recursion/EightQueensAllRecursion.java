package com.renchao.recursion;

/**
 * 八皇后问题：全部递归，没有回溯，可能会发生栈溢出异常，需添加运行参数 -Xss2m
 * 棋盘对应 arr 下标表示第几行，也就是第几个皇后。
 * 值表示当前行的列位置。
 * 比如：arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列
 */
public class EightQueensAllRecursion {
    public static int num = 0;

    public static void main(String[] args) {
        int[] arr = {88, 88, 88, 88, 88, 88, 88, 88};//棋盘
        int i = 0;//开始行
        int val = 0;//开始列
        eightQueens(arr,i,val);
        System.out.println("总数：" + num);
    }

    // 打印
    public static void print(int[] arr) {
        num++;  // 结果计数
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    // 主方法
    public static void eightQueens(int[] arr, int i, int val) {
        int temp;
        // 第一行情况
        if (i == 0) {
            // 当第一行的所有位置都回溯完，程序结束
            if (val == 8)
                return;
            arr[i] = val;
            eightQueens(arr, i + 1, 0);
            return;
        }

        // 超过最后一行，表示得到一个结果
        if (i == 8) {
            print(arr); //打印输出
            //得到一个解后，进行回溯。
            temp = arr[i-1] + 1;    // 上一行的下一列
            arr[i-1] = 88;  //  上一行的当前列置恢复
            eightQueens(arr, i - 1, temp);
            return;
        }

        // 对当前行的每个位置放置。
        if (val < 8) {
            //循环判断与前面已经放的有没有冲突
            for (int k = 0; k < i; k++) {
                if (val == arr[k] || (val - i + k) == arr[k] || (val + i - k) == arr[k]) {
                    // 有冲突就继续试当前行的下一列
                    eightQueens(arr, i, val + 1);
                    return;
                }
            }
            // 没有冲突，在该位置进行放置，然后进入下一行
            arr[i] = val;
            eightQueens(arr, i + 1, 0);
        } else {
            // val大于等于8，回溯到上一行
            temp = arr[i-1] + 1;    // 上一行的下一列
            arr[i-1] = 88;  //  上一行的当前列置恢复
            eightQueens(arr, i - 1, temp);
        }
    }
}