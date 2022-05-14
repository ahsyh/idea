package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;

public class Case044a {
    // Performance is poor
    private static final String TAG = "044a";
    private Case044a() {
    }
    private static Solution s = new Solution();

    public static void test() {
        int i = 0;
        LeetUtil.assertTrue(!s.isMatch("acdcb", "a*c?b"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab", "*aabb***aa**a******aa*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba"
        ,"*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aa", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("aaaaaa", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("ab", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(s.isMatch("abcd", "a?*"), "case " + (i++));
        LeetUtil.assertTrue(!s.isMatch("babcd", "a?*"), "case " + (i++));

    }

    static
    class Solution {
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

        private boolean isMatch(String s, String p, int sPos, int pPos) {
            if (sPos < s.length() && pPos >= p.length()) {
                return false;
            } else if (sPos >= s.length() && pPos >= p.length()) {
                return true;
            }

            if (sPos < s.length() && !isMatch(s.charAt(sPos), p.charAt(pPos))) {
                return false;
            }

            if (p.charAt(pPos) == '*') {
                if (sPos < s.length()) {
                    boolean b1 = isMatch(s, p, sPos, pPos + 1);
                    boolean b2 = isMatch(s, p, sPos +1, pPos + 1);
                    boolean b3 = isMatch(s, p, sPos + 1, pPos);
                    return b1 || b2 || b3;
                } else {
                    return isMatch(s, p, sPos, pPos + 1);
                }

            } else {
                return sPos < s.length() && isMatch(s, p, sPos + 1, pPos + 1);
            }
        }

        public boolean isMatch(String s, String p) {
            return isMatch(s, zipPattern(p), 0, 0);
        }
    }
}
