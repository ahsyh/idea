package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil.ListNode;

import static com.yihui.experimental.util.LeetUtil.arrayToList;
import static com.yihui.experimental.util.LeetUtil.printList;

public class Case002 {
    private static final String TAG = "002";
    private Case002() {
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode t = new ListNode(0);
            ListNode r = t;
            ListNode p = null;
            int overflow = 0;

            for(;;) {
                if (l1 == null && l2 == null) {
                    if (overflow != 0) {
                        t.val = overflow;
                    }
                    break;
                } else {
                    int value = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + overflow;
                    t.val = value % 10;
                    overflow = value / 10;

                    p = t;
                    t.next = new ListNode(0);
                    t = t.next;

                    if (l1 != null) {
                        l1 = l1.next;
                    }
                    if (l2 != null) {
                        l2 = l2.next;
                    }
                }
            }

            if (t.val == 0 && p != t) {
                p.next = null;
            }

            return r;
        }
    }

    public static void test() {
        Solution s = new Solution();
        ListNode l1 = arrayToList(new int[] {9, 1, 6});
        ListNode l2 = arrayToList(new int[] {0});
        printList(l1);
        printList(l2);

        ListNode l = s.addTwoNumbers(l1, l2);
        printList(l);
    }
}
