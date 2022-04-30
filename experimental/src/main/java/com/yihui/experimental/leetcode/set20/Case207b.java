package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Case207b {
    public static void test(String[] args) {
        testOne(new int[][]{{1,4}, {2,4},{3,1},{3,2}}, 5, "case3");

        testOne(new int[][]{{0,2}, {1,2}, {2,0}}, 3, "case0");
        testOne(new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}},
                20, "case1");
    }

    private static Solution s = new Solution();

    private static void testOne(int[][] pre, int numCourses, String tag) {
        boolean r = s.canFinish(numCourses, pre);
        Log.e(tag, "result is: " + r);
    }

    private static class Solution {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (prerequisites.length == 0) {
                return true;
            }

            Map<Integer, Set<Integer>> hasPre = new HashMap<>();
            Map<Integer, Set<Integer>> hasNext = new HashMap<>();

            for (int[] e : prerequisites) {
                int i = e[0];
                int j = e[1];

                if (i == j) {
                    return false;
                }

                if (!hasPre.keySet().contains(j)) {
                    hasPre.put(j, new HashSet<>());
                }
                hasPre.get(j).add(i);

                if (!hasNext.keySet().contains(i)) {
                    hasNext.put(i, new HashSet<>());
                }
                hasNext.get(i).add(j);
            }

            while (!hasNext.isEmpty()) {
                int next = -1;
                for (Integer i : hasNext.keySet()) {
                    if (!hasPre.keySet().contains(i)) {
                        next = i;
                        break;
                    }
                }
                if (next == -1) {
                    return false;
                }

                for (Integer i : hasNext.get(next)) {
                    hasPre.get(i).remove(next);
                    if (hasPre.get(i).isEmpty()) {
                        hasPre.remove(i);
                    }
                }
                hasNext.remove(next);
            }


            return true;
        }

    }
}