package com.renchao.lookup;

import com.renchao.sort.SortTest;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = {-3, 2, 5, 0, 10, -7, 9, 9,9,8, -25, -15, 25, 20};


        SortTest.bubblingSort(arr);
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> binary = binary2(arr, 0, arr.length - 1, -7);
        System.out.println(binary);
    }


    //优化后的
    public static ArrayList<Integer> binary2(int[] arr, int left, int right, int value) {

        System.out.println("被调用");
        int mid = (left + right) / 2;
//        int mid = (value- arr[left]) / (arr[right] - arr[left]) * (right - left) + left;
        if (mid < left || mid > right)
            return null;
        int midVal = arr[mid];
        if (value > midVal)
            return binary2(arr, mid + 1, right, value);
        if (value < midVal)
            return binary2(arr, left, mid - 1, value);
        ArrayList<Integer> als = new ArrayList<>();
        als.add(mid);
        int midT = mid - 1;
        while (midT >= left && arr[midT] == arr[mid])
            als.add(midT--);
        midT = mid + 1;
        while (midT <= right && arr[midT] == arr[mid])
            als.add(midT++);
        return als;
    }


    public static ArrayList<Integer> binary(int[] arr, int left, int right, int value) {

        System.out.println("被调用");
//        int mid = (left + right) / 2;
        int mid = (value- arr[left]) / (arr[right] - arr[left]) * (right - left) + left;
        if (mid < left || mid > right)
            return null;
        if (arr[mid] == value) {
            ArrayList<Integer> als = new ArrayList<>();
            als.add(mid);
            int midT = mid - 1;
            while (midT >= left && arr[midT] == arr[mid])
                als.add(midT--);
            midT = mid + 1;
            while (midT <= right && arr[midT] == arr[mid])
                als.add(midT++);
            return als;
        }
        if (left == right)
            return null;
        if (arr[mid] > value)
            return binary(arr, left, mid, value);
        return binary(arr, mid + 1, right, value);

    }
}
