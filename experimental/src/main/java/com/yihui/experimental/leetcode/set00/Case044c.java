package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;

public class Case044c {
    private static final String TAG = "044c";
    private Case044c() {
    }
    private static Solution s = new Solution();

    public static void test() {
        int i = 0;
        LeetUtil.assertTrue(!s.isMatch("a", "*?*a"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("c", "*?*"), "case " + (i++));
        LeetUtil.assertTrue(!s.isMatch("acdcb", "a*c?b"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab", "*aabb***aa**a******aa*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aa", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aaaaaa", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("ab", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("abcd", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(!s.isMatch("babcd", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("", "*******"), "case " + (i++));
        LeetUtil.assertTrue(!s.isMatch("", "***?****"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba"
                ,"*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"), "case " + (i++));
    }

    static class Solution {
        public boolean isMatch(String s, String p) {
            int n = s.length();
            int m = p.length();

            if (m == 0)
                return n ==0;

            boolean[][] table = new boolean[n+1][m+1];
            table[0][0] = true;

            for (int j=1; j<=m; j++) {
                if (p.charAt(j-1) == '*')
                    table[0][j] = table[0][j-1];
            }

            for (int i=1; i<=n; i++) {
                for (int j=1; j<=m; j++) {
                    char first = s.charAt(i-1);
                    char second = p.charAt(j-1);

                    if (first == second || second =='?')
                        table[i][j] = table[i-1][j-1];
                    else if (second == '*') {
                        table[i][j] = table[i-1][j-1] || table[i-1][j] || table[i][j-1];
                    }
                }
            }
            return table[n][m];
        }
    }
}
