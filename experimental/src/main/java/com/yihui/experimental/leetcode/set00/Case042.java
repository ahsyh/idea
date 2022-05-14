package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;

public class Case042 {
    private static final String TAG = "042";
    private Case042() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.trap(new int[]{1,2,3})==0, "1");
        LeetUtil.assertTrue(s.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})==6, "2");
        LeetUtil.assertTrue(s.trap(new int[]{2,0,2})==2, "3");
    }

    static
    class Solution {
        private int trap(int[] h, int left, int right) {
            if (left >= right) {
                return 0;
            }

            if (h[left] >= h[right]) {
                int r = 0;
                for (int j = right - 1; j > left; j--) {
                    if (h[j] <= h[right]) {
                        r += h[right] - h[j];
                    } else {
                        return r + trap(h, left, j);
                    }
                }
                return r;
            } else {
                int r = 0;
                for (int j = left + 1; j < right; j++) {
                    if ( h[j] <= h[left]) {
                        r += h[left] - h[j];
                    } else {
                        return r + trap(h, j, right);
                    }
                }
                return r;
            }
        }

        public int trap(int[] height) {
            return trap(height, 0, height.length - 1);
        }
    }
}
