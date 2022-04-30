package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

import java.util.Arrays;

public class Case189 {
    public static void test(String[] args) {
        testOne(new int[]{1,2,3,4,5,6,7,8,9,10}, 3);
    }

    private Case189() {
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int k) {
        s.rotate(nums, k);
        Log.e("","result is: " + Arrays.toString(nums));
    }

    private static class Solution {
        private void rotateOne(int[] nums, int start, int i, int segment) {
            if (i > segment || segment >= nums.length) {
                return;
            }

            int curr = i + start;
            int val = nums[curr];
            while ((curr + segment) < nums.length) {
                int next = curr + segment;
                nums[curr] = nums[next];
                curr = next;
            }
            nums[curr] = val;
        }

        public void rotate(int[] nums, int k) {
            for (int i = 0; i < k; i++) {
                rotateOne(nums, 0, i, k);
            }
       }
    }
}
