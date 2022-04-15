package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

public final class Case032 {
    private static final String TAG = "032";
    private Case032() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.longestValidParentheses("")==0, "empty");
        LeetUtil.assertTrue(s.longestValidParentheses("(()()")==4, "(()()");
        LeetUtil.assertTrue(s.longestValidParentheses("(()")==2, "(()");
        LeetUtil.assertTrue(s.longestValidParentheses(")()())")==4, ")()())");
    }

    static class Solution {
        public int longestValidParentheses(String s) {
            if (s.isEmpty()) {
                return 0;
            }

            int r = 0;
            int[] d = new int[s.length()];
            d[s.length()-1] = 0;
            for (int i = s.length()-2; i >= 0; i-- ) {
                int t = d[i+1];

                if ((i+t+1) < s.length() && s.charAt(i)=='(' && s.charAt(i+t+1)==')') {
                    d[i] = t+2;
                    if ((i+t+2) < s.length()) {
                        d[i] += d[i+t+2];
                    }
                } else {
                    d[i] = 0;
                }
                if (r < d[i]) {
                    r = d[i];
                }
            }

            return r;
        }
    }

}
