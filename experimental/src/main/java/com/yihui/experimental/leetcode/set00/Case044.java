package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;

import java.util.ArrayList;

public class Case044 {
    // misunderstand about requirement
    private static final String TAG = "044";
    private Case044() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.isMatch("aa", "a*a*a*a*a*"), "1");
        LeetUtil.assertTrue(s.isMatch("aab", "a*a*a*a*a*b"), "2");
        LeetUtil.assertTrue(s.isMatch("b", "a*a*a*a*a*b"), "3");
        LeetUtil.assertTrue(!s.isMatch("aaaaa", "a*a*a*a*a*b"), "4");
        LeetUtil.assertTrue(s.isMatch("aaabccd", "a*a*a*a*a*b*c*d*"), "5");
        LeetUtil.assertTrue(s.isMatch("aaabccd", "a*a*a*a*a*bb*c*dd*"), "6");
        LeetUtil.assertTrue(!s.isMatch("aaabccd", "a*a*a*a*a*bb*c*ddd*"), "7");
    }

    static
    class Solution {
        private static class Ele {
            public char ch;
            public boolean withStar;

            public Ele(char c, boolean w) {
                ch = c;
                withStar = w;
            }
        }

        private ArrayList<Ele> convertPattern(String p) {
            final ArrayList<Ele> r = new ArrayList<>();

            for (int i = 0; i < p.length(); i++) {
                if (i < p.length() - 1 && p.charAt(i+1) == '*') {
                    int j = r.size() - 1;
                    if (!(!r.isEmpty() && r.get(j).ch == p.charAt(i) && r.get(j).withStar)) {
                        r.add(new Ele(p.charAt(i), true));
                    }
                    i++;
                } else {
                    r.add(new Ele(p.charAt(i), false));
                }
            }
            return r;
        }

        private boolean isMatch(char a, char p) {
            return (a == p) || (p == '?');
        }

        private boolean isMatch(String s, ArrayList<Ele> p, int sPos, int pPos) {
            {
                if (sPos < s.length() && pPos >= p.size()) {
                    return false;
                } else if (sPos >= s.length() && pPos >= p.size()) {
                    return true;
                }

                if (!p.get(pPos).withStar) {
                    if (sPos < s.length() && isMatch(s.charAt(sPos), p.get(pPos).ch)) {
                        return isMatch(s, p, sPos+1, pPos+1);
                    } else {
                        return false;
                    }
                } else {
                    if (sPos < s.length() && isMatch(s.charAt(sPos), p.get(pPos).ch)) {
                        return isMatch(s, p, sPos+1, pPos+1)
                                || isMatch(s, p, sPos, pPos+1)
                                || isMatch(s, p, sPos+1, pPos);
                    } else {
                        return isMatch(s, p, sPos, pPos+1);
                    }
                }
            }
        }

        public boolean isMatch(String s, String p) {
            return isMatch(s, convertPattern(p), 0, 0);
        }
    }
}
