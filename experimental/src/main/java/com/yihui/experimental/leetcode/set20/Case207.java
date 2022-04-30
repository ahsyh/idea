package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Case207 {
    public static void test(String[] args) {
        testOne(new int[][]{{0,2}, {1,2}, {2,0}}, 3, "case0");
        testOne(new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}},
                20, "case1");
    }

    private Case207() {
    }

    private static Solution s = new Solution();

    private static void testOne(int[][] pre, int numCourses, String tag) {
        boolean r = s.canFinish(numCourses, pre);
        Log.e(tag, "result is: " + r);
    }

    private static class Solution {
        private static class Require {
            public int r;
            public int c;

            public Require(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (prerequisites.length == 0) {
                return true;
            }

            List<Require> pre = new LinkedList<>();
            for (int[] p : prerequisites) {
                if (p[0] != p[1]) {
                    pre.add(new Require(p[0], p[1]));
                } else {
                    return false;
                }
            }

            List<Integer> learnSeq = new ArrayList<>();
            Set<Integer> used = new HashSet<>();

            while (learnSeq.size() < numCourses) {
                int next = findAvailable(pre, numCourses, used);

                if (next < 0) {
                    return false;
                }

                learnSeq.add(next);
                used.add(next);

                removeCourse(pre, next);
                if (pre.isEmpty()) {
                    return true;
                }
            }

            return true;
        }

        private void removeCourse(List<Require> pre, int course) {
            pre.removeIf(r -> r.r == course);
        }

        private int findAvailable(List<Require> pre, int numCourses, Set<Integer> used) {
            Set<Integer> hasCondition = new HashSet<>();

            for (Require r : pre) {
                hasCondition.add(r.c);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!hasCondition.contains(i) && !used.contains(i)) {
                    return i;
                }
            }

            return -1;
        }
    }
}

