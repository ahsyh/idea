package com.yihui.experimental.leetcode00;

import java.util.HashMap;
import java.util.Map;

public class Case001 {
    private static final String TAG = "001";

    private static class Solution {

        public int[] twoSum(int[] nums, int target) {
            int[] r = new int[]{-1, -1};
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }

            for (int i = 0; i < nums.length; i++) {
                int pair = target - nums[i];
                if (map.containsKey(pair) && i != map.get(pair)) {
                    r[0] = i;
                    r[1] = map.get(pair);
                    return r;
                }
            }

            return r;
        }
    }

    public static void test() {

    }
}
