package com.renchao.Dijkstra;

import java.util.Arrays;

public class VisitedVertex {
    private final int[] alreadyArr;  //已经访问过的节点
    private final int[] preVisited;  //记录各个顶点的前驱顶点
    private final int[] dis;  //出发顶点到其他顶点的距离

    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];

        Arrays.fill(preVisited,-1);
        Arrays.fill(dis, 999);
        this.alreadyArr[index] = 1;
        this.dis[index] = 0;
    }


    /**
     * 判断index顶点是否被访问过
     * @param index 顶点
     * @return 访问过返回true,否则返回false
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index 顶点
     * @param len 距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱为index节点
     * @param i 顶点
     * @param pre 前驱节点
     */
    public void updatePre(int i, int pre) {
        preVisited[i] = pre;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index 顶点
     * @return 返回出发顶点到index顶点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    public int getPre(int index) {
        return preVisited[index];
    }

    /**
     * 获取下一个最短距离的顶点作为出发点
     * @return 返回获取的顶点
     */
    public int getNext() {
        int min = 999, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) { // 没有被访问过的顶点，而且到出发顶点最近的
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }
}
