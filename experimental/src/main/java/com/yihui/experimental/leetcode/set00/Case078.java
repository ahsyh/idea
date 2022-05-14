package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Case078 {
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
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> r = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            t(r, nums, path, 0);
            return r;
        }

        private void t(List<List<Integer>> r, int[] nums, List<Integer> path, int index) {
            if (index >= nums.length) {
                r.add(new ArrayList<>(path));
                return;
            }

            t(r, nums, path, index + 1);
            path.add(nums[index]);
            t(r, nums, path, index + 1);
            path.remove(path.size() - 1);
        }

    }
}
