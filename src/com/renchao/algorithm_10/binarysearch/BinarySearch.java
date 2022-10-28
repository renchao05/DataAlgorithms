package com.renchao.algorithm_10.binarysearch;

/**
 * 二分查找，非递归
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(search(arr, 12));

    }

    public static int search(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (true) {
            if (left > right)
                return -1;
            mid = (right + left) / 2;
            if (value < arr[mid]) {
                right = mid - 1;
            } else if (value > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
    }
}
