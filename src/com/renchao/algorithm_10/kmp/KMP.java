package com.renchao.algorithm_10.kmp;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABABEABDABABCDEff";
        s2 = "ABCDABA ABCDAB ABCDABCDABDE";
        s2 = "ABCDABD";
//        System.out.println(kmp(s1, s2));
        System.out.println(Arrays.toString(getNext2(s2)));
    }

    /**
     * kmp算法
     *
     * @param s 主字符串
     * @param p 模式串
     * @return
     */
    public static int kmp(String s, String p) {
        byte[] sb = s.getBytes();
        byte[] pb = p.getBytes();
        int[] next = getNext(p);
        int sLen = sb.length;
        int pLen = pb.length;
        int j = 0;
        int i = 0;
        while (j < sLen && i < pLen) {
            if (i == -1 || pb[i] == sb[j]) {
                j++;
                i++;
            } else {
                i = next[i];
            }
        }
        return i == pLen ? j - i : -1;
    }

    /**
     * 获取 Next 数组
     * 相对原始 next 被整体后移的获取方式，next[0]是-1
     * @param str 模式串
     * @return
     */
    public static int[] getNext(String str) {
        byte[] b = str.getBytes();
        int[] next = new int[b.length];
        int i = -1; // 前缀子串指针，也代表与后缀匹配了的字符个数
        int j = 0;  // 后缀子串指针
        next[0] = -1;
        // 遍历后缀子串的每个字符
        while (j < b.length - 1) {
            // 当前缀子串指针在最左侧(-1)，或者前缀和后缀的当前字符相同时，两个指针同时向前推进，进行比较。
            if (i == -1 || b[i] == b[j]) {
                // 对比到当前位置有多少给相同字符，把个数填入next数组下一个位置
                next[++j] = ++i;
            } else {
                // 在这里 后缀子串可以看成是主字符串，前缀子串可以看成是模式串。
                // 当失配的时候，也是通过已经生成的next数组值确定前缀子串指针的指向
                // 失配时，最终是先i=0,再i=-1,在-1的时候，j再向前推进。
                // 在第一个字符串没有匹配的情况下，i总是被置为-1后，才能让j向前推进。
                i = next[i];
            }
        }
        return next;
    }


    /**
     * 原始 next 数组获取方法，更容易理解一些
     * @param str
     * @return
     */
    public static int[] getNext2(String str) {
        byte[] b = str.getBytes();
        int[] next = new int[b.length];
        int i = 0; // 前缀子串指针，也代表与后缀匹配了的字符个数
        int j = 1;  // 后缀子串指针
        next[0] = 0;
        while (j < b.length - 1) {
            if (b[i] == b[j]) {
                // 字符相同时，双指针同时后移，并且向next记录当前位置前后缀相同字符的个数。
                next[j++] = ++i;
            } else if (i == 0) {
                // 字符不同，但前缀子串指针在第一个字符时，只让后缀子串指针后移
                j++;
            } else {
                // 失配时，前缀子串指针跳转的位置，这里是递推的。
                // 比如：通过这个字符串测试ABCDABA ABCDAB ABCDABCDABDE
                i = next[i - 1];
            }
        }
        return next;
    }
}
