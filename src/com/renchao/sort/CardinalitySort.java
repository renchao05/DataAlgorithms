package com.renchao.sort;

import java.util.Arrays;

public class CardinalitySort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        cardinality(arr);
        System.out.println(Arrays.toString(arr));

        long l1;
        long l2;
        int[] ints = new int[8000000];
        int[] temp = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 800000);
        }

        //基数排序
        l1 = System.currentTimeMillis();
        cardinality(ints);
        l2 = System.currentTimeMillis();
        System.out.println("基数排序耗时:" + (l2 - l1));
    }

    public static void cardinality(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max)
                max = i;
        }
        int[][] bucket = new int[10][arr.length];
        int[] pointer = new int[10];
        int maxLen = (max + "").length();
        int digit;
        int index;
        for (int i = 0,n =1; i < maxLen; i++,n *= 10) {
            for (int ii : arr) {
                digit = ii / n % 10;
                bucket[digit][pointer[digit]++] = ii;
            }
            index = 0;
            for (int j = 0; j < 10; j++) {
                if (pointer[j] != 0) {
                    for (int k = 0; k < pointer[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                pointer[j] = 0;
            }
        }
    }
}


