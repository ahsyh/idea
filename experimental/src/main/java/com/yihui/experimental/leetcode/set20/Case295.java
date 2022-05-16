package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Case295 {
    public static void test(String[] args) {
        int i = 0;
        testOne(++i);
    }

    private static MedianFinder s = new MedianFinder();

    private static void testOne(int n) {
        int i = 0;
        Log.e("case" + n, "");
        s.addNum(1);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(2);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(3);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(4);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(5);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(6);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(7);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(8);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(9);
        Log.e("result" + (++i) + ":" + s.findMedian());
        s.addNum(10);
        Log.e("result" + (++i) + ":" + s.findMedian());
    }


    private static class MedianFinder {
        private PriorityQueue<Integer> small = new PriorityQueue<>(100,
                (a, b) -> b - a);
        private PriorityQueue<Integer> big = new PriorityQueue<>(100,
                Comparator.comparingInt(a -> a));
        private List<Integer> value =  new ArrayList<>();

        public MedianFinder() {
        }

        public void addNum(int num) {
            int size = small.size() + big.size();

            if (size == 0) {
                value.add(num);
                if (value.size() >= 2) {
                    int a = value.get(0);
                    int b = value.get(1);
                    big.offer(Math.max(a, b));
                    small.offer(Math.min(a, b));
                    value.clear();
                }
                return;
            }

            if (num > big.peek()) {
                big.offer(num);
                while (big.size() > small.size()) {
                    small.offer(big.poll());
                }
            } else {
                small.offer(num);
                while (big.size() < small.size()) {
                    big.offer(small.poll());
                }
            }
        }

        public double findMedian() {
            double r = 0.0;
            int size = small.size() + big.size();

            if (size == 0) {
                if (!value.isEmpty()) {
                    return value.get(0);
                }
            }

            if (small.size() < big.size()) {
                return big.peek();
            } else if (small.size() > big.size()) {
                return small.peek();
            } else {
                return (((double) small.peek()) + ((double) big.peek())) / 2.0;
            }
        }
    }


    private static class Solution {

    }
}

