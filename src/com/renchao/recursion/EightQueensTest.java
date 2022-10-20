package com.renchao.recursion;

public class EightQueensTest {
    public static void main(String[] args) {
        int[] arr = {88, 88, 88, 88, 88, 88, 88, 88};//棋盘
        int i = 0;//行
        int val = 0;//列
        eightQueens(arr,i,val);
    }

    // 打印
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    public static void eightQueens(int[] che, int i, int val) {
        int temp = 0;
        if (i == 0) {
            if (val == 8)
                return;
            che[i] = val;
            eightQueens(che, i + 1, 0);
            return;
        }
        if (i == 8) {
            print(che); //打印输出
            //得到一个解后，进行回溯。
            temp = che[i-1] + 1;
            che[i-1] = 88;
            eightQueens(che, i - 1, temp);
            return;
        }
        if(val < 8) {
            //循环判断与前面放的有没有冲突的
            boolean jud = true;
            for(int k = 0; k < 8; k++){
                if(val == che[k] || (val - i + k) == che[k] || (val + i - k) == che[k]) {
                    eightQueens(che, i, val + 1);
                    return;
                }
            }
            che[i] = val;
            eightQueens(che, i + 1, 0);
            return;
        }
        temp = che[i-1] + 1;
        che[i-1] = 88;
        eightQueens(che, i - 1, temp);
    }
}