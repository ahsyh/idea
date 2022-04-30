package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

public class Case198 {
    public static void test(String[] args) {
        testOne(new int[]{1,2,3,1});
    }

    private Case198() {
    }

    private static Solution s = new Solution();

    private static void testOne(int[] n) {
        int r = s.rob(n);
        Log.e("", "result is: " + r);
    }

    private static class Solution {
        public int rob(int[] nums) {
            int[][] dp = new int[nums.length][2];

            dp[0][0] = 0;
            dp[0][1] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = dp[i-1][0] + nums[i];
            }
            return Math.max(dp[nums.length-1][0], dp[nums.length-1][1]);
        }
    }
}
