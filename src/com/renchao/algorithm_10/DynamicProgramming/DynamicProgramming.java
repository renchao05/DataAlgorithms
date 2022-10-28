package com.renchao.algorithm_10.DynamicProgramming;

import java.util.Arrays;

/**
 * 动态规划 背包问题
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        int[] bv = {0, 1500, 3000, 2000};    // 物品价值，0表示没有商品，为了商品标号从1开始
        int[] bw = {0, 1, 4, 3}; // 物品重量。0表示没有商品，也是为了与table表对齐
        int volume = 4;   // 背包容量
        knapsack(bv, bw, volume);
    }

    /**
     * 动态规划 背包问题
     * @param bv 物品价值
     * @param bw 物品重量
     * @param volume 背包容量
     */
    private static void knapsack(int[] bv, int[] bw, int volume) {
        int n = bv.length;   // 物品数量
        // table[i][w]记录前i个物品能够装入容量为w的背包中的最大价值。
        int[][] table = new int[n][volume + 1];
        // 记录物品放入背包的情况。
        int[][] record = new int[n][volume + 1];

        // 循环每个商品，从1号商品开始，0表示没有商品。
        for (int i = 1; i < n; i++) {
            // 循环每个容量，这里直接使用w表示容量
            for (int w = 1; w < table[i].length; w++) {
                if (w >= bw[i]) {    // 容量w大于第i号商品的重量时
                    // bv[i]当前商品的价值
                    // table[i-1][w-bw[i]]装完当前商品，剩余容量在上一个商品时，可以装下的最大价值，w-bw[i]表示剩余容量
                    // table[i-1][w]上一个商品在当前容量下的最大价值
                    if (bv[i] + table[i - 1][w - bw[i]] > table[i - 1][w]) {
                        table[i][w] = bv[i] + table[i - 1][w - bw[i]];
                        record[i][w] = 1;// 记录商品放入
                    } else {
                        table[i][w] = table[i - 1][w];
                    }
                } else {
                    table[i][w] = table[i - 1][w];
                }
            }
        }
        System.out.println("======商品价值表======");
        for (int[] arr : table) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("======商品放入情况======");
        for (int[] arr : record) {
            System.out.println(Arrays.toString(arr));
        }
        // 在最大化情况下，背包可放的物品
        int w = volume; // 可放入的最大容量
        // 从最后一个商品开始循环
        for (int i = n - 1; i > 0 && w > 0; i--) {
            if (record[i][w] == 1) {
                System.out.println(i + "放入背包。");
                w = w - bw[i];  // 减去该商品重量
            }
        }
    }
}
