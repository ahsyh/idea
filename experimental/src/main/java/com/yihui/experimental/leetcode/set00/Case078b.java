package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Case078b {
    public static void test(String[] args) {
        int i = 0;
        testOne(new int[]{1,2,3}, ++i);
        testOne(new int[]{1,2,3,4}, ++i);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int i) {
        List<List<Integer>> r = s.subsets(nums);
        Log.e("case" + i, ", result: " + r.size() + ", " + r);
    }

    private static class Solution {

        private void t(List<List<Integer>> r, List<Integer> path, int[] nums, int start) {
            r.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                t(r, path, nums, i + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> r = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            t(r, path, nums, 0);
            return r;
        }

    }
}
