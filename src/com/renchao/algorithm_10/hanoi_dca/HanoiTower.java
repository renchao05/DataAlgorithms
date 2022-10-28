package com.renchao.algorithm_10.hanoi_dca;

public class HanoiTower {
    public static void main(String[] args) {
        hanoi(2);
    }

    public static void hanoi(int num) {
        hanoi(num,"A","B","C");
    }

    /**
     * 汉诺塔
     * @param num 层数
     * @param current 当前圆盘所在位置
     * @param transit 中转位置
     * @param target 目标位置
     */
    public static void hanoi(int num,String current,String transit,String target) {
        if (num == 1) {
            // 如果只有一块，直接从当前位置放到目标位置
            System.out.println(current + "->" + target);
        } else if (num >= 2) { // 两块以及两块以上
            // 把当前位置最后一层上面的圆盘都移动到中转位置。
            hanoi(num - 1,current,target,transit);
            // 把最后一层的圆盘移动到目标位置
            System.out.println(current + "->" + target);
            // 把中转位置的圆盘移动到目标位置
            hanoi(num - 1,transit,current,target);
        }
    }
}
