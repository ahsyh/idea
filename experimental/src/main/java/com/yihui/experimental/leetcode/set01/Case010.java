package com.yihui.experimental.leetcode.set01;

import com.yihui.experimental.util.LeetUtil;

import java.util.ArrayList;

public class Case010 {
    private static final String TAG = "010";
    private Case010() {
    }
    private static Solution s = new Solution();

    private static class Solution {
        private static class PatternEle {
            public char ch;
            public boolean withStar;

            public PatternEle(char ch, boolean withStar) {
                this.ch = ch;
                this.withStar = withStar;
            }
        }

        private boolean isMatch(String s, int indexS, ArrayList<PatternEle> p, int indexP) {
            if (indexS >= s.length() && indexP >= p.size()) {
                return true;
            } else if (indexP >= p.size()) {
                return false;
            }

            if (!p.get(indexP).withStar) {
                if (indexS >= s.length() || !isCharMatch(s.charAt(indexS), p.get(indexP).ch)) {
                    return false;
                }
                return isMatch(s, indexS+1, p, indexP+1);
            } else {
                if (indexS < s.length() && !isCharMatch(s.charAt(indexS), p.get(indexP).ch)) {
                    return isMatch(s, indexS, p, indexP+1);
                } else {
                    return isMatch(s, indexS, p, indexP+1)
                        || isMatch(s, indexS+1, p, indexP+1)
                        || (indexS < s.length() && isMatch(s, indexS+1, p, indexP));
                }
            }
        }

        private static boolean isCharMatch(char c, char p) {
            if (p == '.' || c == p) {
                return true;
            } else {
                return false;
            }
        }

        private static void convertPattern(String pStr, ArrayList<PatternEle> p) {
            for (int i = 0; i < pStr.length(); i++) {
                if ((i+1) < pStr.length() && pStr.charAt(i+1) == '*') {
                    PatternEle n = (new PatternEle(pStr.charAt(i), true));
                    boolean needZip = false;
                    if (!p.isEmpty()) {
                        PatternEle t = p.get(p.size()-1);
                        if (n.ch == t.ch && n.withStar && t.withStar) {
                            needZip = true;
                        }
                    }
                    if (needZip) {
                        p.get(p.size()-1).withStar = true;
                    } else {
                        p.add(n);
                    }
                    i++;
                } else {
                    p.add(new PatternEle(pStr.charAt(i), false));
                }
            }
        }

        public boolean isMatch(String s, String p) {
            ArrayList<PatternEle> pA = new ArrayList<>();
            convertPattern(p, pA);
            return isMatch(s, 0, pA, 0);
        }
    }

    public static void test() {
        LeetUtil.assertTrue(!s.isMatch("aa", "a"), "aa - a");
        LeetUtil.assertTrue(s.isMatch("aa", "a*"), "aa - a*");
        LeetUtil.assertTrue(s.isMatch("ab", ".*"), "ab - .*");
        LeetUtil.assertTrue(s.isMatch("a", "ab*"), "a - ab*");
        LeetUtil.assertTrue(!s.isMatch("ab", ".*c"), "ab - .*c");
        LeetUtil.assertTrue(s.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"), "zip");
        LeetUtil.assertTrue(!s.isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"), "zip2");
    }

}
