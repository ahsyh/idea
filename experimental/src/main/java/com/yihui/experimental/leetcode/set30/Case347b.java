package com.yihui.experimental.leetcode.set30;

import com.yihui.experimental.util.LeetUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Case347b {
    private static Solution s = new Solution();

    public static void test(String[] args) {
        int[] r = s.topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        LeetUtil.printList(r);
    }

    static class Solution {
        private static class Ele {
            public Integer n;
            public Integer feq;

            public Ele(int n) {
                this.n = n;
                this.feq = 1;
            }

        }

        public int[] topKFrequent(int[] nums, int k) {
            final int[] r = new int[k];
            final Map<Integer, Ele> m = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int j = nums[i];
                if (m.containsKey(j)) {
                    m.get(j).feq++;
                } else {
                    m.put(j,new Ele(j));
                }
            }

            final PriorityQueue<Ele> q = new PriorityQueue<>(m.size(), (a, b) -> b.feq - a.feq);
            for (Ele e : m.values()) {
                q.offer(e);
            }

            for (int i = 0; i < k; i++) {
                Ele e = q.poll();
                r[i] = e.n;
            }
            q.peek();


            return r;
        }
    }
}
