package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Case207c {
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
            int[] to = new int[numCourses];
            List<Integer>[] from = new List[numCourses];

            for (int[] e : prerequisites) {
                int first = e[0];
                int second = e[1];

                to[second]++;

                List<Integer> list = from[first];
                if (list == null) {
                    list = from[first] = new ArrayList<>();
                }
                list.add(second);
            }

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (to[i] == 0) {
                    q.add(i);
                }
            }

            List<Integer> result = new ArrayList<>();

            while (!q.isEmpty()) {
                int n = q.poll();

                result.add(n);
                List<Integer> list = from[n];
                if (list == null) {
                    continue;
                }

                for (int i : list) {
                    to[i]--;
                    if (to[i] == 0) {
                        q.add(i);
                    }
                }
            }
            return (result.size() >= numCourses);
        }

    }
}
