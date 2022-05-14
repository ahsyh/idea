package com.yihui.experimental.leetcode.set90;

import com.yihui.experimental.util.Log;

import java.util.Stack;

public class Case921 {
    public static void test(String[] args) {
        int n = 1;
        testOne("())(", n++);
    }

    private static Solution s = new Solution();

    private static void testOne(String str, int n) {
        int r = s.minAddToMakeValid(str);
        Log.e("case" + n, str + " need insert " + r);
    }

    private static class Solution {
        int minAddToMakeValid(String s) {
            int missingRight = 0;
            int missingLeft = 0;
            Stack<Character> st = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    missingRight++;
                } else if (c == ')') {
                    missingRight--;
                    if (missingRight < 0) {
                        missingLeft++;
                        missingRight = 0;
                    }
                } else {

                }
            }

            return missingRight + missingLeft;
        }

    }
}

