package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

public class Case044c {
    private static final String TAG = "044c";
    private Case044c() {
    }
    private static Solution s = new Solution();

    public static void test() {
        int i = 0;
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
        private boolean[][] d;

        private boolean isMatch(char a, char p) {
            return (a == p) || (p == '?') || (p == '*');
        }

        private String zipPattern(String p) {
            StringBuilder sb = new StringBuilder();
            boolean lastIsStar = false;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    sb.append(p.charAt(i));
                    lastIsStar = false;
                } else {
                    // is * now
                    if (!lastIsStar) {
                        sb.append('*');
                    }
                    lastIsStar = true;
                }
            }
            return sb.toString();
        }

        private void fillInD(boolean[][] d, String s, String p) {
            for (int i = 0; i < s.length(); i++) {
                if (p.charAt(0) == '*') {
                    d[i][0] = true;
                } else {
                    if (i == 0 && isMatch(s.charAt(0), p.charAt(0))) {
                        d[0][0] = true;
                    } else {
                        d[i][0] = false;
                    }
                }
            }

            if (p.length() <= 1) {
                return;
            }

            for (int j = 1; j < p.length(); j++) {
                if (j > 2) {
                    d[0][j] = false;
                } else {
                    if (j == 1 && d[0][0] && (p.charAt(0) == '*' || p.charAt(1) == '*') && isMatch(s.charAt(0), p.charAt(1))) {
                        d[0][j] = true;
                    } else if (j == 2 && d[0][1] && (p.charAt(2) == '*')) {
                        d[0][j] = true;
                    } else {
                        d[0][j] = false;
                    }
                }
            }
            for (int j = 1; j < p.length(); j++) {
                for (int i = 1; i < s.length(); i++) {
                    if (p.charAt(j) == '*') {
                        d[i][j] = d[i-1][j-1] || d[i-1][j] || d[i][j-1];
                    } else if (!isMatch(s.charAt(i), p.charAt(j))) {
                        d[i][j] = false;
                    } else {
                        d[i][j] = d[i-1][j-1];
                    }
                }
            }

        }
        public boolean isMatch(String s, String p) {
            d = new boolean[s.length()][p.length()];
            String zP = zipPattern(p);

            if (p.length() == 0 && s.length() == 0) {
                return true;
            } else if (p.length() == 0 && s.length() > 0) {
                return false;
            } else if (s.length() == 0) {
                return zP.charAt(0) == '*' && zP.length() <= 1;
            }

            fillInD(d, s, zP);
            return d[s.length()-1][zP.length()-1];
        }
    }
}
