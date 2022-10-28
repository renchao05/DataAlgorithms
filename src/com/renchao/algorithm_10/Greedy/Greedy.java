package com.renchao.Greedy;

import java.util.*;

/**
 * 贪婪算法，集合覆盖问题
 */
public class Greedy {
    public static void main(String[] args) {
        // 获取电台
        HashMap<String, String[]> ks = getK();
        // 用于收集所有地区，并且去重
        HashSet<String> kk = new HashSet<>();
        // 保存符合要求的电台
        ArrayList<String> selects = new ArrayList<>();
        String maxK = null;   // 记录覆盖地区最多的电台
        int max;
        int num;
        // 收集所有的电台，并且去重
        for (Map.Entry<String,String[]> entry : ks.entrySet()) {
            kk.addAll(Arrays.asList(entry.getValue()));
        }
        while (kk.size() > 0) {
            max = 0;
            for (Map.Entry<String,String[]> k : ks.entrySet()) {
                num = 0;
                //获取当前电台覆盖数
                for (String s : k.getValue()) {
                    if (kk.contains(s))
                        num++;
                }
                // 与最大覆盖数比较
                if (num > max) {
                    max = num;
                    maxK = k.getKey();
                }
            }
            selects.add(maxK);
            // 删除已经覆盖的地区，然后进行下一轮比较
            for (String s : ks.get(maxK)) {
                kk.remove(s);
            }
        }

        // 输出结果
        for (String strings : selects) {
            System.out.println(strings);
        }
    }

    // 获取电台
    public static HashMap<String, String[]> getK() {
        HashMap<String, String[]> ks = new HashMap<>();
        ks.put("k1", new String[]{"北京", "上海", "天津"});
        ks.put("k2", new String[]{"广州", "北京", "深圳"});
        ks.put("k3", new String[]{"成都", "上海", "杭州"});
        ks.put("k4", new String[]{"上海", "天津"});
        ks.put("k5", new String[]{"杭州", "大连"});
        return ks;
    }
}
