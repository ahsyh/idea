package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Case146 {
    public static void test(String[] args) {
        int i = 0;
        testOne(++i);
    }

    private static Solution s = new Solution();

    private static void testOne(int n) {
        Log.e("case" + n, "");
        LRUCache l = new LRUCache(2);
        l.put(2,1);
        l.put(1,1);
        l.put(2,3);
        l.put(4,1);
        int i = l.get(1);
        int j = l.get(2);
    }

    public static class LRUCache {
        private final Cache cache;
        public LRUCache(int capacity) {
            this.cache = new Cache(capacity);
        }

        public int get(int key) {
            Element e = cache.get(key);
            return e == null ? -1 : e.val;
        }

        public void put(int key, int val) {
            cache.add(key, val);
        }

        private static class Element {
            int key;
            int val;
            Element prev;
            Element next;

            public Element(int key, int val) {
                this.key = key;
                this.val = val;
                prev = next = null;
            }
        }

        private static class Cache {
            private final int capacity;
            private final Map<Integer, Element> map = new HashMap<>();
            private final Element head = new Element(-1, -1);
            private final Element tail = new Element(-1, -1);

            public Cache(int capacity) {
                this.capacity = capacity;
                head.next = tail;
                tail.prev = head;
            }

            public Element get(int key) {
                Element e = map.get(key);
                if (e == null) {
                    return null;
                }
                makeRecent(e);
                return e;
            }

            public void add(int key, int val) {
                if (map.containsKey(key)) {
                    Element e = map.get(key);
                    e.val = val;
                    makeRecent(e);
                    return;
                }

                Element e = new Element(key, val);
                while (map.size() >= capacity) {
                    removeElement(head.next);
                }
                addElement(e);
            }

            private void addLinkTail(Element e) {
                Element prev = tail.prev;
                Element next = tail;
                prev.next = e;
                next.prev = e;
                e.prev = prev;
                e.next = next;
            }

            private void removeFromLink(Element e) {
                Element prev = e.prev;
                Element next = e.next;
                prev.next = next;
                next.prev = prev;
            }

            private void addElement(Element e) {
                addLinkTail(e);
                map.put(e.key, e);
            }

            private void removeElement(Element e) {
                removeFromLink(e);
                map.remove(e.key);
            }

            private Element makeRecent(Element e) {
                if (e.next == tail) {
                    return e;
                }

                removeFromLink(e);
                addLinkTail(e);
                return e;
            }
        }
    }

    private static class Solution {

    }
}
