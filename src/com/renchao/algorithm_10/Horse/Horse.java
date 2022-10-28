package com.renchao.Horse;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Horse {
    private static final int X = 16; //棋盘宽度
    private static final int Y = 16; //棋盘高度
    private static final int[][] check = new int[X][Y];   //棋盘
    private static int step = 0;    //步数

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        horse(new Point(5, 1));
        long l2 = System.currentTimeMillis();

        //输出耗时
        System.out.println("耗时：" + (l2 - l1));
        //输出结果
        for (int[] ints : check) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 马踏棋盘主方法
     *
     * @param p 当前棋子坐标
     */
    public static void horse(Point p) {
        step++;
        check[p.x][p.y] = step;
        //获取下一步可以走的位置，//getNext，getSortNext(最优的)，getSortNext2
        ArrayList<Point> next = getSortNext(p);
        //对可以走的位置进行循环递归回溯
        for (Point point : next) {
            horse(point);
        }
        //判断步数是否走完，如果走完了就退出，否则进行回溯
        if (step >= X * Y)
            return;
        //回溯
        step--;
        check[p.x][p.y] = 0;
    }

    /**
     * 获取下一步可以走的所有位置
     *
     * @param p 当前坐标
     * @return 返回所有位置坐标
     */
    public static ArrayList<Point> getNext(Point p) {
        ArrayList<Point> ps = new ArrayList<>();
        //1、下一步棋子要在棋盘范围内。2、下一步棋子也必须没有走过
        if (p.x - 2 >= 0 && p.y - 1 >= 0 && check[p.x - 2][p.y - 1] == 0)
            ps.add(new Point(p.x - 2, p.y - 1));
        if (p.x - 1 >= 0 && p.y - 2 >= 0 && check[p.x - 1][p.y - 2] == 0)
            ps.add(new Point(p.x - 1, p.y - 2));
        if (p.x + 1 < X && p.y - 2 >= 0 && check[p.x + 1][p.y - 2] == 0)
            ps.add(new Point(p.x + 1, p.y - 2));
        if (p.x + 2 < X && p.y - 1 >= 0 && check[p.x + 2][p.y - 1] == 0)
            ps.add(new Point(p.x + 2, p.y - 1));
        if (p.x + 2 < X && p.y + 1 < Y && check[p.x + 2][p.y + 1] == 0)
            ps.add(new Point(p.x + 2, p.y + 1));
        if (p.x + 1 < X && p.y + 2 < Y && check[p.x + 1][p.y + 2] == 0)
            ps.add(new Point(p.x + 1, p.y + 2));
        if (p.x - 1 >= 0 && p.y + 2 < Y && check[p.x - 1][p.y + 2] == 0)
            ps.add(new Point(p.x - 1, p.y + 2));
        if (p.x - 2 >= 0 && p.y + 1 < Y && check[p.x - 2][p.y + 1] == 0)
            ps.add(new Point(p.x - 2, p.y + 1));
        return ps;
    }


    /**
     * 返回排序后的坐标，按照下一步的可走位置从小到大进行排序
     *
     * @param point 当前位置坐标
     * @return 返回排序后的坐标
     */
    public static ArrayList<Point> getSortNext(Point point) {
        ArrayList<Point> list = getNext(point);
        ArrayList<PNode> ipt = new ArrayList<>();
        for (Point p : list) {
            ipt.add(new PNode(p, getNext(p).size()));
        }
        Collections.sort(ipt);
        ArrayList<Point> next = new ArrayList<>();
        for (PNode node : ipt) {
            next.add(node.getPoint());
        }
        return next;
    }
}
