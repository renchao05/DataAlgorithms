package com.renchao.sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
//        int[] ints = {5, 2, 9, 5, 7, 10, -3};
//        int[] ints1 = {-3, 2, 5, 0, 10, -7, 9, 8, -35, 25, 20};
        int[] ints1 = {8,9,1,7,2,3,5,4,6,0};
        radixSort(ints1);
        System.out.println(Arrays.toString(ints1));

        long l1;
        long l2;
        int[] ints = new int[80000000];
        int[] temp = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 800000);
        }

        //基数排序
//        l1 = System.currentTimeMillis();
//        radixSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("基数排序耗时:" + (l2 - l1));

        //归并排序
//        l1 = System.currentTimeMillis();
//        mergeSort(ints,0,ints.length-1,temp);
//        l2 = System.currentTimeMillis();
//        System.out.println("归并排序耗时:" + (l2 - l1));

        //快速排序
//        l1 = System.currentTimeMillis();
//        fastSort(ints,0,ints.length-1);
//        l2 = System.currentTimeMillis();
//        System.out.println("快速排序耗时:" + (l2 - l1));


        //希尔排序
        l1 = System.currentTimeMillis();
        shellSort(ints);
        l2 = System.currentTimeMillis();
        System.out.println("希尔排序耗时:" + (l2 - l1));


//        //插入排序
//        l1 = System.currentTimeMillis();
//        insertSort3(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("插入排序耗时:" + (l2 - l1));

        //选择排序
//        l1 = System.currentTimeMillis();
//        choiceSort2(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("选择排序耗时:" + (l2 - l1));

//        //冒泡排序
//        l1 = System.currentTimeMillis();
//        bubblingSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("冒泡排序耗时:" + (l2 - l1));
    }

    public static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] counts = new int[10];
        int h;
        int max = arr[0];
        for (int i : arr) {
            if (i > max)
                max = i;
        }
        int maxLength = (max + "").length();

        for (int k = 0, n = 1; k < maxLength; k++, n *= 10) {
            for (int j : arr) {
                int vl = (j / n) % 10;
                bucket[vl][counts[vl]++] = j;
            }
            h = 0;
            for (int i = 0; i < 10; i++) {
                if (counts[i] != 0) {
                    for (int j = 0; j < counts[i]; j++) {
                        arr[h++] = bucket[i][j];
                    }
                }
                counts[i] = 0;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左分解
            mergeSort(arr, left, mid,temp);
            //向右分解
            mergeSort(arr,mid+1,right,temp);

            merge(arr,left,mid,right,temp);
        }
    }


    /**
     *
     * @param arr   原始数组
     * @param left  左边序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //按顺序将数据拷贝到temp
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        //将剩余元素拷贝到temp
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //将temp元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

    }

    //快速排序
    public static void fastSort(int[] ints, int left, int right) {
        int l = left;
        int r = right;
        int middle = ints[(l + r) / 2];
        int temp;
        while (l < r) {
            while (ints[l] < middle) {
                l++;
            }
            while (ints[r] > middle) {
                r--;
            }
            if (l >= r)
                break;

            temp = ints[l];
            ints[l] = ints[r];
            ints[r] = temp;

            if (ints[l] == middle)
                r--;
            if (ints[r] == middle)
                l++;
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r)
            fastSort(ints, left, r);
        if (right > l)
            fastSort(ints, l, right);

    }

    //希尔排序
    public static void shellSort(int[] ints) {
        int temp;
        int j;
        for (int k = ints.length / 2; k >= 1; k /= 2) {
            for (int i = k; i < ints.length; i++) {
                temp = ints[i];
                j = i - k;
                while (j >= 0 && ints[j] > temp) {
                    ints[j + k] = ints[j];
                    j -= k;
                }
                ints[j + k] = temp;
            }
        }
    }

    //插入排序
    public static void insertSort2(int[] ints) {
        int temp;
        int index;
        for (int i = 1; i < ints.length; i++) {
            index = i;
            temp = ints[index];
            for (int j = i - 1; j >= 0 && ints[j] > temp; j--) {
                ints[j + 1] = ints[j];
                index--;
            }
            ints[index] = temp;
        }
    }

    //插入排序,优化版
    public static void insertSort3(int[] ints) {
        int temp;
        int j;
        for (int i = 1; i < ints.length; i++) {
            temp = ints[i];
            j = i - 1;
            while (j >= 0 && ints[j] > temp) {
                ints[j + 1] = ints[j];
                j--;
            }
            ints[j + 1] = temp;
        }
    }


    //插入排序
    public static void insertSort(int[] ints) {
        int temp;
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (ints[i + 1] < ints[j]) {
                    temp = ints[i + 1];
                    for (int k = i; k >= j; k--) {
                        ints[k + 1] = ints[k];
                    }
                    ints[j] = temp;
                    break;
                }
            }
        }
    }

    public static void choiceSort2(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            int minIndex = i;
            int min = ints[i];
            for (int j = i + 1; j < ints.length; j++) {
                if (min > ints[j]) {
                    min = ints[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ints[minIndex] = ints[i];
                ints[i] = min;
            }
        }
    }

    public static void choiceSort(int[] ints) {
        int temp;
        int index;
        for (int i = 0; i < ints.length - 1; i++) {
            index = i;
            for (int j = index + 1; j < ints.length; j++) {
                if (ints[index] > ints[j])
                    index = j;
            }
            if (index != i) {
                temp = ints[i];
                ints[i] = ints[index];
                ints[index] = temp;
            }
        }
    }

    public static void bubblingSort(int[] ints) {
        int temp;
        boolean index = false;
        for (int i = 0; i < ints.length - 1; i++) {
//            System.out.println("第" + (i + 1) + "次");
            for (int j = 0; j < ints.length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    index = true;
                }
            }
            if (!index)
                break;
            index = false;
        }
    }
}
