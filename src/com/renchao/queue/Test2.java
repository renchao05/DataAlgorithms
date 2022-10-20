package com.renchao.queue;

import com.renchao.Utility;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        QueueTest2 qt = new QueueTest2(4);
        boolean b = true;

        while (b) {
            System.out.print("请选择添加还是取出：\n0添加，1取出,2显示，9退出");
            int i = Utility.readInt();
            switch (i) {
                case 0:
                    System.out.println("请输入要添加的数：");
                    int i1 = Utility.readInt();
                    System.out.println(qt.add(i1) ? "添加成功。" : "添加失败");
                    break;
                case 1:
                    System.out.println(qt.out());
                    break;
                case 2:
                    qt.show();
                    break;
                case 9:
                    b = false;
            }
        }
    }
}
