package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Case165 {
    private static final String TAG = "165";
    private Case165() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.compareVersion("1.0", "1.0.0")==0, "1");
    }

    static class Solution {
        private int strToInt(String str) {
            if (str.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(str);
            }
        }
        private List<Integer> strToList(String v) {
            String[] l = v.split("\\.");
            return Stream.of(l)
                    .map(this::strToInt)
                    .collect(Collectors.toList());
        }

        private void appendList(List<Integer> list, int num) {
            for (int i = 0; i < num; i++) {
                list.add(0);
            }
        }

        private int compareVersion(List<Integer> list1, List<Integer> list2) {
            for (int i=0; i<list1.size(); i++) {
                if (list1.get(i) > list2.get(i)) {
                    return 1;
                }
                if (list1.get(i) < list2.get(i)) {
                    return -1;
                }
            }
            return 0;
        }
        public int compareVersion(String version1, String version2) {
            List<Integer> list1 = strToList(version1);
            List<Integer> list2 = strToList(version2);

            if (list1.size() > list2.size()) {
                appendList(list2, list1.size() - list2.size());
            } else if (list1.size() < list2.size()) {
                appendList(list1, list2.size() - list1.size());
            }

            return compareVersion(list1, list2);
        }
    }
}
