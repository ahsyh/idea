package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

public class Case172 {
    private static final String TAG = "172";

    private Case172() {
    }

    private static Solution s = new Solution();

    public static void test() {
        testOne(0);
        testOne(3);
        testOne(5);
        testOne(10);
        testOne(1000);
    }

    public static void testOne(int n) {
        int r = s.trailingZeroes(n);
        Log.e("", "result of " + n + " is: " + r);
    }

    public static class Solution {
        public int trailingZeroes(int n) {
            int r = 0;
            int factor5 = 5;

            while (factor5 <= n) {
                r += n / factor5;
                factor5 *= 5;
            }

            return r;
        }
    }
}
