package com.yihui.experimental.leetcode.set20;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Case210 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] to = new int[numCourses];
            List<Integer>[] from = new List[numCourses];

            for (int[] e : prerequisites) {
                int first = e[1];
                int second = e[0];

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

            int[] result = new int[numCourses];
            int index = 0;

            while (!q.isEmpty()) {
                int n = q.poll();

                result[index] = n;
                index++;

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
            if (index >= numCourses) {
                return result;
            }
            return new int[0];
        }

    }
}

