package com.yihui.lintcode.g;

import static com.yihui.lintcode.util.Utils.assertTrue;

public class Case1212 {
    private Case1212() {
    }

    private static Solution s = new Solution();

    public static void test() {
        int id = 1;
        assertTrue(s.findMaxConsecutiveOnes(new int[]{1}) == 1, "Case"+ id++);
        assertTrue(s.findMaxConsecutiveOnes(new int[]{1,0,0,1,1,1,0,1}) == 3, "Case"+ id++);
    }

    static class Solution {
        /**
         * @param nums: a binary array
         * @return:  the maximum number of consecutive 1s
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            // Write your code here
            boolean in = false;
            int start = -1;
            int max = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    if (in) {
                        int cnt = i - start;
                        max = Math.max(cnt, max);
                    }
                    in = false;
                } else {
                    if (!in) {
                        in = true;
                        start = i;
                    }
                }
            }

            if (nums[nums.length - 1] == 1) {
                int cnt = nums.length - start;
                max = Math.max(cnt, max);
            }

            return max;
        }
    }
}
