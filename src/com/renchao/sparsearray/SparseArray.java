package com.renchao.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        // 创建二维数值
        int[][] is = new int[11][11];
        is[3][6] = 15;
        is[4][7] = 19;
        is[8][1] = 25;
        // 打印原二维数值
        for (int[] ints : is) {
            for (int i : ints) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }

        // 统计原数组的有效数据个数
        int size = 0;
        for (int[] ints : is) {
            for (int i : ints) {
                if (i != 0)
                    size++;
            }
        }

        // 创建稀疏数组
        int[][] ints = new int[size+1][3];
        ints[0][0] = is.length;
        ints[0][1] = is[0].length;
        ints[0][2] = size;

        // 原数组 ==》 稀疏数组
        int k = 1;
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is[i].length; j++) {
                if (is[i][j] != 0) {
                    ints[k][0] = i;
                    ints[k][1] = j;
                    ints[k++][2] = is[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("======================");
        for (int[] ints1 : ints) {
            for (int i : ints1) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        // 稀疏数组 ==》 原数组
        int[][] is2 = new int[ints[0][0]][ints[0][1]];
        for (int i = 1; i < ints.length; i++) {
            is2[ints[i][0]][ints[i][1]] = ints[i][2];
        }

        // 输出原数组
        System.out.println("======is2===========");
        for (int[] ints3 : is2) {
            for (int i : ints3) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }

    }
}
