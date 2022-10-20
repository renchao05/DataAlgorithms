package com.renchao.recursion;

/**
 * 迷宫问题
 * 0，路
 * 1，墙
 * 3，走过，路不通
 * 8，走通路线
 */
public class MiGong {
    public static void main(String[] args) {
        // 创建地图
        int[][] map = createMap();
        // 走迷宫
        boolean b = way(map, 1, 1);
        System.out.println(b ? "找到路径。。。。" : "没有找到！！");

        // 显示结果
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public static boolean way(int[][] map, int row, int col) {
        if (map[7][5] == 8)
            return true;
        if (map[row][col] == 0) {
            map[row][col] = 8;
            if (way(map, row + 1, col)) // 向下
                return true;
            if (way(map, row, col + 1)) // 向右
                return true;
            if (way(map, row - 1, col)) // 向上
                return true;
            if (way(map, row, col - 1)) // 向左
                return true;
            map[row][col] = 3;
        }
        return false;
    }

    // 创建地图
    public static int[][] createMap() {
        // 创建地图
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[5][i] = 1;
            map[7][i] = 1;
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[7][5] = 0;
        //上面是创建地图
        map[2][2] = 1;
        map[5][1] = 0;
//        map[6][2] = 1; // 堵住出口
        return map;
    }
}
