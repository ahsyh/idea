package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Case077 {
    public static void test(String[] args) {
        int i = 0;
        long startTime = System.currentTimeMillis();
        testOne(3, 2, ++i);
        testOne(4, 3, ++i);
        testOne(10, 3, ++i);
        Log.e("Time usage: " + (System.currentTimeMillis() - startTime));
    }

    private static Solution s = new Solution();

    private static void testOne(int nums, int k, int i) {
        List<List<Integer>> r = s.combine(nums, k);
        Log.e("case" + i, ", result: " + r.size() + ", " + r);
    }

    private static class Solution {
        private List<List<Integer>> r = new ArrayList<>();
        private List<Integer> path = new ArrayList<>();
        private int k;

        public List<List<Integer>> combine(int n, int k) {
            int[] nums = getArray(n);
            this.k = k;
            this.r.clear();
            this.path.clear();

            if (n >= k) {
                t(nums, 0);
            }

            return r;
        }

        private int[] getArray(int n) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }
            return nums;
        }

        private void t(int[] nums, int start) {
            if (path.size() == k) {
                r.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                t(nums, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
