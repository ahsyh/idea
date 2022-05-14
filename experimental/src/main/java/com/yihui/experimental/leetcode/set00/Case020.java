package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Case020 {
    public static void test(String[] args) {
        int i = 1;
        testOne("(){}[]", i++);
        testOne("([)]", i++);
    }

    private static Solution s = new Solution();

    private static void testOne(String str, int i) {
        boolean b = s.isValid(str);
        Log.e("case " + i, str + " check result: " + b);
    }


    private static class Solution {
        private Set<Character> set = new HashSet<>(Arrays.asList('{', '(', '['));

        private boolean isLeft(char c) {
            return set.contains(c);
        }

        private char leftOf(char c) {
            if (c == '}') {
                return '{';
            } else if (c == ')') {
                return '(';
            } else if (c == ']') {
                return '[';
            }
            return '?';
        }

        public boolean isValid(String str) {
            Stack<Character> s = new Stack<>();

            for (char c : str.toCharArray()) {
                if (isLeft(c)) {
                    s.push(c);
                } else {
                    char top = s.peek();
                    if (top == leftOf(c)) {
                        s.pop();
                    } else {
                        return false;
                    }
                }
            }

            return s.isEmpty();
        }

    }
}
