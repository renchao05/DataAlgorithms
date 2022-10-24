package com.renchao.sort;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {
//        int[] arr = {5, 2, 9, 5, 7, 10, -3};
//        int[] arr = {-3, 2, 5, 0, 10, -7, 9, 8, -35, 25, 20};
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 2};
//        fastSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(ints1));

        long l1;
        long l2;
        int[] ints = new int[80000000];
        int[] temp = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 800000);
        }

        //堆排序 8万 -> 12   80万=》140  800万=》2145  8000万=》30030
        l1 = System.currentTimeMillis();
        heapSort(ints);
        l2 = System.currentTimeMillis();
        System.out.println("堆排序耗时:" + (l2 - l1));

        //基数排序 8万 -> 31   80万=》83  800万=》593  8000万=》5572
//        l1 = System.currentTimeMillis();
//        radixSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("基数排序耗时:" + (l2 - l1));

        //归并排序 8万 -> 15   80万=》130  800万=》1431  8000万=》13651
//        l1 = System.currentTimeMillis();
//        mergeSort(ints,0,ints.length-1,temp);
//        l2 = System.currentTimeMillis();
//        System.out.println("归并排序耗时:" + (l2 - l1));

        //快速排序 8万 -> 30   80万=》135  800万=》1352  8000万=》13212
//        l1 = System.currentTimeMillis();
//        fastSort(ints,0,ints.length-1);
//        l2 = System.currentTimeMillis();
//        System.out.println("快速排序耗时:" + (l2 - l1));


        //希尔排序 8万 -> 24   80万=》186  800万=》2511
//        l1 = System.currentTimeMillis();
//        shellSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("希尔排序耗时:" + (l2 - l1));


        //插入排序 8万 -> 683
//        l1 = System.currentTimeMillis();
//        insertSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("插入排序耗时:" + (l2 - l1));

        //选择排序 8万 -> 5021
//        l1 = System.currentTimeMillis();
//        choiceSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("选择排序耗时:" + (l2 - l1));

//        //冒泡排序 8万 -> 11797
//        l1 = System.currentTimeMillis();
//        bubblingSort(ints);
//        l2 = System.currentTimeMillis();
//        System.out.println("冒泡排序耗时:" + (l2 - l1));
    }


    /**
     * heapSort
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int temp;
        // 整体调整所有顶堆，从最后一个非叶子节点开始调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 把最大数与数组最后一个数进行交换。后面每次交换后，只要调整最上面这个堆就可以了。
        // 注意，length - 1 ,因为第一个循环最后一个数已经调整了
        for (int i = arr.length - 1; i >=0 ; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap(arr, 0, i);
        }
    }
    /**
     * 针对单个节点进行堆调整
     * @param arr 目标数组
     * @param i 需要调整的节点。k是右子节点下标，左右节点比较大小后，会指向大的节点。
     * @param len 需要调整的数组长度
     */
    public static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < len; k = i * 2 + 1) {
            // 两个子节点进行比较，哪个大，k就指向哪个节点
            if (k + 1 < len && arr[k] < arr[k + 1])
                k++;
            //与大的子节点进行比较，如果子节点大，则进行交换。下面的节点受到影响了，还要循环处理
            if (arr[k] > temp) {
                arr[i] = arr[k];// 子节点替换当前节点
                i = k;//跳到下一个节点
                arr[k] = temp; // 当前节点到子节点位置，因为i==k,所以temp不用修改。
            } else {
                break;
            }
        }
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] pointer = new int[10];
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
                bucket[vl][pointer[vl]++] = j;
            }
            h = 0;
            for (int i = 0; i < 10; i++) {
                if (pointer[i] != 0) {
                    for (int j = 0; j < pointer[i]; j++) {
                        arr[h++] = bucket[i][j];
                    }
                }
                pointer[i] = 0;
            }
        }
    }

    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左分解
            mergeSort(arr, left, mid, temp);
            //向右分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 合并
     * @param arr   原始数组
     * @param left  左边序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int m = mid + 1;
        int t = 0;

        //逐个比较两组元素大小，然后按顺序将数据拷贝到temp
        while (l <= mid && m <= right) {
            if (arr[l] < arr[m]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[m++];
            }
        }

        //将另外一组剩余元素拷贝到temp
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (m <= right) {
            temp[t++] = arr[m++];
        }

        //将temp元素重新拷贝回arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

    }

    //快速排序
    public static void fastSort(int[] arr, int left, int right) {
        if (left > right)
            return;
        int l = left;
        int r = right;
        int middle = arr[(l + r) / 2];
        int temp;
        while (l < r) {
            while (arr[l] < middle) {
                l++;
            }
            while (arr[r] > middle) {
                r--;
            }
            if (l < r) {
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                // 防止重复值出现死循环
                if (arr[l] == middle)
                    r--;
                if (arr[r] == middle)
                    l++;
            }
        }

        // 必须，不然递归没法结束，会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        fastSort(arr, left, r);
        fastSort(arr, l, right);
    }

    //希尔排序（插入排序改进版），j是插入点，k是增量步长
    public static void shellSort(int[] ints) {
        int temp;
        int j;
        // 增量循环，k是增量
        for (int k = ints.length / 2; k >= 1; k /= 2) {
            // 下面是普通插入排序算法，不通的是增量不同，普通插入增量是1
            // 增量是1以上的时候，每组是交替排序
            for (int i = k; i < ints.length; i++) {
                temp = ints[i];
                j = i;
                while (j >= k && ints[j - k] > temp) {
                    ints[j] = ints[j - k];
                    j -= k;
                }
                ints[j] = temp;
            }
        }
    }

    //插入排序,j表示插入点
    public static void insertSort(int[] ints) {
        int temp;
        int j;
        for (int i = 1; i < ints.length; i++) {
            temp = ints[i];
            j = i - 1;
            // 大于temp的值进行后移
            while (j >= 0 && ints[j] > temp) {
                ints[j + 1] = ints[j];
                j--;
            }
            // 插入
            ints[j + 1] = temp;
        }
    }

    /**
     * 选择排序
     *
     * @param ints
     */
    public static void choiceSort(int[] ints) {
        int temp;
        int index;
        // 最后一次不需要再比较，所以是 ints.length - 1
        for (int i = 0; i < ints.length - 1; i++) {
            index = i;  // 记录最小值索引
            for (int j = index + 1; j < ints.length; j++) {
                if (ints[index] > ints[j])
                    index = j;
            }
            // 数据交换
            if (index != i) {
                temp = ints[i];
                ints[i] = ints[index];
                ints[index] = temp;
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param ints
     */
    public static void bubblingSort(int[] ints) {
        int temp;
        boolean index = false;  // 是否进行过数据交换，如果没有，则数据是有序的，可以结束排序
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    index = true;   // 有数据交换
                }
            }
            if (!index) // 没有数据交换，就结束循环
                break;
            index = false;  // 重置
        }
    }
}
