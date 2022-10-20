package com.renchao.lookup;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(fibonacci(arr, 5));
    }

    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibonacci(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        int mid;
        int[] f = fib();
        while (f[k] < right + 1)
            k++;
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (value < temp[mid]) {
                right = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                left = mid + 1;
                k -= 2;
            } else {
                if (mid >= arr.length-1)
                    return arr.length -1;
                return mid;
            }
        }
        return -1;
    }

}
