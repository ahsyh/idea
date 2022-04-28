package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Case179 {
    private static final String TAG = "179";

    private Case179() {
    }

    private static Solution s = new Solution();

    public static void test() {
        testOne(new int[]{0, 0});
        testOne(new int[]{10, 2});
        testOne(new int[]{3,30,34,5,9});
        testOne(new int[]{3,30,34,5,9,301});
    }

    private static void testOne(int[] nums) {
        String r = s.largestNumber(nums);
        Log.e("", "result is: " + r);
    }

    private static class Solution {
        private static class StrComp implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return -1 * o1.compareTo(o2);
                }

                if (o1.length() < o2.length() && o2.startsWith(o1)) {
                    return compare(o1, o2.substring(o1.length()));
                } else if (o1.length() > o2.length() && o1.startsWith(o2)) {
                    return compare(o1.substring(o2.length()), o2);
                } else {
                    return -1 * o1.compareTo(o2);
                }
            }
        }

        public String largestNumber(int[] nums) {
            List<String> list = Arrays.stream(nums)
                    .boxed()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            StrComp c = new StrComp();
            list.sort(c);
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str);
            }

            String result = sb.toString();
            int i = 0;
            for (; i < result.length(); i++) {
                if (result.charAt(i) != '0') {
                    break;
                }
            }
            if (i >= result.length() - 1) {
                i = result.length() - 1;
            }

            return result.substring(i);
        }
    }
}
