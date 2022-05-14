package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;
import com.yihui.experimental.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Case022 {
    private static final String TAG = "022";
    private Case022() {
    }
    private static Solution s = new Solution();

    public static   void test() {
        LeetUtil.assertSameString("()", s.generateParenthesis(1).get(0), "1");
        List<String> r = s.generateParenthesis(3);
        Log.e(TAG, " " + r.size());
    }

    static class Solution {
        private void a(List<String> list, char[] content, int curr, int usedLeft, int usedRight, int totalLeft) {
            if (curr >= 2 * totalLeft) {
                String s = String.valueOf(content);
//                Log.e(TAG, "get one result: [" + s + "]");
                list.add(s);
                return;
            }

            if (usedLeft < totalLeft) {
                content[curr] = '(';
                a(list, content, curr + 1, usedLeft + 1, usedRight, totalLeft);
            }
            if (usedRight < usedLeft) {
                content[curr] = ')';
                a(list, content, curr + 1, usedLeft, usedRight + 1, totalLeft);
            }
        }

        public List<String> generateParenthesis(int n) {
            char[] content = new char[n * 2];
            List<String> list = new LinkedList<>();
            a(list, content, 0, 0, 0, n);
            return list;
        }
    }
}
