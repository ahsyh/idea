package com.yihui.experimental.util;

public final class LeetUtil {
    public static void assertTrue(boolean b, String label) {
        if (!b) {
            Log.e("assert", "assertTrue failed! label:" + label);
        }
    }
    public static void assertSameString(String a, String b, String label) {
        if (!a.equals(b)) {
            Log.e("assert", "assertTrue failed! label:" + label);
        }
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        ListNode() {}
        public ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void printList(int[] l) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l.length; i++) {
            sb.append(l[i] + " ");
        }
        Log.e("", sb.toString());
    }

    public static void printList(ListNode l) {
        StringBuilder sb = new StringBuilder();
        while (l != null) {
            sb.append(l.val + " ");
            l = l.next;
        }
        Log.e("", sb.toString());
    }

    public static ListNode arrayToList(int[] a) {
        if (a.length <= 0) return null;

        ListNode t = new ListNode(0);
        ListNode r = t;
        ListNode p = null;

        for (int i = 0; i < a.length; i++) {
            t.val = a[i];
            t.next = new ListNode(0);
            p = t;
            t = t.next;
        }

        if (t.val == 0 && p != t) {
            p.next = null;
        }

        return r;
    }



}
