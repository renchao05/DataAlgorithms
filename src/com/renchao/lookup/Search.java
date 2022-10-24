package com.renchao.lookup;

import com.renchao.sort.SortDemo;

import java.util.ArrayList;
import java.util.Arrays;

public class Search {
    public static void main(String[] args) {
//        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = {-3, 2, 5, 0, 10, -7, 9, 9, 9, 8, -25, -15, 25, 20};


        SortDemo.bubblingSort(arr);
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> binary = binary(arr, 0, arr.length - 1, 25);
        System.out.println(binary);
    }


    /**
     * 二分查找
     *
     * @param arr   目标有序数组
     * @param left  左边界索引
     * @param right 右边界索引
     * @param value 需要查找的目标值
     * @return 查找到的目标值索引集合，包含重复值
     */
    public static ArrayList<Integer> binary(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1])
            return null;
        System.out.println("被调用");
//        int mid = (left + right) / 2;
        // 插值查找公式
        int mid = (value - arr[left]) / (arr[right] - arr[left]) * (right - left) + left;

        int midVal = arr[mid];
        // 向左右递归查找
        if (value > midVal)
            return binary(arr, mid + 1, right, value);
        if (value < midVal)
            return binary(arr, left, mid - 1, value);

        // 既不大于也不小于，就是等于，说明找到，执行下面代码
        ArrayList<Integer> als = new ArrayList<>();
        als.add(mid);
        int midT = mid - 1; // 向左查找重复值
        while (midT >= left && arr[midT] == arr[mid])
            als.add(midT--);
        midT = mid + 1; // 向右查找重复值
        while (midT <= right && arr[midT] == arr[mid])
            als.add(midT++);
        return als;
    }
}
