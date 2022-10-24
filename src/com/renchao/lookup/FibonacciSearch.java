package com.renchao.lookup;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(fibonacci(arr, 100));
    }

    /**
     * 斐波那契 查找算法
     * @param arr
     * @param value
     */
    public static int fibonacci(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        // 根据数组长度获取斐波那契数列
        int[] f = fib(arr.length, 0, 1, 0);
        int fx = f.length - 1;  // 记录斐波那契数的索引。
        // 新建与最大的斐波那契数一样大的数组。
        int[] temp = Arrays.copyOf(arr, f[fx]);
        // 把后面的多余部分都填充为数值的最后一个数值
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            // 获取斐波那契数列第 n-1 的值作为mid,后面减1是为了与数组的索引对齐
            mid = left + f[fx - 1] - 1;
            // 向左搜索
            if (value < temp[mid]) {
                right = mid - 1;    // 调整right值
                fx--;   // 向左，是斐波那契数列是 f(n-1)
                // 向右搜索
            } else if (value > temp[mid]) {
                left = mid + 1;
                fx -= 2;   // 向右，是斐波那契数列是 f(n-2)
                // 既不大于，也不小于，说明是等于，就找到目标值
            } else {
                // mid 大于数组的最大索引，说明是最后一个数，则返回最后一个数的索引
                if (mid >= arr.length - 1)
                    return arr.length - 1;
                return mid;
            }
        }
        return -1;
    }

    /**
     * 根据目标数组的长度生成斐波那契数列
     * @param len   目标数组长度
     * @param f1    f(n-2)
     * @param f2    f(n-1)
     * @param index 斐波那契数的索引
     */
    public static int[] fib(int len, int f1, int f2, int index) {
        if (f2 > len) {
            int[] f = new int[index + 2];
            f[index] = f1;
            f[index + 1] = f2;
            return f;
        }
        int[] f = fib(len, f2, f1 + f2, index + 1);
        f[index] = f1;
        return f;
    }
}
