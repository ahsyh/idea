package com.yihui.experimental.leetcode.set60;

import com.yihui.experimental.util.LeetUtil;

import java.util.Stack;

public class Case682 {
    private static final String TAG = "032";
    private Case682() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.calPoints(new String[]{"5","2","C","D","+"})==30, "case1");
    }

    static class Solution {
        public int calPoints(String[] ops) {
            Stack<String> stack = new Stack<>();
            for (String str : ops) {
                switch (str) {
                    case "+":
                        Integer i1 = Integer.parseInt(stack.pop());
                        Integer i2 = Integer.parseInt(stack.pop());
                        stack.push(i2 + "");
                        stack.push(i1 + "");
                        stack.push((i1 + i2) + "");
                        break;
                    case "D":
                        Integer i3 = Integer.parseInt(stack.peek());
                        stack.push((i3 * 2) +"");
                        break;
                    case "C":
                        stack.pop();
                        break;
                    default:
                        Integer i4 = Integer.parseInt(str);
                        stack.push(i4 + "");
                        break;
                }
            }

            int r = 0;
            while (!stack.isEmpty()) {
                r += Integer.parseInt(stack.pop());
            }

            return r;
        }
    }
}
